package com.altrocks.forms.kitsheetEms;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import com.altrocks.forms.incoming.ReservationDocHeaderJsonFormatPropertiesDAO;
import com.altrocks.forms.kitsheetindent.KitsheetService;
import com.altrocks.forms.kitsheetindent.ProductionOrderComponent4DAO;
import com.altrocks.forms.kitsheetindent.ProductionOrderComponent4JsonFormatPropertiesDAO;
import com.altrocks.forms.kitsheetindent.ProductionOrderComponent4PropertiesDAO;
import com.altrocks.forms.kitsheetindent.ViolinMaterialJsonFormatPropertiesDAO;
import com.altrocks.forms.kitsheetindent.WareHouseJsonFormatPropertiesDAO;
import com.altrocks.forms.master.MasterPlantJsonFormatPropetiesDAO;
import com.altrocks.forms.master.MasterService;
import com.altrocks.forms.utils.APIConstants;
import com.altrocks.forms.utils.UtilsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;


/**
 * 
 * @author Guna
 *
 */

@Service
public class KitesheetCodeOptimazationService {

	@Autowired
	UtilsService Utils;

	@Autowired
	MasterService masterservice;

	@Autowired
	KitsheetService kitsheetservice;

	@Autowired
	KitsheetEmsService kitsheetEmsservice;

	public List<MasterPlantJsonFormatPropetiesDAO> plantListAPI(List<String> plantListCode)
			throws UnirestException, ParseException, InterruptedException, ExecutionException, IOException {

		// System.out.println("API Call 6");

		String BaseAPI = "https://" + Utils.port + "-" + APIConstants.masterReport.YY1APIPLANTCDS;
		String filterExpr = plantListCode.stream().filter(order -> order != null && !order.trim().isEmpty())
				.map(order -> "Plant eq '" + order.trim() + "'").collect(Collectors.joining(" or "));

		String encodedFilter = URLEncoder.encode(filterExpr, StandardCharsets.UTF_8.toString());
		String apiUrl = BaseAPI + "?$filter=" + encodedFilter;
		List<MasterPlantJsonFormatPropetiesDAO> getplantList = masterservice.getPlantListForKitsheetEMS(apiUrl);

		return getplantList;

	}

	public List<WareHouseJsonFormatPropertiesDAO> getwareHouse(List<String> pleantList)
			throws ParseException, UnirestException, IOException {
		// TODO Auto-generated method stub
		String BaseAPI = "https://" + Utils.port + "-" + APIConstants.KitSheet.YY1_warehouse;
		String filterExpr = pleantList.stream().filter(order -> order != null && !order.trim().isEmpty())
				.map(order -> "Plant eq '" + order.trim() + "'").collect(Collectors.joining(" or "));

		String encodedFilter = URLEncoder.encode(filterExpr, StandardCharsets.UTF_8.toString());
		String apiUrl = BaseAPI + "?$filter=" + encodedFilter;

		List<WareHouseJsonFormatPropertiesDAO> getwareHouseList = kitsheetservice
				.getwareHouseListForKitSheetEMS(apiUrl);
		return getwareHouseList;
	}

	public List<ViolinMaterialJsonFormatPropertiesDAO> getViolinMaterial(List<String> productList)
			throws ParseException, UnirestException, IOException {
		// TODO Auto-generated method stub
		List<ViolinMaterialJsonFormatPropertiesDAO> obj = new ArrayList<>();
		try {
			String BaseAPI = "https://" + Utils.port + "-" + APIConstants.masterReport.YY1ViolinMaterial;

			String filterExpr = productList.stream().filter(order -> order != null && !order.trim().isEmpty())
					.map(order -> "Product eq'" + order.trim() + "'").collect(Collectors.joining(" or "));

			String encodedFilter = URLEncoder.encode(filterExpr, StandardCharsets.UTF_8.toString());
			String apiUrl = BaseAPI + "?$filter=" + encodedFilter;

			List<ViolinMaterialJsonFormatPropertiesDAO> violinmaterialList = kitsheetservice
					.getViolinMaterialListForKitSheetEMS(apiUrl);
			obj.addAll(violinmaterialList);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return obj;
	}

	public List<ViolinMaterialJsonFormatPropertiesDAO> getViolinMaterialOpti(String productList)
			throws ParseException, UnirestException, IOException {
		// TODO Auto-generated method stub
		ArrayList<ViolinMaterialJsonFormatPropertiesDAO> arrayList = new ArrayList<>();
		try {
			String BaseAPI = "https://" + Utils.port + "-" + APIConstants.masterReport.YY1ViolinMaterial;

//			 String filterExpr = productList.stream()
//				        .filter(order -> order != null && !order.trim().isEmpty())
//				        .map(order -> "Product eq'"+order.trim()+"'")
//				        .collect(Collectors.joining(" or "));
			String filter = "Product eq '" + productList + "'";
			String encodedFilter = URLEncoder.encode(filter, StandardCharsets.UTF_8.toString());
			String apiUrl = BaseAPI + "?$filter=" + encodedFilter;

			List<ViolinMaterialJsonFormatPropertiesDAO> violinmaterialList = kitsheetservice
					.getViolinMaterialListForKitSheetEMS(apiUrl);
			arrayList.addAll(violinmaterialList);
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return arrayList;
	}

	public List<SaleOrderItemJsonFormatPropertiesDAO> salesorderItem(List<String> salesOrderList)
			throws ParseException, UnirestException, IOException {
		// TODO Auto-generated method stub

		String BaseAPI = "https://" + Utils.port + "-" + APIConstants.KitSheet.A_SalesOrderItem;

		String filterExpr = salesOrderList.stream().filter(order -> order != null && !order.trim().isEmpty())
				.map(order -> "SalesOrder eq '" + order.trim() + "'").collect(Collectors.joining(" or "));

		String encodedFilter = URLEncoder.encode(filterExpr, StandardCharsets.UTF_8.toString());
		String apiUrl = BaseAPI + "?$filter=" + encodedFilter;

		List<SaleOrderItemJsonFormatPropertiesDAO> salesorderItemList = kitsheetEmsservice
				.getSalesOrderItemForKitSheetEMS(apiUrl);
		return salesorderItemList;
	}

	public List<ReservationDocHeaderJsonFormatPropertiesDAO> getReservationDocumentHeader(
			List<String> productionOrderList) throws ParseException, UnirestException, IOException {

		String BaseAPI = "https://" + Utils.port + "-" + APIConstants.MRINReport.AReservationDocumentHeader;
		String filterExpr = productionOrderList.stream().filter(order -> order != null && !order.trim().isEmpty())
				.map(order -> "GoodsRecipientName eq '" + order.trim() + "'").collect(Collectors.joining(" or "));

		String encodedFilter = URLEncoder.encode(filterExpr, StandardCharsets.UTF_8.toString());
		String apiUrl = BaseAPI + "?$filter=" + encodedFilter;

		List<ReservationDocHeaderJsonFormatPropertiesDAO> reservationHeaderList = getReservationDocumentHeader(apiUrl);
		return reservationHeaderList;
	}

	public List<ProductionOrderComponent4JsonFormatPropertiesDAO> getProductionOrderList4(
			List<String> productionOrderList) throws ParseException, UnirestException, IOException {

		String BaseAPI = "https://" + Utils.port + "-" + APIConstants.KitSheet.A_ProductionOrderComponent_4;
		String filterExpr = productionOrderList.stream().filter(order -> order != null && !order.trim().isEmpty())
				.map(order -> "ManufacturingOrder eq '" + order.trim() + "'").collect(Collectors.joining(" or "));

		String encodedFilter = URLEncoder.encode(filterExpr, StandardCharsets.UTF_8.toString());
		String apiUrl = BaseAPI + "?$filter=" + encodedFilter;

		List<ProductionOrderComponent4JsonFormatPropertiesDAO> productionOrderComponentsList = getProductionOrderList4ForKitSheetEMS(
				apiUrl);
		return productionOrderComponentsList;
	}

	public List<AReservationDocumentItemJsonFormatPropertiesDAO> getReservationDocumentItem(
			List<String> reservationList) throws ParseException, UnirestException, IOException {

		String BaseAPI = "https://" + Utils.port + "-" + APIConstants.MRINReport.AReservationDocumentItem;
		String filterExpr = reservationList.stream().filter(order -> order != null && !order.trim().isEmpty())
				.map(order -> "Reservation eq '" + order.trim() + "'").collect(Collectors.joining(" or "));

		String encodedFilter = URLEncoder.encode(filterExpr, StandardCharsets.UTF_8.toString());
		String apiUrl = BaseAPI + "?$filter=" + encodedFilter;

		List<AReservationDocumentItemJsonFormatPropertiesDAO> productionOrderComponentsList = kitsheetEmsservice
				.getReservationDocumentItemForKitSheetEMS(apiUrl);
		return productionOrderComponentsList;
	}

	@Description(value = "Production Order List API Consume")
	public List<ProductionOrderComponent4PropertiesDAO> getProductionOrderList4(String apiUrl)
			throws UnirestException, IOException, ParseException {
		ArrayList<ProductionOrderComponent4PropertiesDAO> arrayList = new ArrayList<>();

		try {
			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.KitSheet.A_ProductionOrderComponent_4);
			HttpResponse<String> jsonresponse = Utils.ApiCall(apiUrl);

			JsonNode getJsonValues = Utils.XmlToJsonConversionTestContent(jsonresponse.getBody());

			ObjectMapper objectMapper = new ObjectMapper();

			List<ProductionOrderComponent4DAO> entryNodesValues = objectMapper.readValue(getJsonValues.traverse(),
					objectMapper.getTypeFactory().constructCollectionType(List.class,
							ProductionOrderComponent4DAO.class));

			List<ProductionOrderComponent4PropertiesDAO> data = entryNodesValues.stream()
					.map(e -> e.getContentDAO().getProperties()).collect(Collectors.toList());

			arrayList.addAll(data);
		} catch (Exception e2) {
			// TODO: handle exception

			System.out.println(
					"KitesheetCodeOptimazationService.getProductionOrderList4() - Error : " + e2.getLocalizedMessage());
		}

		return arrayList;

	}

	@Description(value = "Production Order List API Consume")
	public List<ProductionOrderComponent4JsonFormatPropertiesDAO> getProductionOrderList4ForKitSheetEMS(String apiUrl)
			throws UnirestException, IOException, ParseException {
		ArrayList<ProductionOrderComponent4JsonFormatPropertiesDAO> arrayList = new ArrayList<>();

		try {
			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.KitSheet.A_ProductionOrderComponent_4);
			HttpResponse<String> jsonresponse = Utils.ApiCall(apiUrl + "&$format=json");
			ObjectMapper objectMapper = new ObjectMapper();

			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");

			// Convert using readValue with TypeReference
			List<ProductionOrderComponent4JsonFormatPropertiesDAO> resultsList = objectMapper.readValue(
					resultsNode.toString(),
					new TypeReference<List<ProductionOrderComponent4JsonFormatPropertiesDAO>>() {
					});

//			JsonNode getJsonValues = Utils.XmlToJsonConversionTestContent(jsonresponse.getBody());
//
//			ObjectMapper objectMapper = new ObjectMapper();
//
//			List<ProductionOrderComponent4DAO> entryNodesValues = objectMapper.readValue(getJsonValues.traverse(),
//					objectMapper.getTypeFactory().constructCollectionType(List.class, ProductionOrderComponent4DAO.class));
//
//			List<ProductionOrderComponent4PropertiesDAO> data = entryNodesValues.stream()
//					.map(e -> e.getContentDAO().getProperties()).collect(Collectors.toList());

			arrayList.addAll(resultsList);
		} catch (Exception e2) {
			// TODO: handle exception

			System.out.println("KitesheetCodeOptimazationService.getProductionOrderList4ForKitSheetEMS() - Error : "
					+ e2.getLocalizedMessage());
		}

		return arrayList;

	}

	@Description(value = "Reservation Document Header API Consume")
	public List<ReservationDocHeaderJsonFormatPropertiesDAO> getReservationDocumentHeader(String apiUrl)
			throws UnirestException, IOException, ParseException {

		ArrayList<ReservationDocHeaderJsonFormatPropertiesDAO> arrayList = new ArrayList<>();
		try {

			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.MRINReport.AReservationDocumentHeader);

			HttpResponse<String> jsonresponse = Utils.ApiCall(apiUrl + "&$format=json");
			ObjectMapper objectMapper = new ObjectMapper();

			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");

			// Convert using readValue with TypeReference
			List<ReservationDocHeaderJsonFormatPropertiesDAO> resultsList = objectMapper.readValue(
					resultsNode.toString(), new TypeReference<List<ReservationDocHeaderJsonFormatPropertiesDAO>>() {
					});

//			JsonNode getJsonValues = Utils.XmlToJsonConversionTestContent(jsonresponse.getBody());
//
//			ObjectMapper objectMapper = new ObjectMapper();
//
//			List<ReservationDocHeaderDAO> entryNodesValues = objectMapper.readValue(getJsonValues.traverse(),
//					objectMapper.getTypeFactory().constructCollectionType(List.class, ReservationDocHeaderDAO.class));
//
//			List<ReservationDocHeaderPropertiesDAO> data = entryNodesValues.stream()
//					.map(e -> e.getContent().getProperties()).collect(Collectors.toList());

			arrayList.addAll(resultsList);
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println("KitesheetCodeOptimazationService.getReservationDocumentHeader() - Error : "
					+ e2.getLocalizedMessage());
		}

		return arrayList;

	}

}
