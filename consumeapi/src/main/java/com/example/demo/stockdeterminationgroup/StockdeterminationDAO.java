package com.example.demo.stockdeterminationgroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties({"link","id","title","category","updated"})
public class StockdeterminationDAO {
	
	@JsonProperty("content")
	private  StockdeterminationContentDAO content;
	

}
