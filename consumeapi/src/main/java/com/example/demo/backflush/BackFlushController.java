package com.example.demo.backflush;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
public class BackFlushController {

	@Autowired
	BackFlushService backflushservice;
	
	  @GetMapping("/getbackflush")
	    public List<BackFlushPropertiesDAO> getbackflushData() throws IOException, UnirestException {
	    	List<BackFlushPropertiesDAO>  loadinggrp = backflushservice.getbackflush();

	        return loadinggrp; 
	    }
}
