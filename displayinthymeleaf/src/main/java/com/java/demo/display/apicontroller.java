package com.java.demo.display;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import net.sf.jasperreports.engine.JRException;



@RestController
public class apicontroller {

    @Autowired
    private  apiservice apserv;
    @GetMapping("/zcogs")
    public List<ZCOGSPropertiesDAO> getZcogsData() throws IOException, UnirestException, JRException {
    	List<ZCOGSPropertiesDAO>  zcogs = apserv.fetchDataFromAPI();
//       model.addAttribute("data", zcogs);
        return zcogs; // Return Thymeleaf template name
    }
    
    @GetMapping("/zcogsMto")
    public List<ZCOGSMtoPropertiesDAO> getZcogsMtoData() throws IOException, UnirestException {
    	List<ZCOGSMtoPropertiesDAO>  zcogs = apserv.fetchDataMtoFromAPI();
     
        return zcogs; // Return Thymeleaf template name
    }
    
    @GetMapping("/common")
    public List<ZCOGSMtoPropertiesDAO> getCommonKeyValues() throws IOException, UnirestException, JRException {
        return apserv.findCommonKeyValues();
    }
}
