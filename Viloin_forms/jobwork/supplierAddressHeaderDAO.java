package com.altrocks.forms.jobwork;

import com.altrocks.forms.routecard.ProductionOrderContentDAO;
import com.altrocks.forms.routecard.ProductionOrderDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class supplierAddressHeaderDAO {

	@JsonProperty("content")
	public supplierAddressContentDAO content;

}
