package com.altrocks.forms.jobwork;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class POSubcontractingComponentPropertiesDAO {
	
	@JsonProperty("PurchaseOrder")
	private String purchaseOrder;
	@JsonProperty("PurchaseOrderItem")
	private String purchaseOrderItem;
	@JsonProperty("ScheduleLine")
	private String scheduleLine;
	@JsonProperty("ReservationItem")
	private String reservationItem;
	@JsonProperty("RecordType")
	private String recordType;
	@JsonProperty("Material")
	private String material;
	@JsonProperty("BOMItemDescription")
	private String bOMItemDescription;
	@JsonProperty("RequiredQuantity")
	private String requiredQuantity;
	@JsonProperty("BaseUnit")
	private String baseUnit;
	@JsonProperty("RequirementDate")
	private String requirementDate;
	@JsonProperty("QuantityInEntryUnit")
	private String quantityInEntryUnit;
	@JsonProperty("EntryUnit")
	private String entryUnit;
	@JsonProperty("WithdrawnQuantity")
	private String withdrawnQuantity;
	@JsonProperty("Plant")
	private String plant;
	@JsonProperty("Batch")
	private String batch;

}
