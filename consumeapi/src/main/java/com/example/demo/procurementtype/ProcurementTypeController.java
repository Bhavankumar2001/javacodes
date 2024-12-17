package com.example.demo.procurementtype;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
public class ProcurementTypeController {

	@Autowired
	ProcurementTypeService procurementservice;
	
	  @GetMapping("/getprocurementtype")
	    public List<ProcurementTypePropertiesDAO> getprocurementTypeData() throws IOException, UnirestException {
	    	List<ProcurementTypePropertiesDAO>  loadinggrp = procurementservice.getprocurementType();

	        return loadinggrp; 
	    }
}
