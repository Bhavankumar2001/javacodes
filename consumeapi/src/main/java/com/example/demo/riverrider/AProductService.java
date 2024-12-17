package com.example.demo.riverrider;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.utils.APIConstants;
import com.example.demo.utils.UtilsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;



//@Service
@RestController
public class AProductService {

	@Autowired
	UtilsService Utils;
	
	@GetMapping(value = "/AProduct/filter")
	@Description(value = "Loading  AProduct Consume")
	public List<AProductPropertiesDAO> getAProduct()
			throws UnirestException, JsonMappingException, JsonProcessingException {

		HttpResponse<String> jsonresponse = Utils.ApiCall(
				"https://" + Utils.port + "-" + APIConstants.masterReport.A_Product, Utils.apiUserName,
				Utils.apiPassword);
	
		JsonNode getJsonValues = Utils.XmlToJsonConversion(jsonresponse.getBody());
		
		   // API call to fetch CSRF token
	    HttpResponse<String> tokenResponse = Utils.ApiCallWithHeaders(
	            "https://" + Utils.port + "-" + APIConstants.masterReport.A_Product, 
	            Utils.apiUserName, 
	            Utils.apiPassword);

	    String csrfToken = tokenResponse.getHeaders().getFirst("x-csrf-token");

	    System.out.println("CSRF Token: " + csrfToken);
		
		ObjectMapper objectMapper = new ObjectMapper();

		List<AProductDAO> entryNodesValues = objectMapper.reader()
				.forType(objectMapper.getTypeFactory().constructCollectionType(List.class, AProductDAO.class))
				.readValue(getJsonValues.toString());

		//A-productList
		List<AProductPropertiesDAO> customerList = entryNodesValues.stream().map(e -> e.getContent().getProperties())
				.collect(Collectors.toList());

		return customerList;
	}

	@GetMapping(value = "/AProduct_Description/filter")
	@Description(value = "Loading  AProduct_Description Consume")
	public List<AProductDescPropertiesDAO> getAProductDescription()
			throws UnirestException, JsonMappingException, JsonProcessingException {

		 
		HttpResponse<String> jsonresponse = Utils.ApiCall(
				"https://" + Utils.port + "-" + APIConstants.masterReport.A_ProductDescription, Utils.apiUserName,
				Utils.apiPassword);
		JsonNode getJsonValues = Utils.XmlToJsonConversion(jsonresponse.getBody());
		ObjectMapper objectMapper = new ObjectMapper();

		List<AProductDescDAO> entryNodesValues = objectMapper.reader()
				.forType(objectMapper.getTypeFactory().constructCollectionType(List.class, AProductDescDAO.class))
				.readValue(getJsonValues.toString());

		//Product Description API Details
		List<AProductDescPropertiesDAO> productdescList = entryNodesValues.stream()
				.map(e -> e.getContent().getProperties()).collect(Collectors.toList());
	
	    return productdescList;
	}
	
	
	@GetMapping(value = "/AProduct_Plant/filter")
	@Description(value = "Loading  A_ProductPlant Consume")
	public List<AProductPlantPropertiesDAO> getAProductPlantDetails()
			throws UnirestException, JsonMappingException, JsonProcessingException {

		HttpResponse<String> jsonresponse = Utils.ApiCall(
				"https://" + Utils.port + "-" + APIConstants.masterReport.A_ProductPlant, Utils.apiUserName,
				Utils.apiPassword);
		JsonNode getJsonValues = Utils.XmlToJsonConversion(jsonresponse.getBody());
	
		ObjectMapper objectMapper = new ObjectMapper();

		List<AProductPlantDAO> entryNodesValues = objectMapper.reader()
				.forType(objectMapper.getTypeFactory().constructCollectionType(List.class, AProductPlantDAO.class))
				.readValue(getJsonValues.toString());

		//A-product_PlantList
		List<AProductPlantPropertiesDAO> productplantList = entryNodesValues.stream().map(e -> e.getContent().getProperties())
				.collect(Collectors.toList());

		return productplantList;
	}
	
	
	@GetMapping(value = "/AProduct_DescriptionMerge/filter")
	@Description(value = "Logic for 1st 2nd Api Integration ")
	public List<ProductGroupMapping> getAProductdesc()
			throws UnirestException, JsonMappingException, JsonProcessingException {

		 
	
		List<AProductPropertiesDAO> aProductlist = getAProduct();
		List<AProductDescPropertiesDAO> aProductDescription = getAProductDescription();
		
		ArrayList<ProductGroupMapping> arrayList = new ArrayList<>();

	    for (AProductPropertiesDAO obj : aProductlist) {
	        // Filter the second API data to find matching products
	    	List<AProductDescPropertiesDAO>   descriptionsFilteredList = aProductDescription.stream()
	                .filter(e -> e.getDProduct().equalsIgnoreCase(obj.getDProduct()))
	                .collect(Collectors.toList());
	    	
	        // Map the product, productgroup, and matched descriptions
	        ProductGroupMapping mapping = new ProductGroupMapping();
	        mapping.setProduct(obj.getDProduct());
	        mapping.setProductgroup(obj.getDProductGroup());
			mapping.setProductdescription(descriptionsFilteredList.size() > 0 ? descriptionsFilteredList.get(0).getDProductDescription() : null);

			arrayList.add(mapping);
	    }
	    
//	    result.clear();
//	    
//	    result.addAll(arrayList);
//	    
	    
//	    for(ProductGroupMapping a : arrayList ) {
//	    	
//	    	if(!result.contains(a)) {
//	    		result.add(a);
//	    	}
//	    	
//	    }
//	    System.out.println(result.size());
//	 
	    return arrayList;
	}
	
	@GetMapping(value = "/Plant_Description/filter")
	public List<ProductPlantMapping> getAProductPlant()
			throws UnirestException, JsonMappingException, JsonProcessingException {


		List<ProductGroupMapping> dummy = getAProductdesc();
		List<AProductPlantPropertiesDAO> aProductPlantDetails = getAProductPlantDetails();
		
		 List<ProductPlantMapping> result2 = new ArrayList<>();
		for (ProductGroupMapping obj : dummy) {
			  List<AProductPlantPropertiesDAO> plantFilteredList = aProductPlantDetails.stream()
		                .filter(e -> e.getDProduct().equalsIgnoreCase(obj.getProduct()))
		                .collect(Collectors.toList());
			  
			  //plant list
			  List<Plant> plants = plantFilteredList.stream()
		                .map(plantObj -> {
		                    Plant plant = new Plant();
		                    plant.setPlantCode(plantObj.getDPlant());
		                    return plant;
		                })
		                .collect(Collectors.toList());
			  System.out.println("plants "+ plants);

				ProductPlantMapping plantmapping = new ProductPlantMapping();
				plantmapping.setProduct(obj.getProduct());
				plantmapping.setProductgroup(obj.getProductgroup());
				plantmapping.setProductdescription(obj.getProductdescription());
				plantmapping.setPlant(plants);
				result2.add(plantmapping);
		}
		System.out.println("ProductPlantMapping :"+  result2);
		
		return result2;
	}
	

	
	
	
}
