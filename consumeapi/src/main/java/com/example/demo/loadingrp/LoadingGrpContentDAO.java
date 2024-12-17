package com.example.demo.loadingrp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

@Data
@JsonIgnoreProperties("type")
public class LoadingGrpContentDAO {

	@JsonProperty(value="m:properties")
	private LoadingGrpPropertiesDAO properties;


}
