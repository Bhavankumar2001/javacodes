package com.example.demo.mrpcontroller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MrpPropertiesDAO {
//	@JsonProperty("d:SAP_UUID")
//	private String dSAPUUID;
//	@JsonProperty("d:SAP_LastChangedByUser")
//	private String dSAPLastChangedByUser;
//	@JsonProperty("d:SAP_LifecycleStatus_Text")
//	private String dSAPLifecycleStatusText;
//	@JsonProperty("d:SAP_CreatedByUser_Text")
//	private String dSAPCreatedByUserText;
//	@JsonProperty("d:SAP_LastChangedDateTime")
//	private DSAPLastChangedDateTime dSAPLastChangedDateTime;
	@JsonProperty("d:MRPCn")
	private String dMRPCn;
//	@JsonProperty("d:SAP_LastChangedByUser_Text")
//	private String dSAPLastChangedByUserText;
//	@JsonProperty("d:SAP_CreatedByUser")
//	private String dSAPCreatedByUser;
//	@JsonProperty("d:SAP_CreatedDateTime")
//	private DSAPCreatedDateTime dSAPCreatedDateTime;
//	@JsonProperty("xmlns:d")
//	private String xmlnsD;
	@JsonProperty("d:Name")
	private String dName;
//	@JsonProperty("d:SAP_LifecycleStatus")
//	private String dSAPLifecycleStatus;
//	@JsonProperty("xmlns:m")
//	private String xmlnsM;

}
