//package com.example.demo.stockdeterminationgroup;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.mashape.unirest.http.exceptions.UnirestException;
//
//@RestController
//public class StockdeterminationController {
//
//	@Autowired
//	StockdeterminationService  stockdeterminationservice;
//	
//	  @GetMapping("/getstockdetermination")
//	    public List<StockdeterminationDAO> getprocurementTypeData() throws IOException, UnirestException {
//	    	List<StockdeterminationDAO>  loadinggrp = stockdeterminationservice.getstockdetermination();
//
//	        return loadinggrp; 
//	    }
//}
