package com.altrocks.forms.jobwork;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymenttermstextPropertiesDAO {
	
	@JsonProperty("PurchaseOrder")
	private String purchaseOrder;
	@JsonProperty("PaymentTerms")
	private String paymentTerms;
	@JsonProperty("PaymentTermsConditionDesc")
	private String paymentTermsConditionDesc;

}
