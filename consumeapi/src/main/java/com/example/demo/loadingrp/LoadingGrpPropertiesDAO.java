package com.example.demo.loadingrp;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoadingGrpPropertiesDAO {


	@JsonProperty("d:Description")
	private String dDescription;
	
	@JsonProperty("d:LGrp")
	private String dLGrp;


}
