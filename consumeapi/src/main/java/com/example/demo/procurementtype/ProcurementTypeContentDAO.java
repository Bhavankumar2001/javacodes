package com.example.demo.procurementtype;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties("type")
public class ProcurementTypeContentDAO {
	@JsonProperty(value="m:properties")
	private ProcurementTypePropertiesDAO properties;

}
