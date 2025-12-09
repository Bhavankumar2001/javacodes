package com.altrocks.forms.mrin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CostCenterPropertiesDAO {
	@JsonProperty("Language")
	private String language;

	@JsonProperty("ControllingArea")
	private String controllingArea;

	@JsonProperty("CostCenter")
	private String costCenter;

	@JsonProperty("ValidityEndDate")
	private String validityEndDate;

	@JsonProperty("CostCenterName")
	private String costCenterName;

	@JsonProperty("CostCenterDescription")
	private String costCenterDescription;

	@JsonProperty("SAP__Messages")
	private List<String> sapMessages;

}
