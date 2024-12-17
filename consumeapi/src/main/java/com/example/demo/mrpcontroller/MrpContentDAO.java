package com.example.demo.mrpcontroller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties("type")
public class MrpContentDAO {
	@JsonProperty(value="m:properties")
	private MrpPropertiesDAO properties; 
}
