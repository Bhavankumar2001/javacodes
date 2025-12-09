package com.altrocks.forms.mrin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import com.altrocks.forms.incoming.InspectionAPIListService;

import com.altrocks.forms.incoming.ReservationDocLinePropertiesDAO;
import com.altrocks.forms.kitsheetindent.KitsheetService;
import com.altrocks.forms.kitsheetindent.ViolinMaterialJsonFormatPropertiesDAO;
import com.altrocks.forms.kitsheetindent.WareHouseJsonFormatPropertiesDAO;
import com.altrocks.forms.master.MasterPlantJsonFormatPropetiesDAO;
import com.altrocks.forms.master.MasterService;
import com.altrocks.forms.routecard.APIListService;
import com.altrocks.forms.utils.APIConstants;
import com.altrocks.forms.utils.UtilsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
//@RestController
public class MRINService {

	@Autowired
	InspectionAPIListService apiListService;

	@Autowired
	MRINRepository mrinRepository;

	@Autowired
	UtilsService Utils;

	@Autowired
	KitsheetService kitsheetService;

	@Autowired
	APIListService apiListService1;

	@Autowired
	MasterService master;

	public List<ReservationDocHeaderPropertiesDAO> getMRINList(String reservationNumber)
			throws ParseException, UnirestException, IOException {

		List<ReservationDocHeaderPropertiesDAO> reservationDocumentHeader = apiListService
				.getReservationDocumentHeader(reservationNumber);

		// ----------------Filter Functions-----------------------

		List<ReservationDocHeaderPropertiesDAO> afterFilterList = reservationDocumentHeader;

		if (!reservationNumber.isEmpty()) {
			afterFilterList = afterFilterList.stream()
					.filter(e -> e.getReservation().equalsIgnoreCase(reservationNumber)).collect(Collectors.toList());
		}
//
//		if (!costCenter.isEmpty()) {
//			afterFilterList = afterFilterList.stream().filter(e -> e.getCostCenter().equalsIgnoreCase(costCenter))
//					.collect(Collectors.toList());
//		}

		System.out.println("AfterFilterList ==>" + afterFilterList.size());

		for (ReservationDocHeaderPropertiesDAO objList : afterFilterList) {

			objList.setReservationNumber(reservationNumber);
//			objList.setPlant(plant != null ? plant : "");
//			objList.setMaterial(material);
//			objList.setCostCenterNew(costCenter);

		}

		return afterFilterList;

	}

	public void saveMRIN(String reservation)
			throws ParseException, UnirestException, IOException, InterruptedException, ExecutionException {

		List<ReservationDocLinePropertiesDAO> reservationDocumentLine = apiListService
				.getReservationDocumentLine(reservation);
		// List<ProductBasicTextPropertiesDAO> productBasicText =
		// apiListService.getProductBasicText();

		List<String> partNumberList = reservationDocumentLine.stream().filter(e -> e.getProduct() != null)
				.map(ReservationDocLinePropertiesDAO::getProduct).distinct().collect(Collectors.toList());
		List<String> plantDataList = reservationDocumentLine.stream().filter(e -> e.getPlant() != null)
				.map(ReservationDocLinePropertiesDAO::getPlant).distinct().collect(Collectors.toList());

		List<MovingPricePropertiesDAO> productValuationList = apiListService.movingPriceAPIList(partNumberList);
		// CompletableFuture<List<MovingPricePropertiesDAO>> productValuation =
		// apiListService.parallelcall_getmovingprice();

		// CompletableFuture<List<MaterialStoclLocationPropertiesDAO>>
		// materialStockLocation = apiListService.Parallel_AMatlStkInAcctMod();
		// List<ProductStorageLocationPropertiesDAO> productStorageLocation =
		// apiListService.getProductStorageLocation();
		// CompletableFuture<List<MasterPlantPropetiesDAO>> Completable_plantList =
		// master.plantListAPI();

		List<MasterPlantJsonFormatPropetiesDAO> plantList = master.getPlantListBasedOnPlantData(plantDataList);

		// CompletableFuture<List<ProductDescriptionPropertiesDAO>>
		// productDescriptionListAsync =
		// apiListService1.getProductDescriptionListAsync();
		// CompletableFuture<List<WareHousePropertiesDAO>> getwareHouse =
		// kitsheetService.getwareHouse();

		// getwareHouseListBasedOnInputDate
		List<WareHouseJsonFormatPropertiesDAO> getwareHouselist = kitsheetService
				.getwareHouseListBasedOnInputDate(partNumberList);

//		CompletableFuture<List<ViolinMaterialPropertiesDAO>> violinMaterial = kitsheetService.getViolinMaterial();
//		CompletableFuture.allOf(violinMaterial);

		// List<ProductDescriptionPropertiesDAO> productDescriptionList =
		// productDescriptionListAsync.get();
		// List<WareHousePropertiesDAO> getwareHouselist = getwareHouse.get();

		// List<WareHousePropertiesDAO> getwareHouselist = getwareHouse.get();

		// List<MaterialStoclLocationPropertiesDAO> materialStockLocationlist =
		// materialStockLocation.get();

		List<ViolinMaterialJsonFormatPropertiesDAO> violinMaterialList = kitsheetService
				.getViolinMaterialListBasedOnMaterialData(partNumberList);
		// List<ViolinMaterialPropertiesDAO> violinMaterialLis= violinMaterial.get();
		// Get results
		// List<MovingPricePropertiesDAO> aProductValuation = productValuation.get();
//		System.out.println("aProductValuation ::"+ aProductValuation);

		// List<MasterPlantPropetiesDAO> plantList = Completable_plantList.get();

		List<CostCenterPropertiesDAO> costCenterCodeList = CostCenterCodeList();
		List<ReservationDocHeaderPropertiesDAO> afterFilterList = getMRINList(reservation);
		// List<ReservationDocumentDAO> reservationDocumentList =
		// getReservationDocumentList();
		ArrayList<MRINEntity> objList = new ArrayList<>();

		for (ReservationDocHeaderPropertiesDAO obj : afterFilterList) {

			String reservationNumber = obj.getReservation();
			List<ReservationDocLinePropertiesDAO> matchingLines = reservationDocumentLine.stream()
					.filter(e -> e.getReservation().equalsIgnoreCase(reservationNumber)).collect(Collectors.toList());

			String reqName = obj.getGoodsRecipientName();
			for (ReservationDocLinePropertiesDAO line : matchingLines) {

				String getplant = reservationDocumentLine.stream()
						.filter(e -> e.getReservation().equalsIgnoreCase(obj.getReservation())).map(e -> e.getPlant())
						.findFirst().orElse("");
				String plantName = plantList.stream().filter(e -> e.getPlant().equalsIgnoreCase(getplant))
						.map(e -> e.getPlantName()).findFirst().orElse("");

				String partNumber = line.getProduct();

				String itemName = reservationDocumentLine.stream()
						.filter(e -> !partNumber.isEmpty() && e.getProduct().equalsIgnoreCase(partNumber))
						.map(e -> e.getProduct()).findFirst().orElse("");

				String itemName1 = violinMaterialList.stream().filter(e -> e.getDProduct().equalsIgnoreCase(itemName))
						.map(e -> e.getDMaterialDescription()).findFirst().orElse("");

				String requiredQty = reservationDocumentLine.stream()
						.filter(e -> e.getReservation().equalsIgnoreCase(obj.getReservation())
								&& e.getProduct().equalsIgnoreCase(partNumber))
						.map(e -> e.getResvnItmRequiredQtyInBaseUnit()).findFirst().orElse("");

				String valuationList = reservationDocumentLine.stream()
						.filter(e -> e.getReservation().equalsIgnoreCase(obj.getReservation())
								&& e.getProduct().equalsIgnoreCase(partNumber))
						.map(e -> e.getProduct()).findFirst().orElse("");

				String valuationPlantList = reservationDocumentLine.stream()
						.filter(e -> e.getProduct().equalsIgnoreCase(line.getProduct())).map(e -> e.getPlant())
						.findFirst().orElse("");
				final String valuationPlant;
				try {
//					Integer.parseInt(valuationPlantList);
					valuationPlant = valuationPlantList;
				} catch (NumberFormatException ex) {
					throw new IllegalArgumentException("Invalid plant number: " + valuationPlantList, ex);
				}

//				String unitPrice = productValuationList.stream().filter(e -> e.getDProduct().equalsIgnoreCase(partNumber)
//						&& e.getDValuationArea().equalsIgnoreCase(valuationPlantList))
//						.map(e -> e.getDMovingAveragePrice()).findFirst().orElse("");

				Double unitPrice = productValuationList.stream()
						.filter(e -> e.getDProduct().equalsIgnoreCase(partNumber)
								&& Objects.equals(e.getDValuationArea(), valuationPlant) // ✅ now both Integer
								&& ("V".equalsIgnoreCase(e.getDinventoryValuationProcedure())
										|| "S".equalsIgnoreCase(e.getDinventoryValuationProcedure())))
						.map(e -> "V".equalsIgnoreCase(e.getDinventoryValuationProcedure())
								? (e.getDMovingAveragePrice() != null ? e.getDMovingAveragePrice().doubleValue() : 0.0)
								: (e.getDStandardPrice() != null ? e.getDStandardPrice().doubleValue() : 0.0))
						.findFirst().orElse(0.0);

//				System.out.println("unitPrice :: " + unitPrice);

//				System.out.println("unitPrice ::" + unitPrice);

				String baseUnit = reservationDocumentLine.stream()
						.filter(e -> e.getProduct().equalsIgnoreCase(partNumber)).map(e -> e.getBaseUnit()).findFirst()
						.orElse("");

				// System.out.println("unitPrice ::"+ unitPrice);

				String plantObj = reservationDocumentLine.stream()
						.filter(e -> e.getReservation().equalsIgnoreCase(obj.getReservation())).map(e -> e.getPlant())
						.findFirst().orElse("");
				List<String> allowedStorageLocations = getwareHouselist.stream()
						.map(WareHouseJsonFormatPropertiesDAO::getEWMWarehouse).filter(Objects::nonNull).distinct() // optional
																													// //
																													// -
																													// avoid
																													// duplicates
						.collect(Collectors.toList());
				if (plantObj != null && !plantObj.isEmpty()) {

					List<WareHouseJsonFormatPropertiesDAO> matchingWarehouseEntries = getwareHouselist.stream()
							.filter(e -> e.getDMaterial() != null && e.getDMaterial().equalsIgnoreCase(valuationList)
									&& e.getDPlant().equalsIgnoreCase(plantObj))
							.collect(Collectors.toList());

					Map<String, List<WareHouseJsonFormatPropertiesDAO>> batchToEntriesMap = matchingWarehouseEntries
							.stream().filter(e -> e.getDBatch() != null)
							.collect(Collectors.groupingBy(WareHouseJsonFormatPropertiesDAO::getDBatch));
					List<ReservationDocumentDAO> reservationDocumentList = getReservationDocumentList(
							obj.getReservation());
					if (batchToEntriesMap.isEmpty()) {
						// No batch entries — still create one dummy MRIN row
						MRINEntity mrin = new MRINEntity();

						if (obj.getReservationDate() != null && !obj.getReservationDate().has("m:null")) {
							mrin.setMrinDate(
									Utils.ConvertDateFromJsonNodeFormatToString(obj.getReservationDate().toString()));
						} else {
							mrin.setMrinDate(null);
							obj.setReservationDate(null);
						}

						mrin.setPartNumber(partNumber);
						mrin.setBatchNo("");
						mrin.setStoreStock("");
						mrin.setLocation("");
						mrin.setStorageBin("");
						mrin.setReservation(obj.getReservation());

						String costCenter = costCenterCodeList.stream()
								.filter(e -> e.getCostCenter().equalsIgnoreCase(obj.getCostCenter()))
								.map(e -> e.getCostCenterName()).findFirst().orElse("");

						String remarks = reservationDocumentList.stream()
								.filter(e -> e.getReservation().equalsIgnoreCase(obj.getReservation()))
								.map(e -> e.getYY1_Remarks_RDH()).findFirst().orElse("");

						mrin.setRemarks(remarks);
						mrin.setCostCenter(obj.getCostCenter());
						mrin.setDepartment(costCenter);
						mrin.setTitle(obj.getGoodsMovementType().startsWith("ZEN") ? "EIN INDENT SHEET"
								: "MRIN INDENT SHEET");
						mrin.setMrinNo(obj.getReservation());
						mrin.setReqName(reqName);
						mrin.setPlantName(plantName);
						mrin.setItemName(itemName1);
						mrin.setRequiredQty(requiredQty);
//						mrin.setUnitPrice((unitPrice != null && unitPrice.length() > 0) ? unitPrice : "0.00");
						mrin.setUnitPrice(
								(unitPrice != null && unitPrice > 0) ? String.format("%.2f", unitPrice) : "0.00");

						mrin.setBaseunit(baseUnit);
//						mrin.setPlant(plant);
//						mrin.setMaterial(material);
//						mrin.setCostCenter(costCenterNew);

						objList.add(mrin);
					} else {
						batchToEntriesMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
							String batchNo = entry.getKey();

							// Group by storage bin within valid storage locations
							Map<String, List<WareHouseJsonFormatPropertiesDAO>> filteredBinMap = entry.getValue()
									.stream().filter(e -> allowedStorageLocations.contains(e.getDStorageLocation()))
									.collect(
											Collectors.groupingBy(WareHouseJsonFormatPropertiesDAO::getDEWMStorageBin));

							// For each storage bin group, create a new MRINEntity
							filteredBinMap.forEach((storageBin, binEntries) -> {
								MRINEntity mrin = new MRINEntity();

								// Set reservation date
								if (obj.getReservationDate() != null
										&& obj.getReservationDate().has("m:null") == false) {
									mrin.setMrinDate(Utils.ConvertDateFromJsonNodeFormatToString(
											obj.getReservationDate().toString()));
								} else {
									mrin.setMrinDate(null);
									obj.setReservationDate(null);
								}

								mrin.setPartNumber(partNumber);
								mrin.setBatchNo(batchNo);
								mrin.setStorageBin(storageBin != null ? storageBin : "");

								// Extract first non-null stock qty and location
								String storeStock = binEntries.stream()
										.map(WareHouseJsonFormatPropertiesDAO::getEWMStockQuantityInBaseUnit)
										.filter(Objects::nonNull).findFirst().orElse("");

								String location = binEntries.stream()
										.map(WareHouseJsonFormatPropertiesDAO::getDStorageLocation)
										.filter(Objects::nonNull).findFirst().orElse("");

								// Skip if all fields are empty
								if (storeStock.isEmpty() && location.isEmpty()
										&& (storageBin == null || storageBin.isEmpty())) {
									return;
								}

								mrin.setStoreStock(storeStock);
								mrin.setLocation(location);
								mrin.setCostCenter(obj.getCostCenter());
								mrin.setReservation(obj.getReservation());

								String costCenter = costCenterCodeList.stream()
										.filter(e -> e.getCostCenter().equalsIgnoreCase(obj.getCostCenter()))
										.map(e -> e.getCostCenterName()).findFirst().orElse("");

								String remarks = reservationDocumentList.stream()
										.filter(e -> e.getReservation().equalsIgnoreCase(obj.getReservation()))
										.map(e -> e.getYY1_Remarks_RDH()).findFirst().orElse("");

								mrin.setRemarks(remarks);
								mrin.setDepartment(costCenter);
								mrin.setTitle(obj.getGoodsMovementType().startsWith("ZEN") ? "EIN INDENT SHEET"
										: "MRIN INDENT SHEET");
								mrin.setMrinNo(obj.getReservation());
								mrin.setReqName(reqName);
								mrin.setPlantName(plantName);
								mrin.setItemName(itemName1);
								mrin.setRequiredQty(requiredQty);
//								mrin.setUnitPrice((unitPrice != null && unitPrice.length() > 0) ? unitPrice : "0.00");
								mrin.setUnitPrice(
										(unitPrice != null && unitPrice > 0) ? String.format("%.2f", unitPrice)
												: "0.00");

								mrin.setBaseunit(baseUnit);
//					            mrin.setPlant(plant);
//					            mrin.setMaterial(material);
//					            mrin.setCostCenter(costCenterNew);

								objList.add(mrin);
							});
						});
					}
				}
			}
		}
		// System.out.println("objList:"+objList);

		// Step 1: Calculate totalPrice
		for (MRINEntity obj : objList) {
			double requiredQty = 0.00, unitPrice = 0.00;
			if (obj.getRequiredQty() != null && !obj.getRequiredQty().isEmpty()) {
				requiredQty = Double.parseDouble(obj.getRequiredQty());
			}
			if (obj.getUnitPrice() != null && !obj.getUnitPrice().isEmpty()) {
				unitPrice = Double.parseDouble(obj.getUnitPrice());
			}
			obj.setTotalPrice(String.format("%.2f", requiredQty * unitPrice));
		}

		// Step 2: Sort and group by partNumber for lineId logic
		objList.sort(Comparator.comparing(MRINEntity::getPartNumber, Comparator.nullsLast(String::compareTo)));
		Map<String, List<MRINEntity>> grouped11 = new LinkedHashMap<>();
		for (MRINEntity entity : objList) {
			String partNumber = entity.getPartNumber();
			if (partNumber == null || partNumber.trim().isEmpty()) {
				String lastKey = grouped11.keySet().stream().reduce((first, second) -> second).orElse(null);
				if (lastKey != null)
					grouped11.get(lastKey).add(entity);
			} else {
				grouped11.computeIfAbsent(partNumber, k -> new ArrayList<>()).add(entity);
			}
		}

		List<MRINEntity> finalList = new ArrayList<>();
		int lineId = 1;
		for (Map.Entry<String, List<MRINEntity>> entry : grouped11.entrySet()) {
			List<MRINEntity> groupItems = entry.getValue();
			for (int i = 0; i < groupItems.size(); i++) {
				MRINEntity current = groupItems.get(i);
				current.setLineId(lineId);
				if (i > 0) {
					current.setPartNumber("");
					current.setItemName("");
					current.setRequiredQty(null);
					current.setUnitPrice(null);
					current.setTotalPrice(null);
					current.setBaseunit("");
					current.setLineId(null);
				}
				finalList.add(current);
			}
			lineId++;
		}

		List<MRINEntity> objFilterList = finalList;
//		if (plant != null && !plant.isEmpty()) {
//			objFilterList = objFilterList.stream().filter(e -> e.getPlantName().equalsIgnoreCase(plant))
//					.collect(Collectors.toList());
//		}
//		if (material != null && !material.isEmpty()) {
//			objFilterList = objFilterList.stream().filter(e -> e.getMaterial().equalsIgnoreCase(material))
//					.collect(Collectors.toList());
//		}

		mrinRepository.saveAll(objFilterList);
	}

//	@GetMapping("/getReservation")
	@Description(value = "Reservation Document API Consume")
	public List<ReservationDocumentDAO> getReservationDocumentList(String reservationNumber)
			throws JsonMappingException, JsonProcessingException, UnirestException {
		List<ReservationDocumentDAO> FinalList = new ArrayList<>();
		try {

			String ReservationFiltration = "?$filter=Reservation%20eq%20%27" + reservationNumber + "%27";

			HttpResponse<String> jsonResponse = Utils.ApiCall("https://" + Utils.port + "-"
					+ APIConstants.MRINReport.apireservationdocument + ReservationFiltration);

//			String apiUrl = "https://my418933-api.s4hana.cloud.sap/sap/opu/odata4/sap/api_reservation_document/srvd_a2x/sap/apireservationdocument/0001/ReservationDocument?$orderby=Reservation&$top=100000";

			// API Call
//			HttpResponse<String> jsonResponse = Utils.ApiCall1(apiUrl);

			if (jsonResponse.getBody() == null || jsonResponse.getBody().isEmpty()) {
				throw new RuntimeException("Empty API response");
			}

			// Parse the JSON response
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonResponse.getBody());

			JsonNode valueNode = rootNode.path("value");

			/**
			 * Start Of New API Json Consumption
			 * 
			 */

			try {
				if (valueNode.toString().trim().startsWith("[")) {
					// Response is an array
					List<ReservationDocumentDAO> entryNodes = objectMapper.reader().forType(objectMapper
							.getTypeFactory().constructCollectionType(List.class, ReservationDocumentDAO.class))
							.readValue(valueNode.toString());

					FinalList = entryNodes.stream().collect(Collectors.toList());

				} else if (valueNode.toString().trim().startsWith("{")) {
					// Response is a single object
					ReservationDocumentDAO entryNode = objectMapper.readValue(valueNode.toString(),
							ReservationDocumentDAO.class);

					FinalList = Stream.of(entryNode).collect(Collectors.toList());
				} else {
					// Unknown format
					System.out.println("PaymentAdviceService.PaymentAdviceJournalService() - Error");
				}

			} catch (Exception e) {
				System.out.println(
						"PaymentAdviceService.PaymentAdviceJournalService()  - Error : " + e.getLocalizedMessage());
			}

			/* End */

//			if (jsonResponse.getBody() == null || jsonResponse.getBody().isEmpty()) {
//				throw new RuntimeException("Empty API response");
//			}
//
//			// Parse the JSON response
//			ObjectMapper objectMapper = new ObjectMapper();
//			JsonNode rootNode = objectMapper.readTree(jsonResponse.getBody());
//
//			JsonNode valueNode = rootNode.path("value");
//
//			if (!valueNode.isArray()) {
//				throw new RuntimeException("Expected array under 'value'");
//			}
//
//			List<ReservationDocumentDAO> results = objectMapper.readValue(valueNode.toString(),
//					new TypeReference<List<ReservationDocumentDAO>>() {
//					});

//			System.out.println("result ::" + results);

		} catch (Exception e) {
			System.out.println("Reservation Document API Error: " + e.getMessage());

		}
		return FinalList;
	}

	@Description(value = "Cost Center API Consume")
	public List<CostCenterPropertiesDAO> CostCenterCodeList()
			throws JsonMappingException, JsonProcessingException, UnirestException {
		try {
			// API Call
			HttpResponse<String> jsonresponse = Utils
					.ApiCall("https://" + Utils.port + "-" + APIConstants.MRINReport.ACostCenterText2);

			if (jsonresponse.getBody() == null || jsonresponse.getBody().isEmpty()) {
				throw new RuntimeException("Empty API response");
			}

			ObjectMapper objectMapper = new ObjectMapper();

			// Parse the JSON response
			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());

			// Navigate to the "value" node
			JsonNode resultsNode = rootNode.path("value");
//			System.out.println("resultsNode: " + resultsNode);

			// Check if resultsNode is an array
			if (!resultsNode.isArray()) {
				throw new RuntimeException("Expected an array in 'value' node but found something else.");
			}

			// Convert JSON array to List<CostCenterPropertiesDAO>
			List<CostCenterPropertiesDAO> resultsList = objectMapper.readValue(resultsNode.toString(),
					new TypeReference<List<CostCenterPropertiesDAO>>() {
					});

			// Debug: Print the mapped list
//			resultsList.forEach(System.out::println);

			return resultsList;

		} catch (Exception e) {
			System.out.println("Cost Center API Error: " + e.getMessage());
			return new ArrayList<>(); // Return an empty list in case of error
		}
	}

}
