package com.altrocks.forms.jobwork;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class purchaseOrderDAO {
	
	@JsonProperty("content")
	public purchaseOrderContentDAO contentDAO;

}
