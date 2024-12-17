package com.example.demo.stockdeterminationgroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockdeterminationPropertiesDAO {

	@JsonProperty("d:SAP_UUID")
	private String dSAPUUID;
	@JsonProperty("d:Plnt")
	private String dPlnt;
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
	@JsonProperty("d:GroupSTCK")
	private Integer dGroupSTCK;
	@JsonProperty("d:Code")
	private String dCode;
//	@JsonProperty("d:SAP_CreatedDateTime")
//	private DSAPCreatedDateTime dSAPCreatedDateTime;
//	@JsonProperty("xmlns:d")
//	private String xmlnsD;
//	@JsonProperty("d:SAP_LifecycleStatus")
//	private String dSAPLifecycleStatus;
//	@JsonProperty("xmlns:m")
//	private String xmlnsM;
}
