package com.altrocks.forms.mrin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;



@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationDocHeaderDAO {
	
	@JsonProperty("content")
	public ReservationDocHeaderContentDAO content;

}
