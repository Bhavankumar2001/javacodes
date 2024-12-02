package com.java.demo.display;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
@JsonIgnoreProperties({"type"})
public class contentMtoDAO {
	
	@JsonProperty("m:properties")
	public ZCOGSMtoPropertiesDAO properties;
}
