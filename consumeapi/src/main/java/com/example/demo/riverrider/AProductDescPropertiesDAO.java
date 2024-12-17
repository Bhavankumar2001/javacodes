package com.example.demo.riverrider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AProductDescPropertiesDAO {

	@JsonProperty("d:Language")
	private String dLanguage;
	@JsonProperty("xmlns:d")
	private String xmlnsD;
	@JsonProperty("d:ProductDescription")
	private String dProductDescription;
	@JsonProperty("d:Product")
	private String dProduct;
	@JsonProperty("xmlns:m")
	private String xmlnsM;
}
