package com.altrocks.forms.jobwork;

import com.altrocks.forms.debitNote.purchaseOrderContentDAO;
import com.altrocks.forms.debitNote.purchaseOrderDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseOrderScheduleLineDAO {
	
	@JsonProperty("content")
	public PurchaseOrderScheduleLineContentDAO contentDAO;


}
