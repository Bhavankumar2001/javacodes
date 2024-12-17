package com.example.demo.lotsizingprocedure;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
public class LotSizingController {

	@Autowired
	LotSizingService lotsizingservice;
	
	  @GetMapping("/getlotsizing")
	    public List<LotSizingPropertiesDAO> getMrpData() throws IOException, UnirestException {
	    	List<LotSizingPropertiesDAO>  loadinggrp = lotsizingservice.getlotsizing();

	        return loadinggrp; 
	    }
}
