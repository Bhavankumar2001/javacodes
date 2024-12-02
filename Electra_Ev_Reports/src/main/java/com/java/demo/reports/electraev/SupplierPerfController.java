package com.java.demo.reports.electraev;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.misc.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
@RequestMapping("/electraev")
public class SupplierPerfController {
	@Autowired
	SupplierPerfService supplierperf;

	@GetMapping("/supplierperf")
	public String supplierData( Model model, @RequestParam(defaultValue = "") String fromDate,
			@RequestParam(defaultValue = "") String toDate) throws JsonMappingException, JsonProcessingException, UnirestException, ParseException {
		
		List<SupplierHeaderPropertiesDAO> fetchDataFromApi = supplierperf.fetchDataFromApi();

		List<SupplierHeaderPropertiesDAO> uniqueSuppliers = fetchDataFromApi.stream()
				.filter(properties -> properties.getDSupplier() != null && !properties.getDSupplier().isEmpty())
				.filter(properties -> properties.getDSupplierFullName() != null
						&& !properties.getDSupplierFullName().isEmpty())
				.collect(Collectors.toMap(SupplierHeaderPropertiesDAO::getDSupplier, // Key: dSupplier
						properties -> properties, // Value: Full properties object
						(existing, replacement) -> existing // In case of key collision, keep the existing entry
				)).values().stream().collect(Collectors.toList());

		// Print unique suppliers
		uniqueSuppliers.forEach(
				supplier -> System.out.println(supplier.getDSupplier() + " - " + supplier.getDSupplierFullName()));

	
		model.addAttribute("fromDate", fromDate);
		model.addAttribute("toDate", toDate);
		//model.addAttribute("data", fetchDataFromApi);
		model.addAttribute("uniqueSupplier", uniqueSuppliers);
		return "PMRReport";
	}

	@PostMapping("/supplierperf/filter")
	public String getSupplierData(Model model, @RequestParam(defaultValue = "") String fromDate,
			@RequestParam(defaultValue = "") String toDate, @RequestParam(defaultValue = "") String supplierField)
			throws UnirestException, JsonMappingException, JsonProcessingException, ParseException {
		
		System.err.println("supplierField :"+supplierField);

		List<SupplierHeaderPropertiesDAO> fetchDataFromApi = supplierperf.fetchDataFromApi(fromDate, toDate,supplierField);

		
		List<SupplierHeaderPropertiesDAO> fetchDataFrom = supplierperf.fetchDataFromApi();

		List<SupplierHeaderPropertiesDAO> uniqueSuppliers = fetchDataFrom.stream()
				.filter(properties -> properties.getDSupplier() != null && !properties.getDSupplier().isEmpty())
				.filter(properties -> properties.getDSupplierFullName() != null
						&& !properties.getDSupplierFullName().isEmpty())
				.collect(Collectors.toMap(SupplierHeaderPropertiesDAO::getDSupplier, // Key: dSupplier
						properties -> properties, // Value: Full properties object
						(existing, replacement) -> existing // In case of key collision, keep the existing entry
				)).values().stream().collect(Collectors.toList());
		// Print unique suppliers
//		uniqueSuppliers.forEach(
//				supplier -> System.out.println(supplier.getDSupplier() + " - " + supplier.getDSupplierFullName()));

		model.addAttribute("fromDate", fromDate);
		model.addAttribute("toDate", toDate);
		model.addAttribute("supplierField", supplierField);
		model.addAttribute("data", fetchDataFromApi);
		model.addAttribute("uniqueSupplier", uniqueSuppliers);

		return "PMRReport";
	}

}
