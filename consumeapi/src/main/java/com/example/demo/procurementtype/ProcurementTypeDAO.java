package com.example.demo.procurementtype;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties({"link","id","title","category","updated"})
public class ProcurementTypeDAO {

	@JsonProperty(value="content")
	private ProcurementTypeContentDAO content;
}
