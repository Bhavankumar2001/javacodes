package com.example.demo.lotsizingprocedure;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties({"link","id","title","category","updated"})
public class LotSizingDAO {

	@JsonProperty(value="content")
	private LotSizingContentDAO content;
}
