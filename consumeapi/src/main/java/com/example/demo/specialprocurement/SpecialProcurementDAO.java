package com.example.demo.specialprocurement;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties({"link","id","title","category","updated"})
public class SpecialProcurementDAO {

	@JsonProperty(value="content")
	private SpecialProcurementContentDAO content;
}
