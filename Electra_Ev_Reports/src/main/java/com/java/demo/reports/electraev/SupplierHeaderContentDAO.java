package com.java.demo.reports.electraev;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties("type")
public class SupplierHeaderContentDAO {

	@JsonProperty("m:properties")
	public SupplierHeaderPropertiesDAO properties;
}
