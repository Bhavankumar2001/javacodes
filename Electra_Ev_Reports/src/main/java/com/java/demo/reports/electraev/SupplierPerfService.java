package com.java.demo.reports.electraev;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.demo.reports.electraev.utils.UtilsService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;



@Service
public class SupplierPerfService {

	@Autowired
	UtilsService Utils;
	
	public List<SupplierHeaderPropertiesDAO> fetchDataFromApi()
			throws UnirestException, JsonMappingException, JsonProcessingException, ParseException {
		String url = "https://my403519-api.s4hana.cloud.sap/sap/opu/odata/sap/YY1_SUPPLIER_PERF_API_CDS/YY1_Supplier_Perf_API";
		String username = "CUSTOM_API";
		String password = "EZdPo~mxpd7uylaCiiNXeGiBRDWdZCVijctKDPBy";

		

		
		HttpResponse<String> response = Unirest.get(url).basicAuth(username, password).asString();

//		System.out.println(response.getBody());
		JsonNode jsonValue = jsonValue(response.getBody());

		// Map JSON to Java object
		ObjectMapper objectMapper = new ObjectMapper();
		List<SupplierPerfHeaderDAO> getJsnonNodeValues = objectMapper.reader()
				.forType(objectMapper.getTypeFactory().constructCollectionType(List.class, SupplierPerfHeaderDAO.class))
				.readValue(jsonValue.toString());

//		System.out.println(getJsnonNodeValues);
		List<SupplierHeaderPropertiesDAO> listdata = getJsnonNodeValues.stream().map(e -> e.content.properties)
				.collect(Collectors.toList());


		
		// Filter the list for DGoodsMovementType "350"
		List<SupplierHeaderPropertiesDAO> debitCreditCode350S = listdata.stream()
				.filter(e -> "S".equals(e.getDDebitCreditCode()) && "350".equals(e.getDGoodsMovementType()))
				.collect(Collectors.toList());

		// Group by dSupplier and dProductName and sum dQuantityInEntryUnit
		Map<String, Double> supplierProductToTotalQuantityMap = debitCreditCode350S.stream()
				.collect(Collectors.groupingBy(e -> e.getDSupplier() + "|" + e.getDProductName(),
						Collectors.summingDouble(SupplierHeaderPropertiesDAO::getDQuantityInEntryUnit)));
//		System.out.println(supplierProductToTotalQuantityMap);

		// Update unitentry350 for each supplier and product combination
		debitCreditCode350S.forEach(supplier -> {
			String key = supplier.getDSupplier() + "|" + supplier.getDProductName();
			double totalQuantity = supplierProductToTotalQuantityMap.getOrDefault(key, 0.0);
			supplier.setUnitentry350(totalQuantity);
		});
//		System.out.println(debitCreditCode350S);
		// Filter the list for DGoodsMovementType "321"
		List<SupplierHeaderPropertiesDAO> debitCreditCode321S = listdata.stream()
				.filter(e -> "S".equals(e.getDDebitCreditCode()) && "321".equals(e.getDGoodsMovementType()))
				.collect(Collectors.toList());

		// Group by dSupplier and dProductName and sum dQuantityInEntryUnit
		Map<String, Double> supplierProductToTotalQuantityMap321 = debitCreditCode321S.stream()
				.collect(Collectors.groupingBy(e -> e.getDSupplier() + "|" + e.getDProductName(),
						Collectors.summingDouble(SupplierHeaderPropertiesDAO::getDQuantityInEntryUnit)));

		// Update unitentry321 for each supplier and product combination
		debitCreditCode321S.forEach(supplier -> {
			String key = supplier.getDSupplier() + "|" + supplier.getDProductName();
			double totalQuantity = supplierProductToTotalQuantityMap321.getOrDefault(key, 0.0);
			supplier.setUnitentry321(totalQuantity);
		});

		// Combine the lists
		List<SupplierHeaderPropertiesDAO> combinedList = new ArrayList<>(debitCreditCode350S);
		combinedList.addAll(debitCreditCode321S);
//		System.out.println(combinedList);
		// Create a map to remove duplicates and update fields
		Map<String, SupplierHeaderPropertiesDAO> supplierProductMap = new HashMap<>();

		for (SupplierHeaderPropertiesDAO item : combinedList) {
			String key = item.getDSupplier() + ":" + item.getDProductName();
			if (supplierProductMap.containsKey(key)) {
				SupplierHeaderPropertiesDAO existing = supplierProductMap.get(key);
				existing.setUnitentry321(item.getUnitentry321()); // Combine or update fields as needed

			} else {
				supplierProductMap.put(key, item);
			}
		}
//		System.out.println(combinedList);
//		System.out.println(supplierProductMap);

		// Convert map values to a list
		List<SupplierHeaderPropertiesDAO> uniqueSuppliers = new ArrayList<>(supplierProductMap.values());

//		System.out.println(uniqueSuppliers);
		// Update the dSupplierFullName field
		for (SupplierHeaderPropertiesDAO supplier : uniqueSuppliers) {
			String fullName = supplier.getDSupplierFullName();

			Double value1 = supplier.getUnitentry321();
			Double value2 = supplier.getUnitentry350();
			Double Totalreceived = value1 != null ? value1 : 0;
			Double rejection = value2 != null ? value2 : 0;
			// Use 0 if values are null
			Double sumation = Totalreceived + rejection;
			supplier.setSumation(sumation);

			Double qualityrating = (Totalreceived - rejection) * 100 / Totalreceived;
			System.out.println(qualityrating.isInfinite());
			supplier.setQualityrating(qualityrating.isInfinite() ? 0 : qualityrating);

			Double deliveryrating = Totalreceived * 100 / sumation;
			supplier.setDeliveryrating(deliveryrating.isInfinite() ? 0 : deliveryrating);

			if (fullName != null && fullName.contains("/")) {
				String[] parts = fullName.split("/");
				String updatedFullName = parts[0].trim();
				supplier.setDSupplierFullName(updatedFullName);
			}
		}

		// Print the updated uniqueSuppliers list
		System.out.println(uniqueSuppliers);

//		
//	    // Convert DebitcreditCodeS to JSON
//        ObjectMapper objectMapper1 = new ObjectMapper();
//        String jsonString = objectMapper1.writeValueAsString(DebitcreditCodeS);
//
//        // Print JSON
//        System.out.println("DebitcreditCodeS in JSON format:");
//        System.out.println(jsonString);
		return uniqueSuppliers;
	}
	
	
	
	

//	public List<SupplierHeaderPropertiesDAO> fetchDataFromApi(String fromDate, String toDate,String supplierField)
//			throws UnirestException, JsonMappingException, JsonProcessingException, ParseException {
//		String url = "https://my403519-api.s4hana.cloud.sap/sap/opu/odata/sap/YY1_SUPPLIER_PERF_API_CDS/YY1_Supplier_Perf_API";
//		String username = "CUSTOM_API";
//		String password = "EZdPo~mxpd7uylaCiiNXeGiBRDWdZCVijctKDPBy";
//
//		Date startOfMonth = Utils.convertToStartOfMonth(fromDate);
//		Date endOfMonth = Utils.getEndOfMonth(toDate);
//		List<String> supplierList = Utils.getStringListSepratedByComma(supplierField);
//
//		System.out.println(startOfMonth);
//
//		HttpResponse<String> response = Unirest.get(url).basicAuth(username, password).asString();
//
////		System.out.println(response.getBody());
//		JsonNode jsonValue = jsonValue(response.getBody());
//
//		// Map JSON to Java object
//		ObjectMapper objectMapper = new ObjectMapper();
//		List<SupplierPerfHeaderDAO> getJsnonNodeValues = objectMapper.reader()
//				.forType(objectMapper.getTypeFactory().constructCollectionType(List.class, SupplierPerfHeaderDAO.class))
//				.readValue(jsonValue.toString());
//
////		System.out.println(getJsnonNodeValues);
//		List<SupplierHeaderPropertiesDAO> listdata = getJsnonNodeValues.stream().map(e -> e.content.properties)
//				.collect(Collectors.toList());
//
//		// Datewise Filteration
//		List<SupplierHeaderPropertiesDAO> supplierHeaderPropertiesDAOs = new ArrayList<>();
//		if (fromDate.length() != 0 && toDate.length() != 0) {
////			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
////			Date startDate = dateFormat.parse(fromDate);
////			Date endDate = dateFormat.parse(Utils.AppendPlusOneDayToDate(toDate));
//
//			supplierHeaderPropertiesDAOs = listdata.stream()
//					.filter(obj -> obj.getDPostingDate().after(startOfMonth) && obj.getDPostingDate().before(endOfMonth)) 
//					.collect(Collectors.toList());
//		System.err.println(" supplierHeaderPropertiesDAOs1111 : "+supplierHeaderPropertiesDAOs);
//		} else {
//			//System.err.println(" listdata :"+listdata);
//			supplierHeaderPropertiesDAOs = listdata;
//		}
//		
//
//
//		if (supplierList != null && supplierList.size() > 0) {
//			supplierHeaderPropertiesDAOs = supplierHeaderPropertiesDAOs.stream().filter(e->supplierList.contains(e.getDSupplier())).collect(Collectors.toList());	
//					}
//		System.err.println("supplierHeaderPropertiesDAOs :"+supplierHeaderPropertiesDAOs);
//		
//		Map<String , SupplierHeaderPropertiesDAO> uniquefiltered = new HashMap<>();
//		for (SupplierHeaderPropertiesDAO supplier : supplierHeaderPropertiesDAOs) {
//			uniquefiltered.put(supplier.getDSupplier(), supplier);
//		}
//		
//		System.out.println(uniquefiltered);
//		
//		
//		   // Convert the HashMap values back to a list
//        List<SupplierHeaderPropertiesDAO> uniqueSuppliersfield = uniquefiltered.values().stream()
//                .collect(Collectors.toList());
//
//        System.err.println("uniqueSuppliers: " + uniqueSuppliersfield);
//		
//		// Filter the list for DGoodsMovementType "350"
//		List<SupplierHeaderPropertiesDAO> debitCreditCode350S = supplierHeaderPropertiesDAOs.stream()
//				.filter(e -> "S".equals(e.getDDebitCreditCode()) && "350".equals(e.getDGoodsMovementType()))
//				.collect(Collectors.toList());
//
//		// Group by dSupplier and dProductName and sum dQuantityInEntryUnit
//		Map<String, Double> supplierProductToTotalQuantityMap = debitCreditCode350S.stream()
//				.collect(Collectors.groupingBy(e -> e.getDSupplier() + "|" + e.getDProductName(),
//						Collectors.summingDouble(SupplierHeaderPropertiesDAO::getDQuantityInEntryUnit)));
////		System.out.println(supplierProductToTotalQuantityMap);
//
//		// Update unitentry350 for each supplier and product combination
//		debitCreditCode350S.forEach(supplier -> {
//			String key = supplier.getDSupplier() + "|" + supplier.getDProductName();
//			double totalQuantity = supplierProductToTotalQuantityMap.getOrDefault(key, 0.0);
//			supplier.setUnitentry350(totalQuantity);
//		});
//		System.out.println(debitCreditCode350S);
//		// Filter the list for DGoodsMovementType "321"
//		List<SupplierHeaderPropertiesDAO> debitCreditCode321S = supplierHeaderPropertiesDAOs.stream()
//				.filter(e -> "S".equals(e.getDDebitCreditCode()) && "321".equals(e.getDGoodsMovementType()))
//				.collect(Collectors.toList());
//
//		// Group by dSupplier and dProductName and sum dQuantityInEntryUnit
//		Map<String, Double> supplierProductToTotalQuantityMap321 = debitCreditCode321S.stream()
//				.collect(Collectors.groupingBy(e -> e.getDSupplier() + "|" + e.getDProductName(),
//						Collectors.summingDouble(SupplierHeaderPropertiesDAO::getDQuantityInEntryUnit)));
//
//		// Update unitentry321 for each supplier and product combination
//		debitCreditCode321S.forEach(supplier -> {
//			String key = supplier.getDSupplier() + "|" + supplier.getDProductName();
//			double totalQuantity = supplierProductToTotalQuantityMap321.getOrDefault(key, 0.0);
//			supplier.setUnitentry321(totalQuantity);
//		});
//
//		// Combine the lists
//		List<SupplierHeaderPropertiesDAO> combinedList = new ArrayList<>(debitCreditCode350S);
//		combinedList.addAll(debitCreditCode321S);
////		System.out.println(combinedList);
//		// Create a map to remove duplicates and update fields
//		Map<String, SupplierHeaderPropertiesDAO> supplierProductMap = new HashMap<>();
//
//		for (SupplierHeaderPropertiesDAO item : combinedList) {
//			String key = item.getDSupplier() + ":" + item.getDProductName();
//			if (supplierProductMap.containsKey(key)) {
//				SupplierHeaderPropertiesDAO existing = supplierProductMap.get(key);
//				existing.setUnitentry321(item.getUnitentry321()); // Combine or update fields as needed
//
//			} else {
//				supplierProductMap.put(key, item);
//			}
//		}
//		//System.out.println(combinedList);
//		//System.out.println(supplierProductMap);
//
//		// Convert map values to a list
//		List<SupplierHeaderPropertiesDAO> uniqueSuppliers = new ArrayList<>(supplierProductMap.values());
//
////		System.out.println(uniqueSuppliers);
//		// Update the dSupplierFullName field
//		for (SupplierHeaderPropertiesDAO supplier : uniqueSuppliers) {
//			String fullName = supplier.getDSupplierFullName();
//
//			Double value1 = supplier.getUnitentry321();
//			Double value2 = supplier.getUnitentry350();
//			Double Totalreceived = value1 != null ? value1 : 0;
//			Double rejection = value2 != null ? value2 : 0;
//			// Use 0 if values are null
//			Double sumation = Totalreceived + rejection;
//			supplier.setSumation(sumation);
//
//			Double qualityrating = (Totalreceived - rejection) * 100 / Totalreceived;
//			System.out.println(qualityrating.isInfinite());
//			supplier.setQualityrating(qualityrating.isInfinite() ? 0 : qualityrating);
//
//			Double deliveryrating = Totalreceived * 100 / sumation;
//			supplier.setDeliveryrating(deliveryrating.isInfinite() ? 0 : deliveryrating);
//
//			if (fullName != null && fullName.contains("/")) {
//				String[] parts = fullName.split("/");
//				String updatedFullName = parts[0].trim();
//				supplier.setDSupplierFullName(updatedFullName);
//			}
//		}
//
//		// Print the updated uniqueSuppliers list
//	//	System.out.println(uniqueSuppliers);
//
////		
////	    // Convert DebitcreditCodeS to JSON
////        ObjectMapper objectMapper1 = new ObjectMapper();
////        String jsonString = objectMapper1.writeValueAsString(DebitcreditCodeS);
////
////        // Print JSON
////        System.out.println("DebitcreditCodeS in JSON format:");
////        System.out.println(jsonString);
//		return uniqueSuppliersfield;
//	}
	
	
	public List<SupplierHeaderPropertiesDAO> fetchDataFromApi(String fromDate, String toDate, String supplierField)
	        throws UnirestException, JsonMappingException, JsonProcessingException, ParseException {
	    
	    String url = "https://my403519-api.s4hana.cloud.sap/sap/opu/odata/sap/YY1_SUPPLIER_PERF_API_CDS/YY1_Supplier_Perf_API";
	    String username = "CUSTOM_API";
	    String password = "EZdPo~mxpd7uylaCiiNXeGiBRDWdZCVijctKDPBy";

	    Date startOfMonth = fromDate != null && !fromDate.isEmpty() ? Utils.convertToStartOfMonth(fromDate) : null;
	    Date endOfMonth = toDate != null && !toDate.isEmpty() ? Utils.getEndOfMonth(toDate) : null;
	    List<String> supplierList = Utils.getStringListSepratedByComma(supplierField);

	    HttpResponse<String> response = Unirest.get(url).basicAuth(username, password).asString();
	    JsonNode jsonValue = jsonValue(response.getBody());

	    // Map JSON to Java object
	    ObjectMapper objectMapper = new ObjectMapper();
	    List<SupplierPerfHeaderDAO> getJsnonNodeValues = objectMapper.reader()
	            .forType(objectMapper.getTypeFactory().constructCollectionType(List.class, SupplierPerfHeaderDAO.class))
	            .readValue(jsonValue.toString());

	    List<SupplierHeaderPropertiesDAO> listdata = getJsnonNodeValues.stream()
	            .map(e -> e.content.properties)
	            .collect(Collectors.toList());

	    // Apply date-based filtering if both fromDate and toDate are provided
	    List<SupplierHeaderPropertiesDAO> supplierHeaderPropertiesDAOs = new ArrayList<>();
	    if (startOfMonth != null && endOfMonth != null) {
	        supplierHeaderPropertiesDAOs = listdata.stream()
	                .filter(obj -> obj.getDPostingDate().after(startOfMonth) && obj.getDPostingDate().before(endOfMonth))
	                .collect(Collectors.toList());
	    } else {
	        supplierHeaderPropertiesDAOs = listdata;
	    }

	    // Apply supplier-based filtering if supplierList is provided
	    if (supplierList != null && !supplierList.isEmpty()) {
	        supplierHeaderPropertiesDAOs = supplierHeaderPropertiesDAOs.stream()
	                .filter(e -> supplierList.contains(e.getDSupplier()))
	                .collect(Collectors.toList());
	    }

	    // Remove duplicates based on dSupplier
	    Map<String, SupplierHeaderPropertiesDAO> uniqueFiltered = new HashMap<>();
	    for (SupplierHeaderPropertiesDAO supplier : supplierHeaderPropertiesDAOs) {
	        uniqueFiltered.put(supplier.getDSupplier(), supplier);
	    }

	    // Convert the HashMap values back to a list
	    List<SupplierHeaderPropertiesDAO> uniqueSuppliersfield = new ArrayList<>(uniqueFiltered.values());

	    // Filter the list for DGoodsMovementType "350" and "321" and perform aggregation
	    List<SupplierHeaderPropertiesDAO> debitCreditCode350S = filterAndAggregateByGoodsMovementType(supplierHeaderPropertiesDAOs, "350");
	    List<SupplierHeaderPropertiesDAO> debitCreditCode321S = filterAndAggregateByGoodsMovementType(supplierHeaderPropertiesDAOs, "321");

	    // Combine the lists and handle duplicates
	    List<SupplierHeaderPropertiesDAO> combinedList = new ArrayList<>(debitCreditCode350S);
	    combinedList.addAll(debitCreditCode321S);

	    // Create a map to remove duplicates and update fields
	    Map<String, SupplierHeaderPropertiesDAO> supplierProductMap = new HashMap<>();
	    for (SupplierHeaderPropertiesDAO item : combinedList) {
	        String key = item.getDSupplier() + ":" + item.getDProductName();
	        if (supplierProductMap.containsKey(key)) {
	            SupplierHeaderPropertiesDAO existing = supplierProductMap.get(key);
	            existing.setUnitentry321(item.getUnitentry321()); // Combine or update fields as needed
	        } else {
	            supplierProductMap.put(key, item);
	        }
	    }

	    // Convert map values to a list
	    List<SupplierHeaderPropertiesDAO> uniqueSuppliers = new ArrayList<>(supplierProductMap.values());

	    // Update the dSupplierFullName field
	    for (SupplierHeaderPropertiesDAO supplier : uniqueSuppliers) {
	        updateSupplierFields(supplier);
	    }

	    return uniqueSuppliers;
	}

	public List<SupplierHeaderPropertiesDAO> filterAndAggregateByGoodsMovementType(List<SupplierHeaderPropertiesDAO> listData, String goodsMovementType) {
	    List<SupplierHeaderPropertiesDAO> filteredList = listData.stream()
	            .filter(e -> "S".equals(e.getDDebitCreditCode()) && goodsMovementType.equals(e.getDGoodsMovementType()))
	            .collect(Collectors.toList());

	    Map<String, Double> supplierProductToTotalQuantityMap = filteredList.stream()
	            .collect(Collectors.groupingBy(e -> e.getDSupplier() + "|" + e.getDProductName(),
	                    Collectors.summingDouble(SupplierHeaderPropertiesDAO::getDQuantityInEntryUnit)));

	    filteredList.forEach(supplier -> {
	        String key = supplier.getDSupplier() + "|" + supplier.getDProductName();
	        double totalQuantity = supplierProductToTotalQuantityMap.getOrDefault(key, 0.0);
	        if (goodsMovementType.equals("350")) {
	            supplier.setUnitentry350(totalQuantity);
	        } else if (goodsMovementType.equals("321")) {
	            supplier.setUnitentry321(totalQuantity);
	        }
	    });

	    return filteredList;
	}

	public void updateSupplierFields(SupplierHeaderPropertiesDAO supplier) {
	    String fullName = supplier.getDSupplierFullName();

	    Double value1 = supplier.getUnitentry321();
	    Double value2 = supplier.getUnitentry350();
	    Double Totalreceived = value1 != null ? value1 : 0;
	    Double rejection = value2 != null ? value2 : 0;

	    // Use 0 if values are null
	    Double sumation = Totalreceived + rejection;
	    supplier.setSumation(sumation);

	    Double qualityrating = (Totalreceived - rejection) * 100 / Totalreceived;
	    supplier.setQualityrating(qualityrating.isInfinite() ? 0 : qualityrating);

	    Double deliveryrating = Totalreceived * 100 / sumation;
	    supplier.setDeliveryrating(deliveryrating.isInfinite() ? 0 : deliveryrating);

	    if (fullName != null && fullName.contains("/")) {
	        String[] parts = fullName.split("/");
	        String updatedFullName = parts[0].trim();
	        supplier.setDSupplierFullName(updatedFullName);
	    }
	}


	private JsonNode jsonValue(String body) throws JsonMappingException, JsonProcessingException {
		JSONObject jsonObject = XML.toJSONObject(body);
		String jsonValue = jsonObject.toString(4);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(jsonValue);
		JsonNode entryNodeArray = rootNode.path("feed").path("entry");

		return entryNodeArray;
	}

}
