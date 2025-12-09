package com.altrocks.forms.jobwork;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductValuationContentDAO {
	
	@JsonProperty("m:properties")
	public ProductValuationPropertiesDAO propertiesDAO;

}
