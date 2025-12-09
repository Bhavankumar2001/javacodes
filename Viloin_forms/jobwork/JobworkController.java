package com.altrocks.forms.jobwork;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.altrocks.forms.debitNote.A_PurchaseOrderNoteDAO;
import com.altrocks.forms.routecard.APIListService;
import com.altrocks.forms.utils.UtilsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/violin/forms")
public class JobworkController {

	@Autowired
	UtilsService Utils;

	@Autowired
	JobWorkService jobWorkService;

	@Autowired
	ApiService apiService;

	@Autowired
	APIListService listService;

	@Value("${app.inspection.path}")
	private String layOutPath;

	@Autowired
	JdbcTemplate jdbc;

	@Autowired
	ApiParameters apiParameters;

	@Value("${app.logo.path}")
	public String logoPath;

	@Description(value = " Home Page!!")
	@GetMapping(value = "/jobwork")
	public String JobWorkHomePage(Model model) throws ParseException, UnirestException, IOException {

		model.addAttribute("client", Utils.clientDetails);
		model.addAttribute("environment", Utils.applicationEnvironment);

//		try {
//
//		} catch (Exception e) {
//			e.getMessage();
//		}

		return "Incoming/JobWork";
	}

	@Description(value = "Display MRIN Form")
	@PostMapping(value = "/jobwork/filter")
	public ResponseEntity<List<purchaseOrderPropertiesDAO>> inspectionFormFilter(HttpServletRequest request,
			Model model)
			throws UnirestException, IOException, ParseException, InterruptedException, ExecutionException {

		String reservationNumber = request.getParameter("reservationNumber");

		model.addAttribute("client", Utils.clientDetails);
		model.addAttribute("environment", Utils.applicationEnvironment);

		List<purchaseOrderPropertiesDAO> jobworkList = jobWorkService.getJobworkList(reservationNumber);
//		System.out.println("jobworkList ::" + jobworkList);

		return ResponseEntity.ok(jobworkList);
	}

//	@Description(value = "MRIN Form Generate")
//	@GetMapping("/mrin/list/{reservation}")
//	public ResponseEntity<byte[]> InspectionFormGenerate(@PathVariable String reservation) throws UnirestException, IOException, ParseException, InterruptedException, ExecutionException {
//
//		
//		String UpdateExisitngLog = "DELETE FROM mrin WHERE reservation = '" + reservation + "' ";
//
//		jdbc.update(UpdateExisitngLog);
//
////		jobWorkService.saveJobWork(reservation);
//
//		HashMap<String, Object> map = new HashMap();
//
//		map.put("reservation", reservation);
//		map.put("logo", logoPath);
//
////		map.put("logo", Utils.paymentAdviceJasperImagePath);
//
//		String mainLayOutPath = layOutPath + "JobWork.jrxml";
//		byte[] reportBytes = null;
//		try {
//
//			JasperReport reportNew = JasperCompileManager.compileReport(mainLayOutPath);
//
//			
//			try (Connection con = DriverManager.getConnection(Utils.databaseConnectionUrl, Utils.databaseUsername,
//					Utils.databasePassword)) {
//
//				// Fill the report
//				JasperPrint jasperPrint = JasperFillManager.fillReport(reportNew, map, con);
//
//				// Export to PDF
//				reportBytes = JasperExportManager.exportReportToPdf(jasperPrint);
//
//			} catch (JRException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//
//		} catch (Exception e) {
//			System.out.println("LayOut Path Error :" + e.getMessage());
//		}
//
//		return ResponseEntity.ok().header("Content-Type", "application/pdf")
//				.header("Content-Disposition", "inline; filename=MRIN" + ".pdf").body(reportBytes);
//
//	}
	@Description(value = "Jobwork Form Generate")
	@GetMapping("/jobwork/list/{purchaseOrder}")
	public ResponseEntity<byte[]> InspectionFormGenerate(@PathVariable String purchaseOrder)
			throws UnirestException, IOException, ParseException, InterruptedException, ExecutionException {

		String UpdateExisitngLog = "DELETE FROM mrin WHERE reservation = '" + purchaseOrder + "' ";

		jdbc.update(UpdateExisitngLog);

//		jobWorkService.saveJobWork(reservation);

		HashMap<String, Object> map = new HashMap();

		map.put("reservation", purchaseOrder);
		map.put("logo", logoPath);

//		map.put("logo", Utils.paymentAdviceJasperImagePath);

		String mainLayOutPath = layOutPath + "JobWork.jrxml";
		byte[] reportBytes = null;
		List<JobWorkDAO> serviceprocessedList = new ArrayList<>();
		List<ItemIssueDAO> itemIssuedList = new ArrayList<>();
		List<RecieveDetailDAO> receiveDetailList = new ArrayList<>();

		try {

			List<purchaseOrderPropertiesDAO> purchaseOrderHeader = apiParameters.purchaseOrderAPI(purchaseOrder);

			List<purchaseOrderItemPropertiesDAO> purchaseOrderItemList = apiParameters
					.purchaseOrderItemAPI(purchaseOrder);

			String plantParam = purchaseOrderItemList.stream()
					.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder)).map(e -> e.getPlant())
					.findFirst().orElse("");

			List<PlantAddressPropertiesDAO> plantAddress = apiParameters.plantAPI(plantParam);

			String Supplier = purchaseOrderHeader.stream()
					.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder)).map(e -> e.getSupplier())
					.findFirst().orElse("");

			List<supplierAddressPropertiesDAO> supplierAddressList = apiParameters.getSupplierAddress(Supplier);

			List<PurchaseOrderScheduleLinePropertiesDAO> purchaseOrderScheduleLineList = apiParameters
					.getPurchaseOrderScheduleLine(purchaseOrder);

			List<POSubcontractingComponentPropertiesDAO> poSubcontractingComponentList = apiParameters
					.getPOSubcontractingComponent(purchaseOrder);
			List<PaymenttermstextPropertiesDAO> paymenttermstext = apiParameters.getPaymenttermstext(purchaseOrder);

//			List<purchaseOrderPropertiesDAO> purchaseorderlist = purchaseOrderHeader.get();
//			List<PlantAddressPropertiesDAO> plantAddressList = plantAddressAsync.get();
//			List<supplierAddressPropertiesDAO> supplierAddressList = supplierAddressAsync.get();
//			List<purchaseOrderItemPropertiesDAO> purchaseOrderItemList = purchaseOrderItemAsync.get();
//			List<PurchaseOrderScheduleLinePropertiesDAO> purchaseOrderScheduleLineList = purchaseOrderScheduleLineAsync
//					.get();
//			List<POSubcontractingComponentPropertiesDAO> poSubcontractingComponentList = poSubcontractingComponentAsync
//					.get();
//			List<ProductPlantPropertiesDAO> aProductPlantList = aProductPlantAsync.get();
//			List<ProductDescriptionPropertiesDAO> productDescriptionList = productDescriptionListAsync.get();
//			List<ProductPropertiesDAO> productList = productListAsync.get();
//			List<ProductValuationPropertiesDAO> productValuationList = productValuationAsync.get();

//			String purchaseOrderNumber = purchaseOrderHeader.stream()
//					.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder)).map(e -> e.getPurchaseOrder())
//					.findFirst().orElse("");

//			String plant = purchaseOrderItemList.stream()
//					.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrderNumber)).map(e -> e.getPlant())
//					.findFirst().orElse("");

//			List<PlantAddressPropertiesDAO> PlantAddress = plantAddress.stream()
//					.filter(e -> e.getPlant().equalsIgnoreCase(plant)).collect(Collectors.toList());

			// plant name
			String header1 = "";
			String header2 = "";
			String header3 = "";
			String header4 = "";
			String header5 = "";
//			String header6 = "";

			if (plantAddress.size() > 0) {

				PlantAddressPropertiesDAO plantAddressPropertiesDAO = plantAddress.get(0);

				header1 = plantAddressPropertiesDAO.getAddresseeFullName() + " - "
						+ plantAddressPropertiesDAO.getPlantName();

				header2 = plantAddressPropertiesDAO.getStreetName() + " "
						+ plantAddressPropertiesDAO.getStreetPrefixName1();

				header3 = plantAddressPropertiesDAO.getCityName() + " " + plantAddressPropertiesDAO.getPostalCode()
						+ " " + plantAddressPropertiesDAO.getRegion();

				header4 = "GSTIN :" + plantAddressPropertiesDAO.getINGSTIdentificationNumber();

			}
			map.put("header1", header1);
			map.put("header2", header2);
			map.put("header3", header3);
			map.put("header4", header4);
//			map.put("header5", header5);
//			map.put("header6", header6);

			// for supplier vendor name
//
//			List<supplierAddressPropertiesDAO> supplierAddress = supplierAddressList.stream()
//					.filter(e -> e.getSupplier().equalsIgnoreCase(Supplier)).collect(Collectors.toList());

			String header7 = "";
			String header8 = "";
			String header9 = "";

			if (supplierAddressList.size() > 0) {

				supplierAddressPropertiesDAO supplierAddressPropertiesDAO = supplierAddressList.get(0);

				header7 = supplierAddressPropertiesDAO.getSupplierName();
				header8 = supplierAddressPropertiesDAO.getHouseNumber() + " "
						+ supplierAddressPropertiesDAO.getBPAddrStreetName();
				header9 = supplierAddressPropertiesDAO.getPostalCode();

//				map.put("SupplierName", supplierAddressPropertiesDAO.getSupplierName());
				map.put("BPAddrCityName", supplierAddressPropertiesDAO.getBPAddrCityName());
//				map.put("BPAddrStreetName", supplierAddressPropertiesDAO.getBPAddrStreetName());
				map.put("GSTIN_Supplier", supplierAddressPropertiesDAO.getTaxNumber3());

			}
			map.put("header7", header7);
			map.put("header8", header8);
			map.put("header9", header9);

			map.put("PurchaseOrder", purchaseOrder);
			String creationDate = purchaseOrderHeader.stream()
					.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder)).map(e -> e.getCreationDate())
					.findFirst().orElse("");
			map.put("PurchaseOrder_CreationDate", formatODataDate(creationDate));

			String paymentTerms = paymenttermstext.stream()
					.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder))
					.map(e -> e.getPaymentTermsConditionDesc()).findFirst().orElse("");
			map.put("PaymentTerms", paymentTerms);

			List<A_PurchaseOrderNoteDAO> puchaseNoteApi = apiParameters.puchaseNoteApi(purchaseOrder);
			String termsAndCondition = puchaseNoteApi.stream()
					.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder) && e.getTextObjectType().equalsIgnoreCase("F02")).map(e -> e.getPlainLongText())
					.findFirst().orElse("");

			String[] sentences = termsAndCondition.split("\\.\\s*");
			StringBuilder numbered = new StringBuilder();
			int counter = 7; // start from 7
			for (String s : sentences) {
				if (!s.trim().isEmpty()) {
					numbered.append(counter++).append(". ").append(s.trim()).append(".\n");
				}
			}

			map.put("TermsAndCondition", numbered.toString());

//			map.put("TermsAndCondition", termsAndCondition);
//			System.out.println("termsAndCondition ::"+ termsAndCondition);
//			String deliveryDate = purchaseOrderScheduleLineList.stream()
//					.filter(e -> e.getPurchasingDocument().equalsIgnoreCase(purchaseOrder)).map(e -> {
//						JsonNode node = e.getScheduleLineDeliveryDate();
//						if (node != null && !node.isNull()) {
//							String raw = node.asText(); // "/Date(1752192000000)/"
//							if (raw.startsWith("/Date(")) {
//								long epochMillis = Long.parseLong(raw.replaceAll("[^0-9]", ""));
//								return new SimpleDateFormat("yyyy-MM-dd").format(new Date(epochMillis));
//							}
//						}
//						return "";
//					}).findFirst().orElse("");
//
//			map.put("deliveryDate", deliveryDate);

			String deliveryDate = purchaseOrderScheduleLineList.stream()
					.filter(e -> e.getPurchasingDocument().equalsIgnoreCase(purchaseOrder))
					.map(e -> parseDeliveryDate(e.getScheduleLineDeliveryDate())).findFirst().orElse("");

			map.put("deliveryDate", deliveryDate);

//			line items

//			Service table 
			List<purchaseOrderPropertiesDAO> LineList = jobWorkService.getJobworkList(purchaseOrder);

			for (purchaseOrderPropertiesDAO purchaseOrderItemPropertiesDAO : LineList) {

				JobWorkDAO dao = new JobWorkDAO();
				dao.setService(purchaseOrderItemPropertiesDAO.getYY1ProcessListPOPDHT());
				// SUM QTY
				Double qty = purchaseOrderItemList.stream()
						.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder))
						.mapToDouble(e -> e.getOrderQuantity()).sum();
				dao.setQty(qty);
				dao.setQty(qty);
				String uom = purchaseOrderItemList.stream()
						.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder))
						.map(e -> e.getPurchaseOrderQuantityUnit()).findFirst().orElse("N/A"); // fallback if not found

				dao.setUom(uom);

				Double rate = purchaseOrderItemList.stream()
						.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder))
						.map(e -> e.getNetPriceAmount()).findFirst().orElse(0.00);
				dao.setRate(rate);
				Double amount = qty * rate;
				dao.setAmount(amount);

				serviceprocessedList.add(dao);

				// item issues table

				List<String> itemCodes = poSubcontractingComponentList.stream()
						.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder)).map(e -> e.getMaterial())
						.distinct().collect(Collectors.toList());
				List<ProductDescriptionPropertiesDAO> productDescriptionList = apiParameters
						.getProductDescriptionListAsync(itemCodes);

				List<ProductPlantPropertiesDAO> ProductPlantList = apiParameters.getAProductPlant(itemCodes);

				List<ProductPropertiesDAO> productList = apiParameters.getProductListAsync(itemCodes);

				List<ProductValuationPropertiesDAO> productValuationList = apiParameters
						.getProductValuationAsync(itemCodes);

				for (String itemCode : itemCodes) {

					// Filter all component entries for this itemCode
					List<POSubcontractingComponentPropertiesDAO> itemComponents = poSubcontractingComponentList.stream()
							.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder)
									&& e.getMaterial().equalsIgnoreCase(itemCode))
							.collect(Collectors.toList());

					// If NO components found → still add empty row
					if (itemComponents.isEmpty()) {
						ItemIssueDAO issueDao = new ItemIssueDAO();
						issueDao.setItemCode(itemCode);
						issueDao.setParticulars("");
						itemIssuedList.add(issueDao);
						continue;
					}

					// Loop for each component row
					for (POSubcontractingComponentPropertiesDAO comp : itemComponents) {

						ItemIssueDAO issueDao = new ItemIssueDAO();
						issueDao.setItemCode(itemCode);

						// DESCRIPTION
						String description = productDescriptionList.stream()
								.filter(e -> e.getProduct().equalsIgnoreCase(itemCode))
								.map(e -> e.getProductDescription()).findFirst().orElse("");
						issueDao.setParticulars(description);

						// HSN CODE
						String hsnCode = ProductPlantList.stream()
								.filter(e -> e.getProduct().equalsIgnoreCase(itemCode))
								.map(e -> e.getConsumptionTaxCtrlCode()).findFirst().orElse("");
						issueDao.setHsnCode(hsnCode);

						// WEIGHT
						double weightValue = productList.stream().filter(e -> e.getProduct().equalsIgnoreCase(itemCode))
								.map(e -> e.getNetWeight()).findFirst().map(Double::parseDouble).orElse(0.0);
						issueDao.setWeight(String.valueOf(weightValue));

						// QTY (multiple rows allowed)
						double qtyIssueValue = Double.parseDouble(comp.getQuantityInEntryUnit());
						issueDao.setQty(qtyIssueValue);

						// UOM
						String uomIssue = productList.stream().filter(e -> e.getProduct().equalsIgnoreCase(itemCode))
								.map(e -> e.getBaseUnit()).findFirst().orElse("");
						issueDao.setUom(uomIssue);

						// NET WT
						double netWt = weightValue * qtyIssueValue;
						issueDao.setNetWt(netWt);

						// RATE
						double rateIssue = productValuationList.stream()
								.filter(e -> e.getProduct().equalsIgnoreCase(itemCode)).map(e -> {
									if ("V".equalsIgnoreCase(e.getInventoryValuationProcedure())) {
										return e.getMovingAveragePrice();
									} else if ("S".equalsIgnoreCase(e.getInventoryValuationProcedure())) {
										return e.getStandardPrice();
									} else {
										return 0.0;
									}
								}).findFirst().orElse(0.0);
						issueDao.setRate(rateIssue);

						// AMOUNT
						double amountIssue = qtyIssueValue * rateIssue;
						issueDao.setAmount(amountIssue);

						// ADD to final list
						itemIssuedList.add(issueDao);

//						System.out.println("issueDao ::" + issueDao);
					}
				}

//				Receive Detail
//				RecieveDetailDAO receivedao = new RecieveDetailDAO();

//				String material = purchaseOrderItemList.stream()
//						.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder)).map(e -> e.getMaterial())
//						.findFirst().orElse("");
//				receivedao.setItem(material);
//
//				String receiveDescription = purchaseOrderItemList.stream()
//						.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder))
//						.map(e -> e.getPurchaseOrderItemText()).findFirst().orElse("");
//				receivedao.setDescription(receiveDescription);
//
//				Double receiveQty = purchaseOrderItemList.stream()
//						.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder))
//						.map(e -> e.getOrderQuantity()).findFirst().orElse(0.00);
//				receivedao.setQty(receiveQty);
//
//				String receiveUom = purchaseOrderItemList.stream()
//						.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder))
//						.map(e -> e.getPurchaseOrderQuantityUnit()).findFirst().orElse("");
//
//				receivedao.setUom(receiveUom);
//				receiveDetailList.add(receivedao);

				for (purchaseOrderItemPropertiesDAO item : purchaseOrderItemList) {

					if (item.getPurchaseOrder().equalsIgnoreCase(purchaseOrder)) {

						RecieveDetailDAO receivedao = new RecieveDetailDAO();

						receivedao.setItem(item.getMaterial());
						receivedao.setDescription(item.getPurchaseOrderItemText());
						receivedao.setQty(item.getOrderQuantity());
						receivedao.setUom(item.getPurchaseOrderQuantityUnit());

						receiveDetailList.add(receivedao);
					}
				}

			}
//			System.out.println("saveList ::" + serviceprocessedList);
//			System.out.println("itemIssuedList ::" + itemIssuedList);
//			System.out.println("receiveDetailList ::" + receiveDetailList);

			if (!serviceprocessedList.isEmpty()) {
				map.put("lineDataSet", new JRBeanCollectionDataSource(serviceprocessedList));
			}
			if (!itemIssuedList.isEmpty()) {
				map.put("itemIssuedList", new JRBeanCollectionDataSource(itemIssuedList));
			}

			if (!receiveDetailList.isEmpty()) {
				map.put("receiveDetailList", new JRBeanCollectionDataSource(receiveDetailList));
			}

			try {

				JasperReport report = JasperCompileManager.compileReport(mainLayOutPath);
				JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, new JREmptyDataSource());
				reportBytes = JasperExportManager.exportReportToPdf(jasperPrint);

			} catch (Exception e) {
				System.out.println("LayOut Path Error :" + e.getMessage());
			}

			return ResponseEntity.ok().header("Content-Type", "application/pdf")
					.header("Content-Disposition", "inline; filename=JobWork" + ".pdf").body(reportBytes);

		} catch (JsonProcessingException | UnirestException | InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private String parseDeliveryDate(JsonNode node) {
		if (node == null || node.isNull()) {
			return "";
		}
		String raw = node.asText(); // e.g. "/Date(1752192000000)/"
		if (raw.startsWith("/Date(")) {
			long epochMillis = Long.parseLong(raw.replaceAll("[^0-9]", ""));
			return new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH).format(new Date(epochMillis));
		}
		return "";
	}

	private String formatIsoDate(String isoDate) {
		if (isoDate == null || isoDate.trim().isEmpty()) {
			return "";
		}
		try {
			LocalDateTime dateTime = LocalDateTime.parse(isoDate, DateTimeFormatter.ISO_DATE_TIME);
			return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		} catch (Exception e) {
			// log or handle if format is different
			return isoDate; // fallback: return as is
		}
	}

	public String formatODataDate(String odataDate) {
		if (odataDate == null || odataDate.isEmpty()) {
			return "";
		}

		try {
			// Extract the number from /Date(1754956800000)/
			String millisString = odataDate.replaceAll("[^0-9]", "");
			long millis = Long.parseLong(millisString);

			// Convert to LocalDate
			LocalDate date = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate();

			// Format as 17-Jul-25
			return date.format(DateTimeFormatter.ofPattern("dd-MMM-yy", Locale.ENGLISH));

		} catch (Exception e) {
			// In case of bad data, just return original
			return odataDate;
		}
	}

}
