package com.altrocks.forms.mrin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovingPricePropertiesDAO {
	@JsonProperty("Currency")
	private String dCurrency;
	@JsonProperty("ValuationArea")
	private String dValuationArea;
	@JsonProperty("MovingAveragePrice")
	private Double dMovingAveragePrice;
	@JsonProperty("Product")
	private String dProduct;
	@JsonProperty("ValuationType")
	private String dValuationType;
	@JsonProperty("StandardPrice")
	private Double dStandardPrice;
	
	
	@JsonProperty("InventoryValuationProcedure")
	private String dinventoryValuationProcedure;

}
