package com.java.demo.display;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.internal.build.AllowSysOut;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class apiservice {
	@Autowired

	public List<ZCOGSPropertiesDAO> fetchDataFromAPI()
			throws UnirestException, JsonMappingException, JsonProcessingException, JRException {
		String url = "https://my410508-api.s4hana.cloud.sap/sap/opu/odata/sap/YY1_ZCOGS_CDS/YY1_ZCOGS"; // Replace with
		// your API URL

		String username = "YY1_GSTREPORT"; // Replace with your username
		String password = "hfb]DCSKP9pPNuGpjeKxyMwZxRHPVCGHnjgjEztE"; // Replace with your password

		HttpResponse<String> response = Unirest.get(url).basicAuth(username, password).asString();
//		System.out.println(response.getBody());
		JsonNode jsonValue = jsonValue(response.getBody());

//		System.out.println(jsonValue);
		// Map JSON to Java object
		ObjectMapper objectMapper = new ObjectMapper();
		List<ZcogsDAO> properties = objectMapper.reader()
				.forType(objectMapper.getTypeFactory().constructCollectionType(List.class, ZcogsDAO.class))
				.readValue(jsonValue.toString());

//		System.out.println(properties);

		List<ZCOGSPropertiesDAO> listdata = properties.stream().map(e -> e.content.properties)
				.collect(Collectors.toList());
		
System.out.println(listdata);

		String filepath = "C:\\Users\\user\\OneDrive - Altrocks Tech Private Limited\\displayinthymeleaf\\src\\main\\resources\\report_template.jrxml";

		// Convert data to JRBeanCollectionDataSource
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listdata);

		// Parameters map (you may add additional parameters if required)
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("Firstname", "Bhavan");
		parameters.put("Dob", "8/6/2001");
		parameters.put("Jasper", dataSource);

		JasperReport report = JasperCompileManager.compileReport(filepath);
		JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

		JasperExportManager.exportReportToPdfFile(print,
				"C:\\Users\\user\\OneDrive - Altrocks Tech Private Limited\\displayinthymeleaf\\src\\main\\resources\\Practice.pdf");
//	        return JasperExportManager.exportReportToPdf(print);
//		System.out.println(listdata);
		return listdata;

	}

	public List<ZCOGSMtoPropertiesDAO> fetchDataMtoFromAPI()
			throws UnirestException, JsonMappingException, JsonProcessingException {
		String billingAPI = "https://my410508-api.s4hana.cloud.sap/sap/opu/odata/sap/YY1_COGSMTOBILLING_CDS/YY1_COGSMTOBILLING";
		String username = "YY1_GSTREPORT"; // Replace with your username
		String password = "hfb]DCSKP9pPNuGpjeKxyMwZxRHPVCGHnjgjEztE"; // Replace with your password

		HttpResponse<String> response = Unirest.get(billingAPI).basicAuth(username, password).asString();
		System.out.println(response.getBody());
		JsonNode jsonValue = jsonMtoValue(response.getBody());

//		System.out.println(jsonValue);
		// Map JSON to Java object
		ObjectMapper objectMapper = new ObjectMapper();
		List<ZcogsMtoDAO> properties1 = objectMapper.reader()
				.forType(objectMapper.getTypeFactory().constructCollectionType(List.class, ZcogsMtoDAO.class))
				.readValue(jsonValue.toString());

//		System.out.println(properties);

		List<ZCOGSMtoPropertiesDAO> listdata1 = properties1.stream().map(e -> e.content.properties)
				.collect(Collectors.toList());
//		System.out.println(listdata1);
		return listdata1;

	}

	public List<ZCOGSMtoPropertiesDAO> findCommonKeyValues()
			throws JsonMappingException, JsonProcessingException, UnirestException, JRException {
		List<ZCOGSPropertiesDAO> dataFromFirstAPI = fetchDataFromAPI();
		List<ZCOGSMtoPropertiesDAO> dataFromSecondAPI = fetchDataMtoFromAPI();
		System.out.println(dataFromFirstAPI.size());

		for (ZCOGSPropertiesDAO obj : dataFromFirstAPI) {

			List<ZCOGSMtoPropertiesDAO> secondAPIFilteredList = dataFromSecondAPI.stream()
					.filter(e -> e.getBillingDocument().equalsIgnoreCase(obj.getBillingDocument()))
					.collect(Collectors.toList());

//			obj.setSecondApiValue(secondAPIFilteredList.size() > 0 ? secondAPIFilteredList.get(0).getGrNumber() : null);
//			if(secondAPIFilteredList.size() > 0) {
//				obj.setSecondApiValue(secondAPIFilteredList.get(0).getGrNumber());
//			}

		}

		System.out.println(dataFromSecondAPI.size());
		// Filter and collect common entries based on billingDocument field
		List<ZCOGSMtoPropertiesDAO> commonValues = dataFromSecondAPI.stream()
				.filter(second -> dataFromFirstAPI.stream()
						.anyMatch(first -> first.getBillingDocument().equals(second.getBillingDocument())))
				.distinct().collect(Collectors.toList());
		System.out.println(commonValues.size());
		return commonValues;

//		  Set<ZCOGSMtoPropertiesDAO> uniqueBillingNumbers = new HashSet<>();
//		List<ZCOGSMtoPropertiesDAO> resultList = new ArrayList<>();
//
//		// Iterate through both lists and compare based on billingnumber
//		for (ZCOGSMtoPropertiesDAO item1 : dataFromSecondAPI) {
//			for (ZCOGSPropertiesDAO item2 : dataFromFirstAPI) {
//				if (item1.getBillingDocument().equals(item2.getBillingDocument())) {
//					// Add to result list or perform further operations
//
//					resultList.add(item1);
//
//                	 uniqueBillingNumbers.add(item1);
//					
//				}
//			}
//		}
//		System.out.println(resultList.size());
//        System.out.println(uniqueBillingNumbers.size());
//
//		return resultList;
	}

//convert xml to json
	private JsonNode jsonValue(String body) throws JsonMappingException, JsonProcessingException {
		JSONObject jsonObject = XML.toJSONObject(body);
		String jsonValue = jsonObject.toString(4);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(jsonValue);
		System.out.println(rootNode);
		JsonNode entryNodeArray = rootNode.path("feed").path("entry");
//		System.out.println(entryNodeArray);

		return entryNodeArray;
	}

	private JsonNode jsonMtoValue(String body) throws JsonMappingException, JsonProcessingException {
		JSONObject jsonObject = XML.toJSONObject(body);
		String jsonValue = jsonObject.toString(4);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(jsonValue);
		JsonNode entryNodeArray = rootNode.path("feed").path("entry");

		return entryNodeArray;
	}

}
