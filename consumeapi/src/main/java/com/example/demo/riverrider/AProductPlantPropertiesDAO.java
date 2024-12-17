package com.example.demo.riverrider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AProductPlantPropertiesDAO {

	@JsonProperty("d:Plant")
	private String dPlant;

	@JsonProperty("d:Product")
	private String dProduct;

}
