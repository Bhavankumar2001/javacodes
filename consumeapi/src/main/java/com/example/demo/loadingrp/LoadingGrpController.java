
package com.example.demo.loadingrp;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.exceptions.UnirestException;




@RestController
public class LoadingGrpController {
	@Autowired
	LoadingGrpService grpservice;
	
	  @GetMapping("/getloadinggrp")
	    public List<LoadingGrpPropertiesDAO> getLoadingGrpData() throws IOException, UnirestException {
	    	List<LoadingGrpPropertiesDAO>  loadinggrp = grpservice.getloadinggrp();

	        return loadinggrp; 
	    }

}
