package com.altrocks.forms.jobwork;

import java.util.Date;

import com.altrocks.forms.debitNote.purchaseOrderContentDAO;
import com.altrocks.forms.debitNote.purchaseOrderDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseOrderScheduleLinePropertiesDAO {

	@JsonProperty("PurchasingDocument")
	private String purchasingDocument;

	@JsonProperty("PurchasingDocumentItem")
	private String purchasingDocumentItem;

	@JsonProperty("ScheduleLine")
	private String scheduleLine;

	@JsonProperty("DelivDateCategory")
	private String delivDateCategory;

	@JsonProperty("ScheduleLineDeliveryDate")
	private JsonNode scheduleLineDeliveryDate;

	@JsonProperty("PurchaseOrderQuantityUnit")
	private String purchaseOrderQuantityUnit;

	@JsonProperty("ScheduleLineOrderQuantity")
	private String scheduleLineOrderQuantity;

	

	@JsonProperty("PurchaseRequisition")
	private String purchaseRequisition;

	@JsonProperty("PurchaseRequisitionItem")
	private String purchaseRequisitionItem;

	@JsonProperty("ScheduleLineCommittedQuantity")
	private String scheduleLineCommittedQuantity;

//	@JsonProperty("PerformancePeriodStartDate")
//	private Date performancePeriodStartDate;
//
//	@JsonProperty("PerformancePeriodEndDate")
//	private Date performancePeriodEndDate;
}
