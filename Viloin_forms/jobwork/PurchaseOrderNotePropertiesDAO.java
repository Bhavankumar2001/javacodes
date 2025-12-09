package com.altrocks.forms.jobwork;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseOrderNotePropertiesDAO {

	@JsonProperty("PurchaseOrder")
	private String purchaseOrder;
	@JsonProperty("TextObjectType")
	private String textObjectType;
	@JsonProperty("Language")
	private String language;
	@JsonProperty("PlainLongText")
	private String plainLongText;
}
