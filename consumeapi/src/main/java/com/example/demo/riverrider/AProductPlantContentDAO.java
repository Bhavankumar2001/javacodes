package com.example.demo.riverrider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties("type")
public class AProductPlantContentDAO {
	
	
	@JsonProperty(value="m:properties")
	private AProductPlantPropertiesDAO properties;

}
