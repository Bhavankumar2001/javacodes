package com.example.demo.backflush;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties("type")
public class BackFlushContentDAO {

	@JsonProperty(value="m:properties")
	private BackFlushPropertiesDAO properties;
}
