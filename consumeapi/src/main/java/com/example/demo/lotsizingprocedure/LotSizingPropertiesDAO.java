package com.example.demo.lotsizingprocedure;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LotSizingPropertiesDAO {
	
	@JsonProperty("d:SAP_UUID")
	private String dSAPUUID;
//	@JsonProperty("d:SAP_LastChangedByUser")
//	private String dSAPLastChangedByUser;
//	@JsonProperty("d:SAP_LifecycleStatus_Text")
//	private String dSAPLifecycleStatusText;
//	@JsonProperty("d:SAP_CreatedByUser_Text")
//	private String dSAPCreatedByUserText;
//	@JsonProperty("d:SAP_LastChangedDateTime")
//	private DSAPLastChangedDateTime dSAPLastChangedDateTime;
	@JsonProperty("d:Description")
	private String dDescription;
//	@JsonProperty("d:SAP_LastChangedByUser_Text")
//	private String dSAPLastChangedByUserText;
//	@JsonProperty("d:SAP_CreatedByUser")
//	private String dSAPCreatedByUser;
//	@JsonProperty("d:SAP_CreatedDateTime")
//	private DSAPCreatedDateTime dSAPCreatedDateTime;
//	@JsonProperty("xmlns:d")
//	private String xmlnsD;
	@JsonProperty("d:LotSizing")
	private String dLotSizing;
//	@JsonProperty("d:SAP_LifecycleStatus")
//	private String dSAPLifecycleStatus;
//	@JsonProperty("xmlns:m")
//	private String xmlnsM;

}
