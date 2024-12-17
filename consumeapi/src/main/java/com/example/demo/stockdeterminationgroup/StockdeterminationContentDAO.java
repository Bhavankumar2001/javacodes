package com.example.demo.stockdeterminationgroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties({"type"})
public class StockdeterminationContentDAO {
	
	@JsonProperty("m:properties")
	private StockdeterminationPropertiesDAO properties;
 
}
