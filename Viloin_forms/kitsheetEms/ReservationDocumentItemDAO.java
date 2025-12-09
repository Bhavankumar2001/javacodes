package com.altrocks.forms.kitsheetEms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationDocumentItemDAO {
	

	@JsonProperty("content")
	public AReservationDocumentItemContentDAO contentDAO;
}
