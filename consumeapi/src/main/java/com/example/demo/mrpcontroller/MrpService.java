package com.example.demo.mrpcontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import com.example.demo.utils.APIConstants;
import com.example.demo.utils.UtilsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class MrpService {

	@Autowired
	UtilsService Utils;
	
	// @GetMapping(value="/vkc/filter")
		@Description(value = "Loading  API Consume")
		public List<MrpPropertiesDAO> getmrp()
				throws UnirestException, JsonMappingException, JsonProcessingException {

			HttpResponse<String> jsonresponse = Utils.ApiCall(
					"https://" + Utils.port + "-" + APIConstants.masterReport.YY1_MRPCONTROLLER,
					Utils.apiUserName, Utils.apiPassword);
			JsonNode getJsonValues = Utils.XmlToJsonConversion(jsonresponse.getBody());
			ObjectMapper objectMapper = new ObjectMapper();

			List<MrpDAO> entryNodesValues = objectMapper.reader()
					.forType(objectMapper.getTypeFactory().constructCollectionType(List.class,MrpDAO.class))
					.readValue(getJsonValues.toString());

			List<MrpPropertiesDAO> customerList = entryNodesValues.stream()
					.map(e -> e.getContent().getProperties()).collect(Collectors.toList());

			return customerList;
		}

}
