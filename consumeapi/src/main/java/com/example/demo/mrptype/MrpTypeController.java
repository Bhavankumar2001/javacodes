package com.example.demo.mrptype;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
public class MrpTypeController {

	@Autowired
	MrpTypeService mrpservice;
	
	  @GetMapping("/getmrp")
	    public List<MrpTypePropertiesDAO> getMrpTypeData() throws IOException, UnirestException {
	    	List<MrpTypePropertiesDAO>  loadinggrp = mrpservice.getmrpType();

	        return loadinggrp; 
	    }

}
