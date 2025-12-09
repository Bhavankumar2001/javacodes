package com.altrocks.forms.kitsheetEms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleOrderItemJsonFormatPropertiesDAO {
	

@JsonProperty("AdditionalValueDays")
private Integer dAdditionalValueDays;
@JsonProperty("ConfdDelivQtyInOrderQtyUnit")
private Double dConfdDelivQtyInOrderQtyUnit;
@JsonProperty("MaterialPricingGroup")
private String dMaterialPricingGroup;
@JsonProperty("SalesOrder")
private String dSalesOrder;
@JsonProperty("SalesOrderItemText")
private String dSalesOrderItemText;
@JsonProperty("DeliveryDateQuantityIsFixed")
private Boolean dDeliveryDateQuantityIsFixed;
@JsonProperty("ItemVolume")
private Double dItemVolume;
@JsonProperty("BillingDocumentDate")
private String dBillingDocumentDate;
@JsonProperty("MaterialGroup")
private Integer dMaterialGroup;
@JsonProperty("Subtotal4Amount")
private Double dSubtotal4Amount;
@JsonProperty("ShippingType")
private String dShippingType;
@JsonProperty("WBSElement")
private String dWBSElement;
@JsonProperty("ItemVolumeUnit")
private String dItemVolumeUnit;
@JsonProperty("ReferenceSDDocumentItem")
private Integer dReferenceSDDocumentItem;
@JsonProperty("Subtotal1Amount")
private Double dSubtotal1Amount;
@JsonProperty("CustomerPaymentTerms")
private String dCustomerPaymentTerms;
@JsonProperty("ItemWeightUnit")
private String dItemWeightUnit;
@JsonProperty("Batch")
private String dBatch;
@JsonProperty("ExternalItemID")
private String dExternalItemID;
@JsonProperty("Material")
private String dMaterial;
//@JsonProperty("YY1_ViolinCommitmentDa_SDI")
//private String dYY1ViolinCommitmentDaSDI;
@JsonProperty("PurchaseOrderByShipToParty")
private String dPurchaseOrderByShipToParty;
@JsonProperty("YY1_Revision_SDI")
private String dYY1RevisionSDI;
@JsonProperty("StorageLocation")
private String dStorageLocation;
@JsonProperty("ProductTaxClassification8")
private String dProductTaxClassification8;
@JsonProperty("ProductTaxClassification9")
private String dProductTaxClassification9;
//@JsonProperty("ServicesRenderedDate")
//private Date dServicesRenderedDate;
@JsonProperty("ItemVolumeSAPUnit")
private String dItemVolumeSAPUnit;
@JsonProperty("BillingPlan")
private String dBillingPlan;
@JsonProperty("Subtotal3Amount")
private Double dSubtotal3Amount;
@JsonProperty("OriginallyRequestedMaterial")
private String dOriginallyRequestedMaterial;
@JsonProperty("OrderRelatedBillingStatus")
private String dOrderRelatedBillingStatus;
@JsonProperty("ItemNetWeight")
private Double dItemNetWeight;
@JsonProperty("HigherLevelItemUsage")
private String dHigherLevelItemUsage;
@JsonProperty("IncotermsLocation2")
private String dIncotermsLocation2;
@JsonProperty("IncotermsTransferLocation")
private String dIncotermsTransferLocation;
@JsonProperty("IncotermsLocation1")
private String dIncotermsLocation1;
@JsonProperty("IncotermsClassification")
private String dIncotermsClassification;
@JsonProperty("PurchaseOrderByCustomer")
private String dPurchaseOrderByCustomer;
@JsonProperty("Subtotal2Amount")
private Double dSubtotal2Amount;
//@JsonProperty("YY1_OrderAcknowledgmen_SDI")
//private String dYY1OrderAcknowledgmenSDI;
@JsonProperty("ItemBillingBlockReason")
private String dItemBillingBlockReason;
@JsonProperty("OrderQuantitySAPUnit")
private String dOrderQuantitySAPUnit;
@JsonProperty("MaterialSubstitutionReason")
private String dMaterialSubstitutionReason;
@JsonProperty("SDProcessStatus")
private String dSDProcessStatus;
@JsonProperty("NetAmount")
private Double dNetAmount;
@JsonProperty("ContractAccount")
private String dContractAccount;
@JsonProperty("Subtotal5Amount")
private Double dSubtotal5Amount;
@JsonProperty("TotalSDDocReferenceStatus")
private String dTotalSDDocReferenceStatus;
@JsonProperty("SalesOrderItemCategory")
private String dSalesOrderItemCategory;
@JsonProperty("ReferenceSDDocument")
private String dReferenceSDDocument;
@JsonProperty("OrderQuantityISOUnit")
private String dOrderQuantityISOUnit;
@JsonProperty("DeliveryPriority")
private Integer dDeliveryPriority;
@JsonProperty("UnderlyingPurchaseOrderItem")
private String dUnderlyingPurchaseOrderItem;
@JsonProperty("TransactionCurrency")
private String dTransactionCurrency;
@JsonProperty("MaterialByCustomer")
private String dMaterialByCustomer;
@JsonProperty("SDDocReferenceStatus")
private String dSDDocReferenceStatus;
@JsonProperty("ProfitCenter")
private Integer dProfitCenter;
@JsonProperty("ProductionPlant")
private Integer dProductionPlant;
@JsonProperty("SalesDocumentRjcnReason")
private Integer dSalesDocumentRjcnReason;
@JsonProperty("OriginalPlant")
private String dOriginalPlant;
//@JsonProperty("FixedValueDate")
//private Date dFixedValueDate;
@JsonProperty("ProductTaxClassification4")
private Integer dProductTaxClassification4;
@JsonProperty("ProductTaxClassification5")
private Integer dProductTaxClassification5;
@JsonProperty("ProductTaxClassification6")
private Integer dProductTaxClassification6;
@JsonProperty("ItemWeightISOUnit")
private String dItemWeightISOUnit;
@JsonProperty("ProductTaxClassification7")
private String dProductTaxClassification7;
@JsonProperty("SalesOrderItem")
private String dSalesOrderItem;
@JsonProperty("ProductTaxClassification1")
private Integer dProductTaxClassification1;
@JsonProperty("ProductTaxClassification2")
private Integer dProductTaxClassification2;
@JsonProperty("ProductTaxClassification3")
private Integer dProductTaxClassification3;
@JsonProperty("RequestedQuantityUnit")
private String dRequestedQuantityUnit;
@JsonProperty("RequestedQuantity")
private Double dRequestedQuantity;
@JsonProperty("PricingReferenceMaterial")
private String dPricingReferenceMaterial;
@JsonProperty("DeliveryGroup")
private Integer dDeliveryGroup;
@JsonProperty("SlsDocIsRlvtForProofOfDeliv")
private Boolean dSlsDocIsRlvtForProofOfDeliv;
@JsonProperty("AdditionalMaterialGroup1")
private String dAdditionalMaterialGroup1;
@JsonProperty("AdditionalMaterialGroup2")
private String dAdditionalMaterialGroup2;
@JsonProperty("AdditionalMaterialGroup3")
private String dAdditionalMaterialGroup3;
@JsonProperty("CostAmount")
private Double dCostAmount;
@JsonProperty("AdditionalMaterialGroup4")
private String dAdditionalMaterialGroup4;
@JsonProperty("AdditionalMaterialGroup5")
private String dAdditionalMaterialGroup5;
@JsonProperty("RequestedQuantitySAPUnit")
private String dRequestedQuantitySAPUnit;
@JsonProperty("YY1_ViolinIncPartCode_SDI")
private String dYY1ViolinIncPartCodeSDI;
@JsonProperty("DeliveryStatus")
private String dDeliveryStatus;
@JsonProperty("RequestedQuantityISOUnit")
private String dRequestedQuantityISOUnit;
@JsonProperty("DeliveryDateTypeRule")
private String dDeliveryDateTypeRule;
@JsonProperty("MatlAccountAssignmentGroup")
private String dMatlAccountAssignmentGroup;
@JsonProperty("ItemVolumeISOUnit")
private String dItemVolumeISOUnit;
@JsonProperty("OrderQuantityUnit")
private String dOrderQuantityUnit;
@JsonProperty("AltvBsdConfSubstitutionStatus")
private String dAltvBsdConfSubstitutionStatus;
@JsonProperty("TaxAmount")
private Double dTaxAmount;
@JsonProperty("Subtotal6Amount")
private Double dSubtotal6Amount;
@JsonProperty("HigherLevelItem")
private Integer dHigherLevelItem;
@JsonProperty("ShippingPoint")
private Integer dShippingPoint;
@JsonProperty("PricingDate")
private String dPricingDate;
//@JsonProperty("YY1_MaterialLeadDate_SDI")
//private String dYY1MaterialLeadDateSDI;
@JsonProperty("ItemWeightSAPUnit")
private String dItemWeightSAPUnit;
@JsonProperty("ItemGrossWeight")
private Double dItemGrossWeight;
@JsonProperty("CustomerGroup")
private String dCustomerGroup;
@JsonProperty("AccountingExchangeRate")
private Double dAccountingExchangeRate;

}
