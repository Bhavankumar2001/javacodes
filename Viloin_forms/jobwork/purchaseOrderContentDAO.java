package com.altrocks.forms.jobwork;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class purchaseOrderContentDAO {

	@JsonProperty("m:properties")
	public purchaseOrderPropertiesDAO propertiesDAO;

}
