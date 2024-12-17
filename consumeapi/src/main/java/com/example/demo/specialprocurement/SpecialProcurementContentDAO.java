package com.example.demo.specialprocurement;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties("type")
public class SpecialProcurementContentDAO {

	@JsonProperty(value="m:properties")
	private SpecialProcurementPropertiesDAO properties;
}
