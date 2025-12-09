package com.altrocks.forms.kitsheetEms;

import com.altrocks.forms.kitsheetindent.ProductionOrderComponent4ContentDAO;
import com.altrocks.forms.kitsheetindent.ProductionOrderComponent4DAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductionOrderComponent2DAO {
	@JsonProperty("content")
	public ProductionOrderComponent2ContentDAO contentDAO;


}
