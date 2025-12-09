package com.altrocks.forms.jobwork;

import com.altrocks.forms.routecard.MaterialBOMItemContentDAO;
import com.altrocks.forms.routecard.MaterialBOMItemPropertiesDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class supplierAddressContentDAO {

	@JsonProperty("m:properties")
	public supplierAddressPropertiesDAO properties;
	
}
