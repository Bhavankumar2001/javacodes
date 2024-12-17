package com.example.demo.lotsizingprocedure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties("type")
public class LotSizingContentDAO {
	@JsonProperty(value="m:properties")
	private LotSizingPropertiesDAO properties; 

}
