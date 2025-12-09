package com.altrocks.forms.jobwork;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import com.altrocks.forms.debitNote.A_PurchaseOrderNoteDAO;
import com.altrocks.forms.utils.APIConstants;
import com.altrocks.forms.utils.UtilsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;


@Service
public class ApiParameters {
	

	@Autowired
	UtilsService Utils;
	
	@Description(value = "Purchase Order API Consume")
	public List<purchaseOrderPropertiesDAO> purchaseOrderAPI(String purchaseOrder) {

		ArrayList<purchaseOrderPropertiesDAO> FinalList = new ArrayList<purchaseOrderPropertiesDAO>();

		try {

			// String APiFilter = "?$filter=SupplierInvoice eq '" + supplierinvoice + "'";

			String APiFilter = "?$filter=" + URLEncoder.encode("PurchaseOrder eq '" + purchaseOrder + "'", "UTF-8");

			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.PaymentAdviceForm.AcctDocItemCubeAPI + ApiFields+
			// APiFilter+ "&$format=json");
			HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port + "-"
					+ APIConstants.debitNote.A_PurchaseOrder + APiFilter + "&$format=json");
			ObjectMapper objectMapper = new ObjectMapper();
			// Parse the JSON into a tree
			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");
			// Convert using readValue with TypeReference
			List<purchaseOrderPropertiesDAO> resultsList = objectMapper.readValue(resultsNode.toString(),
					new TypeReference<List<purchaseOrderPropertiesDAO>>() {
					});

			FinalList.addAll(resultsList);
		} catch (Exception e) {
			System.out.println("ApiParameters.purchaseOrderAPI() - Error : " + e.getLocalizedMessage());
		}
		return FinalList;
	}
	
	
	@Description(value = "Purchase Order Item API Consume")
	public List<purchaseOrderItemPropertiesDAO> purchaseOrderItemAPI(String purchaseOrder) {

		ArrayList<purchaseOrderItemPropertiesDAO> FinalList = new ArrayList<purchaseOrderItemPropertiesDAO>();

		try {

			// String APiFilter = "?$filter=SupplierInvoice eq '" + supplierinvoice + "'";

			String APiFilter = "?$filter=" + URLEncoder.encode("PurchaseOrder eq '" + purchaseOrder + "'", "UTF-8");

			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.PaymentAdviceForm.AcctDocItemCubeAPI + ApiFields+
			// APiFilter+ "&$format=json");
			HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port + "-"
					+ APIConstants.debitNote.A_PurchaseOrderItem + APiFilter + "&$format=json");
			ObjectMapper objectMapper = new ObjectMapper();
			// Parse the JSON into a tree
			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");
			// Convert using readValue with TypeReference
			List<purchaseOrderItemPropertiesDAO> resultsList = objectMapper.readValue(resultsNode.toString(),
					new TypeReference<List<purchaseOrderItemPropertiesDAO>>() {
					});

			FinalList.addAll(resultsList);
		} catch (Exception e) {
			System.out.println("ApiParameters.purchaseOrderItemAPI() - Error : " + e.getLocalizedMessage());
		}
		return FinalList;
	}
	
	
	@Description(value = "plant API Consume")
	public List<PlantAddressPropertiesDAO> plantAPI(String plant) {

		ArrayList<PlantAddressPropertiesDAO> FinalList = new ArrayList<PlantAddressPropertiesDAO>();

		try {

			// String APiFilter = "?$filter=SupplierInvoice eq '" + supplierinvoice + "'";

			String APiFilter = "?$filter=" + URLEncoder.encode("Plant eq '" + plant + "'", "UTF-8");

			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.PaymentAdviceForm.AcctDocItemCubeAPI + ApiFields+
			// APiFilter+ "&$format=json");
			HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port + "-"
					+ APIConstants.debitNote.YY1_plantaddr + APiFilter + "&$format=json");
			ObjectMapper objectMapper = new ObjectMapper();
			// Parse the JSON into a tree
			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");
			// Convert using readValue with TypeReference
			List<PlantAddressPropertiesDAO> resultsList = objectMapper.readValue(resultsNode.toString(),
					new TypeReference<List<PlantAddressPropertiesDAO>>() {
					});

			FinalList.addAll(resultsList);
		} catch (Exception e) {
			System.out.println("ApiParameters.plantAPI() - Error : " + e.getLocalizedMessage());
		}
		return FinalList;
	}
	
	
	@Description(value = "Supplier address API Consume")
	public List<supplierAddressPropertiesDAO> getSupplierAddress(String supplier) {

		ArrayList<supplierAddressPropertiesDAO> FinalList = new ArrayList<supplierAddressPropertiesDAO>();

		try {

			// String APiFilter = "?$filter=SupplierInvoice eq '" + supplierinvoice + "'";

			String APiFilter = "?$filter=" + URLEncoder.encode("Supplier eq '" + supplier + "'", "UTF-8");

			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.PaymentAdviceForm.AcctDocItemCubeAPI + ApiFields+
			// APiFilter+ "&$format=json");
			HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port + "-"
					+ APIConstants.debitNote.YY1_SupplierAddress + APiFilter + "&$format=json");
			ObjectMapper objectMapper = new ObjectMapper();
			// Parse the JSON into a tree
			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");
			// Convert using readValue with TypeReference
			List<supplierAddressPropertiesDAO> resultsList = objectMapper.readValue(resultsNode.toString(),
					new TypeReference<List<supplierAddressPropertiesDAO>>() {
					});

			FinalList.addAll(resultsList);
		} catch (Exception e) {
			System.out.println("ApiParameters.getSupplierAddress() - Error : " + e.getLocalizedMessage());
		}
		return FinalList;
	}
	
	
	

	@Description(value = "A_PurchaseOrderScheduleLine API Consume")
	public List<PurchaseOrderScheduleLinePropertiesDAO> getPurchaseOrderScheduleLine(String purchaseOrder) {

		ArrayList<PurchaseOrderScheduleLinePropertiesDAO> FinalList = new ArrayList<PurchaseOrderScheduleLinePropertiesDAO>();

		try {

			// String APiFilter = "?$filter=SupplierInvoice eq '" + supplierinvoice + "'";

			String APiFilter = "?$filter=" + URLEncoder.encode("PurchasingDocument eq '" + purchaseOrder + "'", "UTF-8");

			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.PaymentAdviceForm.AcctDocItemCubeAPI + ApiFields+
			// APiFilter+ "&$format=json");
			HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port + "-"
					+ APIConstants.jobWork.A_PurchaseOrderScheduleLine + APiFilter + "&$format=json");
			ObjectMapper objectMapper = new ObjectMapper();
			// Parse the JSON into a tree
			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");
			// Convert using readValue with TypeReference
			List<PurchaseOrderScheduleLinePropertiesDAO> resultsList = objectMapper.readValue(resultsNode.toString(),
					new TypeReference<List<PurchaseOrderScheduleLinePropertiesDAO>>() {
					});

			FinalList.addAll(resultsList);
		} catch (Exception e) {
			System.out.println("ApiParameters.getPurchaseOrderScheduleLine() - Error : " + e.getLocalizedMessage());
		}
		return FinalList;
	}
	
	
	
	@Description(value = "A_PurchaseOrderScheduleLine API Consume")
	public List<POSubcontractingComponentPropertiesDAO> getPOSubcontractingComponent(String purchaseOrder) {

		ArrayList<POSubcontractingComponentPropertiesDAO> FinalList = new ArrayList<POSubcontractingComponentPropertiesDAO>();

		try {

			// String APiFilter = "?$filter=SupplierInvoice eq '" + supplierinvoice + "'";

			String APiFilter = "?$filter=" + URLEncoder.encode("PurchaseOrder eq '" + purchaseOrder + "'", "UTF-8");

			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.PaymentAdviceForm.AcctDocItemCubeAPI + ApiFields+
			// APiFilter+ "&$format=json");
			HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port + "-"
					+ APIConstants.jobWork.POSubcontractingComponent + APiFilter + "&$format=json");
			ObjectMapper objectMapper = new ObjectMapper();
			// Parse the JSON into a tree
			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");
			// Convert using readValue with TypeReference
			List<POSubcontractingComponentPropertiesDAO> resultsList = objectMapper.readValue(resultsNode.toString(),
					new TypeReference<List<POSubcontractingComponentPropertiesDAO>>() {
					});

			FinalList.addAll(resultsList);
		} catch (Exception e) {
			System.out.println("ApiParameters.getPOSubcontractingComponent() - Error : " + e.getLocalizedMessage());
		}
		return FinalList;
	}
	
	
	
	@Description(value = "product Plant API Consume")
	public List<ProductPlantPropertiesDAO> getAProductPlant(List<String> productPlantList) {

		ArrayList<ProductPlantPropertiesDAO> FinalList = new ArrayList<ProductPlantPropertiesDAO>();

		try {
			
			String filterCondition = productPlantList.stream()
	                .map(p -> "Product eq '" + p + "'")
	                .collect(Collectors.joining(" or "));

			// String APiFilter = "?$filter=SupplierInvoice eq '" + supplierinvoice + "'";

			String APiFilter = "?$filter=" + URLEncoder.encode(filterCondition, "UTF-8");

			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.PaymentAdviceForm.AcctDocItemCubeAPI + ApiFields+
			// APiFilter+ "&$format=json");
			HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port + "-"
					+ APIConstants.jobWork.A_ProductPlant + APiFilter + "&$format=json");
			ObjectMapper objectMapper = new ObjectMapper();
			// Parse the JSON into a tree
			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");
			// Convert using readValue with TypeReference
			List<ProductPlantPropertiesDAO> resultsList = objectMapper.readValue(resultsNode.toString(),
					new TypeReference<List<ProductPlantPropertiesDAO>>() {
					});

			FinalList.addAll(resultsList);
		} catch (Exception e) {
			System.out.println("ApiParameters.getAProductPlant() - Error : " + e.getLocalizedMessage());
		}
		return FinalList;
	}
	
	
	@Description(value = "product API Consume")
	public List<ProductPropertiesDAO> getProductListAsync(List<String>  productList) {

		ArrayList<ProductPropertiesDAO> FinalList = new ArrayList<ProductPropertiesDAO>();

		try {

//			// String APiFilter = "?$filter=SupplierInvoice eq '" + supplierinvoice + "'";
//
//			String APiFilter = "?$filter=" + URLEncoder.encode("Product eq '" + purchaseOrder + "'", "UTF-8");
			String filterCondition = productList.stream()
	                .map(p -> "Product eq '" + p + "'")
	                .collect(Collectors.joining(" or "));

			// String APiFilter = "?$filter=SupplierInvoice eq '" + supplierinvoice + "'";

			String APiFilter = "?$filter=" + URLEncoder.encode(filterCondition, "UTF-8");


			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.PaymentAdviceForm.AcctDocItemCubeAPI + ApiFields+
			// APiFilter+ "&$format=json");
			HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port + "-"
					+ APIConstants.RouteCard.AProduct + APiFilter + "&$format=json");
			ObjectMapper objectMapper = new ObjectMapper();
			// Parse the JSON into a tree
			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");
			// Convert using readValue with TypeReference
			List<ProductPropertiesDAO> resultsList = objectMapper.readValue(resultsNode.toString(),
					new TypeReference<List<ProductPropertiesDAO>>() {
					});

			FinalList.addAll(resultsList);
		} catch (Exception e) {
			System.out.println("ApiParameters.getProductListAsync() - Error : " + e.getLocalizedMessage());
		}
		return FinalList;
	}
	
	
	
	@Description(value = "product API Consume")
	public List<ProductDescriptionPropertiesDAO> getProductDescriptionListAsync(List<String> productDescription) {

		ArrayList<ProductDescriptionPropertiesDAO> FinalList = new ArrayList<ProductDescriptionPropertiesDAO>();

		try {

			String filterCondition = productDescription.stream()
	                .map(p -> "Product eq '" + p + "'")
	                .collect(Collectors.joining(" or "));

			// String APiFilter = "?$filter=SupplierInvoice eq '" + supplierinvoice + "'";

			String APiFilter = "?$filter=" + URLEncoder.encode(filterCondition, "UTF-8");
			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.PaymentAdviceForm.AcctDocItemCubeAPI + ApiFields+
			// APiFilter+ "&$format=json");
			HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port + "-"
					+ APIConstants.RouteCard.AProductDescription+ APiFilter + "&$format=json");
			ObjectMapper objectMapper = new ObjectMapper();
			// Parse the JSON into a tree
			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");
			// Convert using readValue with TypeReference
			List<ProductDescriptionPropertiesDAO> resultsList = objectMapper.readValue(resultsNode.toString(),
					new TypeReference<List<ProductDescriptionPropertiesDAO>>() {
					});

			FinalList.addAll(resultsList);
		} catch (Exception e) {
			System.out.println("ApiParameters.getProductDescriptionListAsync() - Error : " + e.getLocalizedMessage());
		}
		return FinalList;
	}
	
	
	
	@Description(value = "product API Consume")
	public List<ProductValuationPropertiesDAO> getProductValuationAsync(List<String> productValuation) {

		ArrayList<ProductValuationPropertiesDAO> FinalList = new ArrayList<ProductValuationPropertiesDAO>();

		try {
			String filterCondition = productValuation.stream()
	                .map(p -> "Product eq '" + p + "'")
	                .collect(Collectors.joining(" or "));

			// String APiFilter = "?$filter=SupplierInvoice eq '" + supplierinvoice + "'";

			String APiFilter = "?$filter=" + URLEncoder.encode(filterCondition, "UTF-8");
			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.PaymentAdviceForm.AcctDocItemCubeAPI + ApiFields+
			// APiFilter+ "&$format=json");
			HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port + "-"
					+ APIConstants.MRINReport.AProductValuation + APiFilter + "&$format=json");
			ObjectMapper objectMapper = new ObjectMapper();
			// Parse the JSON into a tree
			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");
			// Convert using readValue with TypeReference
			List<ProductValuationPropertiesDAO> resultsList = objectMapper.readValue(resultsNode.toString(),
					new TypeReference<List<ProductValuationPropertiesDAO>>() {
					});

			FinalList.addAll(resultsList);
		} catch (Exception e) {
			System.out.println("ApiParameters.getProductValuationAsync() - Error : " + e.getLocalizedMessage());
		}
		return FinalList;
	}
	
	@Description(value = "YY1_Paymenttermstext API Consume")
	public List<PaymenttermstextPropertiesDAO> getPaymenttermstext(String purchaseOrder) {

		ArrayList<PaymenttermstextPropertiesDAO> FinalList = new ArrayList<PaymenttermstextPropertiesDAO>();

		try {

			// String APiFilter = "?$filter=SupplierInvoice eq '" + supplierinvoice + "'";

			String APiFilter = "?$filter=" + URLEncoder.encode("PurchaseOrder eq '" + purchaseOrder + "'", "UTF-8");

			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.PaymentAdviceForm.AcctDocItemCubeAPI + ApiFields+
			// APiFilter+ "&$format=json");
			HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port + "-"
					+ APIConstants.jobWork.YY1_Paymenttermstext + APiFilter + "&$format=json");
			ObjectMapper objectMapper = new ObjectMapper();
			// Parse the JSON into a tree
			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");
			// Convert using readValue with TypeReference
			List<PaymenttermstextPropertiesDAO> resultsList = objectMapper.readValue(resultsNode.toString(),
					new TypeReference<List<PaymenttermstextPropertiesDAO>>() {
					});

			FinalList.addAll(resultsList);
		} catch (Exception e) {
			System.out.println("ApiParameters.getPaymenttermstext() - Error : " + e.getLocalizedMessage());
		}
		return FinalList;
	}
	
	@Description(value = "puchaseNoteApi API Consume")
	public List<A_PurchaseOrderNoteDAO> puchaseNoteApi(String purchaseOrder) {

		ArrayList<A_PurchaseOrderNoteDAO> FinalList = new ArrayList<A_PurchaseOrderNoteDAO>();

		try {


			String filter = "PurchaseOrder eq '" + purchaseOrder + "'";
			String APiFilter = "?$filter=" + URLEncoder.encode(filter, "UTF-8");

			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.PaymentAdviceForm.AcctDocItemCubeAPI + ApiFields+
			// APiFilter+ "&$format=json");
			HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port + "-"
					+ APIConstants.debitNote.A_PurchaseOrderNote + APiFilter + "&$format=json");
			ObjectMapper objectMapper = new ObjectMapper();
			// Parse the JSON into a tree
			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");
			// Convert using readValue with TypeReference
			List<A_PurchaseOrderNoteDAO> resultsList = objectMapper.readValue(resultsNode.toString(),
					new TypeReference<List<A_PurchaseOrderNoteDAO>>() {
					});

//			resultsList.forEach(e -> {
//				e.setCreationDate(formatODataDate(e.getCreationDate()));
//			});

			FinalList.addAll(resultsList);
		} catch (Exception e) {
			System.out.println("ApiParameters.puchaseNoteApi() - Error : " + e.getLocalizedMessage());
		}
		return FinalList;
	}

}
