package com.example.demo.mrptype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties("type")
public class MrpTypeContentDAO {

	@JsonProperty(value="m:properties")
	private MrpTypePropertiesDAO properties;
}
