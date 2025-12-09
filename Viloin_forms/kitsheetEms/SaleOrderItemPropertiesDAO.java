package com.altrocks.forms.kitsheetEms;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleOrderItemPropertiesDAO {
	

@JsonProperty("d:AdditionalValueDays")
private Integer dAdditionalValueDays;
@JsonProperty("d:ConfdDelivQtyInOrderQtyUnit")
private Integer dConfdDelivQtyInOrderQtyUnit;
@JsonProperty("d:MaterialPricingGroup")
private String dMaterialPricingGroup;
@JsonProperty("d:SalesOrder")
private String dSalesOrder;
@JsonProperty("d:SalesOrderItemText")
private String dSalesOrderItemText;
@JsonProperty("d:DeliveryDateQuantityIsFixed")
private Boolean dDeliveryDateQuantityIsFixed;
@JsonProperty("d:ItemVolume")
private Integer dItemVolume;
@JsonProperty("d:BillingDocumentDate")
private String dBillingDocumentDate;
@JsonProperty("d:MaterialGroup")
private Integer dMaterialGroup;
@JsonProperty("d:Subtotal4Amount")
private Integer dSubtotal4Amount;
@JsonProperty("d:ShippingType")
private String dShippingType;
@JsonProperty("d:WBSElement")
private String dWBSElement;
@JsonProperty("d:ItemVolumeUnit")
private String dItemVolumeUnit;
@JsonProperty("d:ReferenceSDDocumentItem")
private Integer dReferenceSDDocumentItem;
@JsonProperty("d:Subtotal1Amount")
private Integer dSubtotal1Amount;
@JsonProperty("d:CustomerPaymentTerms")
private String dCustomerPaymentTerms;
@JsonProperty("d:ItemWeightUnit")
private String dItemWeightUnit;
@JsonProperty("d:Batch")
private String dBatch;
@JsonProperty("d:ExternalItemID")
private String dExternalItemID;
@JsonProperty("d:Material")
private String dMaterial;
//@JsonProperty("d:YY1_ViolinCommitmentDa_SDI")
//private String dYY1ViolinCommitmentDaSDI;
@JsonProperty("d:PurchaseOrderByShipToParty")
private String dPurchaseOrderByShipToParty;
@JsonProperty("d:YY1_Revision_SDI")
private String dYY1RevisionSDI;
@JsonProperty("d:StorageLocation")
private String dStorageLocation;
@JsonProperty("d:ProductTaxClassification8")
private String dProductTaxClassification8;
@JsonProperty("d:ProductTaxClassification9")
private String dProductTaxClassification9;
//@JsonProperty("d:ServicesRenderedDate")
//private Date dServicesRenderedDate;
@JsonProperty("d:ItemVolumeSAPUnit")
private String dItemVolumeSAPUnit;
@JsonProperty("d:BillingPlan")
private String dBillingPlan;
@JsonProperty("d:Subtotal3Amount")
private Integer dSubtotal3Amount;
@JsonProperty("d:OriginallyRequestedMaterial")
private String dOriginallyRequestedMaterial;
@JsonProperty("d:OrderRelatedBillingStatus")
private String dOrderRelatedBillingStatus;
@JsonProperty("d:ItemNetWeight")
private Integer dItemNetWeight;
@JsonProperty("d:HigherLevelItemUsage")
private String dHigherLevelItemUsage;
@JsonProperty("d:IncotermsLocation2")
private String dIncotermsLocation2;
@JsonProperty("d:IncotermsTransferLocation")
private String dIncotermsTransferLocation;
@JsonProperty("d:IncotermsLocation1")
private String dIncotermsLocation1;
@JsonProperty("d:IncotermsClassification")
private String dIncotermsClassification;
@JsonProperty("d:PurchaseOrderByCustomer")
private String dPurchaseOrderByCustomer;
@JsonProperty("d:Subtotal2Amount")
private Integer dSubtotal2Amount;
//@JsonProperty("d:YY1_OrderAcknowledgmen_SDI")
//private String dYY1OrderAcknowledgmenSDI;
@JsonProperty("d:ItemBillingBlockReason")
private String dItemBillingBlockReason;
@JsonProperty("d:OrderQuantitySAPUnit")
private String dOrderQuantitySAPUnit;
@JsonProperty("d:MaterialSubstitutionReason")
private String dMaterialSubstitutionReason;
@JsonProperty("d:SDProcessStatus")
private String dSDProcessStatus;
@JsonProperty("d:NetAmount")
private Integer dNetAmount;
@JsonProperty("d:ContractAccount")
private String dContractAccount;
@JsonProperty("d:Subtotal5Amount")
private Integer dSubtotal5Amount;
@JsonProperty("d:TotalSDDocReferenceStatus")
private String dTotalSDDocReferenceStatus;
@JsonProperty("d:SalesOrderItemCategory")
private String dSalesOrderItemCategory;
@JsonProperty("d:ReferenceSDDocument")
private String dReferenceSDDocument;
@JsonProperty("d:OrderQuantityISOUnit")
private String dOrderQuantityISOUnit;
@JsonProperty("d:DeliveryPriority")
private Integer dDeliveryPriority;
@JsonProperty("d:UnderlyingPurchaseOrderItem")
private String dUnderlyingPurchaseOrderItem;
@JsonProperty("d:TransactionCurrency")
private String dTransactionCurrency;
@JsonProperty("xmlns:d")
private String xmlnsD;
@JsonProperty("d:MaterialByCustomer")
private String dMaterialByCustomer;
@JsonProperty("d:SDDocReferenceStatus")
private String dSDDocReferenceStatus;
@JsonProperty("xmlns:m")
private String xmlnsM;
@JsonProperty("d:ProfitCenter")
private Integer dProfitCenter;
@JsonProperty("d:ProductionPlant")
private Integer dProductionPlant;
@JsonProperty("d:SalesDocumentRjcnReason")
private Integer dSalesDocumentRjcnReason;
@JsonProperty("d:OriginalPlant")
private String dOriginalPlant;
//@JsonProperty("d:FixedValueDate")
//private Date dFixedValueDate;
@JsonProperty("d:ProductTaxClassification4")
private Integer dProductTaxClassification4;
@JsonProperty("d:ProductTaxClassification5")
private Integer dProductTaxClassification5;
@JsonProperty("d:ProductTaxClassification6")
private Integer dProductTaxClassification6;
@JsonProperty("d:ItemWeightISOUnit")
private String dItemWeightISOUnit;
@JsonProperty("d:ProductTaxClassification7")
private String dProductTaxClassification7;
@JsonProperty("d:SalesOrderItem")
private String dSalesOrderItem;
@JsonProperty("d:ProductTaxClassification1")
private Integer dProductTaxClassification1;
@JsonProperty("d:ProductTaxClassification2")
private Integer dProductTaxClassification2;
@JsonProperty("d:ProductTaxClassification3")
private Integer dProductTaxClassification3;
@JsonProperty("d:RequestedQuantityUnit")
private String dRequestedQuantityUnit;
@JsonProperty("d:RequestedQuantity")
private Integer dRequestedQuantity;
@JsonProperty("d:PricingReferenceMaterial")
private String dPricingReferenceMaterial;
@JsonProperty("d:DeliveryGroup")
private Integer dDeliveryGroup;
@JsonProperty("d:SlsDocIsRlvtForProofOfDeliv")
private Boolean dSlsDocIsRlvtForProofOfDeliv;
@JsonProperty("d:AdditionalMaterialGroup1")
private String dAdditionalMaterialGroup1;
@JsonProperty("d:AdditionalMaterialGroup2")
private String dAdditionalMaterialGroup2;
@JsonProperty("d:AdditionalMaterialGroup3")
private String dAdditionalMaterialGroup3;
@JsonProperty("d:CostAmount")
private Integer dCostAmount;
@JsonProperty("d:AdditionalMaterialGroup4")
private String dAdditionalMaterialGroup4;
@JsonProperty("d:AdditionalMaterialGroup5")
private String dAdditionalMaterialGroup5;
@JsonProperty("d:RequestedQuantitySAPUnit")
private String dRequestedQuantitySAPUnit;
@JsonProperty("d:YY1_ViolinIncPartCode_SDI")
private String dYY1ViolinIncPartCodeSDI;
@JsonProperty("d:DeliveryStatus")
private String dDeliveryStatus;
@JsonProperty("d:RequestedQuantityISOUnit")
private String dRequestedQuantityISOUnit;
@JsonProperty("d:DeliveryDateTypeRule")
private String dDeliveryDateTypeRule;
@JsonProperty("d:MatlAccountAssignmentGroup")
private String dMatlAccountAssignmentGroup;
@JsonProperty("d:ItemVolumeISOUnit")
private String dItemVolumeISOUnit;
@JsonProperty("d:OrderQuantityUnit")
private String dOrderQuantityUnit;
@JsonProperty("d:AltvBsdConfSubstitutionStatus")
private String dAltvBsdConfSubstitutionStatus;
@JsonProperty("d:TaxAmount")
private Integer dTaxAmount;
@JsonProperty("d:Subtotal6Amount")
private Integer dSubtotal6Amount;
@JsonProperty("d:HigherLevelItem")
private Integer dHigherLevelItem;
@JsonProperty("d:ShippingPoint")
private Integer dShippingPoint;
@JsonProperty("d:PricingDate")
private String dPricingDate;
//@JsonProperty("d:YY1_MaterialLeadDate_SDI")
//private String dYY1MaterialLeadDateSDI;
@JsonProperty("d:ItemWeightSAPUnit")
private String dItemWeightSAPUnit;
@JsonProperty("d:ItemGrossWeight")
private Integer dItemGrossWeight;
@JsonProperty("d:CustomerGroup")
private String dCustomerGroup;
@JsonProperty("d:AccountingExchangeRate")
private Integer dAccountingExchangeRate;

}
