package com.altrocks.forms.kitsheetEms;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductionOrderComponent2PropertiesDAO {
	@JsonProperty("d:ProductionUnit")
	private String dProductionUnit;
	@JsonProperty("d:MfgOrderPlannedEndDate")
	private String dMfgOrderPlannedEndDate;
	@JsonProperty("d:QuantityDistributionKey")
	private String dQuantityDistributionKey;
	@JsonProperty("d:GoodsRecipientName")
	private String dGoodsRecipientName;
	@JsonProperty("d:PlannedCostsCostingVariant")
	private String dPlannedCostsCostingVariant;
	@JsonProperty("d:OrderSequenceNumber")
	private Integer dOrderSequenceNumber;
	@JsonProperty("d:BasicSchedulingType")
	private Integer dBasicSchedulingType;
	@JsonProperty("d:MfgOrderCreationTime")
	private String dMfgOrderCreationTime;
	@JsonProperty("d:WBSElementExternalID")
	private String dWBSElementExternalID;
	@JsonProperty("d:OrderIsClosed")
	private String dOrderIsClosed;
	@JsonProperty("d:SalesOrder")
	private String dSalesOrder;
	@JsonProperty("d:InventoryUsabilityCode")
	private String dInventoryUsabilityCode;
	@JsonProperty("d:MRPArea")
	private Integer dMRPArea;
	@JsonProperty("d:OrderIsDelivered")
	private String dOrderIsDelivered;
	@JsonProperty("d:MaterialGoodsReceiptDuration")
	private Integer dMaterialGoodsReceiptDuration;
	@JsonProperty("d:OrderIsReleased")
	private String dOrderIsReleased;
	@JsonProperty("d:OrderIsPreCosted")
	private String dOrderIsPreCosted;
	@JsonProperty("xmlns:d")
	private String xmlnsD;
	@JsonProperty("d:BusinessArea")
	private String dBusinessArea;
	@JsonProperty("d:MfgOrderPlannedEndTime")
	private String dMfgOrderPlannedEndTime;
	@JsonProperty("d:LastChangeDateTime")
	private Long dLastChangeDateTime;
	@JsonProperty("xmlns:m")
	private String xmlnsM;
	@JsonProperty("d:ProfitCenter")
	private Integer dProfitCenter;
	@JsonProperty("d:MfgOrderCreationDate")
	private String dMfgOrderCreationDate;
	@JsonProperty("d:ProductionPlant")
	private Integer dProductionPlant;
	@JsonProperty("d:ManufacturingOrderCategory")
	private Integer dManufacturingOrderCategory;
	@JsonProperty("d:OrderIsPrinted")
	private String dOrderIsPrinted;
	@JsonProperty("d:OrderIsCreated")
	private String dOrderIsCreated;
	@JsonProperty("d:ManufacturingOrder")
	private Integer dManufacturingOrder;
	@JsonProperty("d:MfgOrderPlannedStartTime")
	private String dMfgOrderPlannedStartTime;
	@JsonProperty("d:TotalQuantity")
	private Integer dTotalQuantity;
	@JsonProperty("d:ManufacturingObject")
	private String dManufacturingObject;
	@JsonProperty("d:OrderIsMarkedForDeletion")
	private String dOrderIsMarkedForDeletion;
	@JsonProperty("d:SalesOrderItem")
	private Integer dSalesOrderItem;
	@JsonProperty("d:PlannedOrder")
	private String dPlannedOrder;
	@JsonProperty("d:ProductionUnitSAPCode")
	private String dProductionUnitSAPCode;
	@JsonProperty("d:Plant")
	private Integer dPlant;
	@JsonProperty("d:OrderIsPartiallyConfirmed")
	private String dOrderIsPartiallyConfirmed;
	@JsonProperty("d:ActualCostsCostingVariant")
	private String dActualCostsCostingVariant;
	@JsonProperty("d:CustomerName")
	private String dCustomerName;
	@JsonProperty("d:MRPController")
	private String dMRPController;
	@JsonProperty("d:OrderHasGeneratedOperations")
	private String dOrderHasGeneratedOperations;
	@JsonProperty("d:ManufacturingOrderType")
	private String dManufacturingOrderType;
	@JsonProperty("d:Material")
	private Long dMaterial;
	@JsonProperty("d:MfgOrderPlannedScrapQty")
	private Integer dMfgOrderPlannedScrapQty;
	@JsonProperty("d:MfgOrderPlannedStartDate")
	private String dMfgOrderPlannedStartDate;
	@JsonProperty("d:ManufacturingOrderImportance")
	private String dManufacturingOrderImportance;
	@JsonProperty("d:OrderIsConfirmed")
	private String dOrderIsConfirmed;
	@JsonProperty("d:MfgOrderScheduledEndDate")
	private String dMfgOrderScheduledEndDate;
	@JsonProperty("d:StorageLocation")
	private String dStorageLocation;
	@JsonProperty("d:OrderIsDeleted")
	private String dOrderIsDeleted;
	@JsonProperty("d:SettlementRuleIsCrtedManually")
	private String dSettlementRuleIsCrtedManually;
	@JsonProperty("d:OrderInternalBillOfOperations")
	private Integer dOrderInternalBillOfOperations;
	@JsonProperty("d:OrderIsTechnicallyCompleted")
	private String dOrderIsTechnicallyCompleted;
	@JsonProperty("d:CompanyCode")
	private Integer dCompanyCode;
	@JsonProperty("d:MfgOrderConfirmedYieldQty")
	private Integer dMfgOrderConfirmedYieldQty;
	@JsonProperty("d:MaterialAvailyIsNotChecked")
	private String dMaterialAvailyIsNotChecked;
	@JsonProperty("d:FunctionalArea")
	private String dFunctionalArea;
	@JsonProperty("d:OrderIsPartiallyDelivered")
	private String dOrderIsPartiallyDelivered;
	@JsonProperty("d:MfgOrderScheduledStartDate")
	private String dMfgOrderScheduledStartDate;
	@JsonProperty("d:MfgOrderActualReleaseDate")
	private Date dMfgOrderActualReleaseDate;
	@JsonProperty("d:OrderIsScheduled")
	private String dOrderIsScheduled;
	@JsonProperty("d:ProductionSupervisor")
	private String dProductionSupervisor;
	@JsonProperty("d:StockSegment")
	private String dStockSegment;
	@JsonProperty("d:OrderIsLocked")
	private String dOrderIsLocked;
	@JsonProperty("d:ProductionUnitISOCode")
	private String dProductionUnitISOCode;
	@JsonProperty("d:UnloadingPointName")
	private String dUnloadingPointName;
	@JsonProperty("d:MfgOrderScheduledEndTime")
	private String dMfgOrderScheduledEndTime;
	@JsonProperty("d:YY1_UpdateFlag_ORD")
	private String dYY1UpdateFlagORD;
	@JsonProperty("d:ProductionVersion")
	private String dProductionVersion;
	@JsonProperty("d:ProductConfiguration")
	private Integer dProductConfiguration;
	@JsonProperty("d:MfgOrderScheduledStartTime")
	private String dMfgOrderScheduledStartTime;
	@JsonProperty("d:SettlementRuleIsCreated")
	private String dSettlementRuleIsCreated;
	@JsonProperty("d:OrderLongText")
	private String dOrderLongText;
	@JsonProperty("d:OrderIsToBeHandledInBatches")
	private String dOrderIsToBeHandledInBatches;
	@JsonProperty("d:OrderIsPartiallyReleased")
	private String dOrderIsPartiallyReleased;

}
