package com.altrocks.forms.mrin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationDocHeaderContentDAO {
	
	@JsonProperty("m:properties")
	public ReservationDocHeaderPropertiesDAO properties;
	

}