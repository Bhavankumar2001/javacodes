package com.example.demo.specialprocurement;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
public class SpecialProcurementController {
	@Autowired
	SpecialProcurementService specialprocurementservice;
	
	  @GetMapping("/getspecialprocurementtype")
	    public List<SpecialProcurementPropertiesDAO> getprocurementTypeData() throws IOException, UnirestException {
	    	List<SpecialProcurementPropertiesDAO>  loadinggrp = specialprocurementservice.getspecialprocurement();

	        return loadinggrp; 
	    }

}
