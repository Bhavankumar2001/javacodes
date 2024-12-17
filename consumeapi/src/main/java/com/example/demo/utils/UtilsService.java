package com.example.demo.utils;


import java.util.Map;

import org.json.JSONObject;
import org.json.XML;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class UtilsService {

	public String port="my413549";
	public String apiUserName="RIVERRIDER_INTEGRATION_USER";
	public String apiPassword="RiverRiderIntegration@2024_Devp";
	
	
	@Description(value = "Convert XML to Json Format !!")
	public JsonNode XmlToJsonConversion(String data) throws JsonMappingException, JsonProcessingException {
		JSONObject json = XML.toJSONObject(data);
		String jsonString2 = json.toString(4);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(jsonString2);
		JsonNode entryNodeArray = rootNode.path("feed").path("entry"); // .path("feed")
		return entryNodeArray;
	}
	@Description(value = "API Authentication")
	public HttpResponse<String> ApiCall(String apiUrl, String username, String password) throws UnirestException {
		HttpResponse<String> response = Unirest.get(apiUrl).basicAuth(username, password).asString();
		return response;
	}
	
	@Description(value = "API Token ")
	public HttpResponse<String> ApiCallWithHeaders(String url, String username, String password) throws UnirestException {
	    return Unirest.get(url)
	            .basicAuth(username, password)
	            .header("x-csrf-token", "fetch")
	            .asString();
	}


}
