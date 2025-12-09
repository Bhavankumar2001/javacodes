package com.altrocks.forms.jobwork;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDescriptionPropertiesDAO {
	
	@JsonProperty("Product")
	private String product;
	@JsonProperty("Language")
	private String language;
	@JsonProperty("ProductDescription")
	private String productDescription;
}
