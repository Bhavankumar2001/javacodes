package com.example.demo.mrpcontroller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
public class MrpController {

	@Autowired
	MrpService mrpservice;
	
	  @GetMapping("/getmrpcontroller")
	    public List<MrpPropertiesDAO> getMrpData() throws IOException, UnirestException {
	    	List<MrpPropertiesDAO>  loadinggrp = mrpservice.getmrp();

	        return loadinggrp; 
	    }
}
