package com.altrocks.forms.jobwork;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPlantPropertiesDAO {
	
	
	@JsonProperty("Product")
	private String product;
	@JsonProperty("Plant")
	private String plant;
	@JsonProperty("PurchasingGroup")
	private String purchasingGroup;
	@JsonProperty("CountryOfOrigin")
	private String countryOfOrigin;
	@JsonProperty("RegionOfOrigin")
	private String regionOfOrigin;
	@JsonProperty("ProductionInvtryManagedLoc")
	private String productionInvtryManagedLoc;
	@JsonProperty("ProfileCode")
	private String profileCode;
	@JsonProperty("ProfileValidityStartDate")
	private Object profileValidityStartDate;
	@JsonProperty("AvailabilityCheckType")
	private String availabilityCheckType;
	@JsonProperty("FiscalYearVariant")
	private String fiscalYearVariant;
	@JsonProperty("PeriodType")
	private String periodType;
	@JsonProperty("ProfitCenter")
	private String profitCenter;
	@JsonProperty("Commodity")
	private String commodity;
	@JsonProperty("GoodsReceiptDuration")
	private String goodsReceiptDuration;
	@JsonProperty("MaintenanceStatusName")
	private String maintenanceStatusName;
	@JsonProperty("IsMarkedForDeletion")
	private Boolean isMarkedForDeletion;
	@JsonProperty("MRPType")
	private String mRPType;
	@JsonProperty("MRPResponsible")
	private String mRPResponsible;
	@JsonProperty("ABCIndicator")
	private String aBCIndicator;
	@JsonProperty("MinimumLotSizeQuantity")
	private String minimumLotSizeQuantity;
	@JsonProperty("MaximumLotSizeQuantity")
	private String maximumLotSizeQuantity;
	@JsonProperty("FixedLotSizeQuantity")
	private String fixedLotSizeQuantity;
	@JsonProperty("ConsumptionTaxCtrlCode")
	private String consumptionTaxCtrlCode;
	@JsonProperty("IsCoProduct")
	private Boolean isCoProduct;
	@JsonProperty("ProductIsConfigurable")
	private String productIsConfigurable;
	@JsonProperty("StockDeterminationGroup")
	private String stockDeterminationGroup;
	@JsonProperty("StockInTransferQuantity")
	private String stockInTransferQuantity;
	@JsonProperty("StockInTransitQuantity")
	private String stockInTransitQuantity;
	@JsonProperty("HasPostToInspectionStock")
	private Boolean hasPostToInspectionStock;
	@JsonProperty("IsBatchManagementRequired")
	private Boolean isBatchManagementRequired;
	@JsonProperty("SerialNumberProfile")
	private String serialNumberProfile;
	@JsonProperty("IsNegativeStockAllowed")
	private Boolean isNegativeStockAllowed;
	@JsonProperty("GoodsReceiptBlockedStockQty")
	private String goodsReceiptBlockedStockQty;
	@JsonProperty("HasConsignmentCtrl")
	private String hasConsignmentCtrl;
	@JsonProperty("FiscalYearCurrentPeriod")
	private String fiscalYearCurrentPeriod;
	@JsonProperty("FiscalMonthCurrentPeriod")
	private String fiscalMonthCurrentPeriod;
	@JsonProperty("ProcurementType")
	private String procurementType;
	@JsonProperty("IsInternalBatchManaged")
	private Boolean isInternalBatchManaged;
	@JsonProperty("ProductCFOPCategory")
	private String productCFOPCategory;
	@JsonProperty("ProductIsExciseTaxRelevant")
	private Boolean productIsExciseTaxRelevant;
	@JsonProperty("BaseUnit")
	private String baseUnit;
	@JsonProperty("ConfigurableProduct")
	private String configurableProduct;
	@JsonProperty("GoodsIssueUnit")
	private String goodsIssueUnit;
	@JsonProperty("MaterialFreightGroup")
	private String materialFreightGroup;
	@JsonProperty("OriginalBatchReferenceMaterial")
	private String originalBatchReferenceMaterial;
	@JsonProperty("OriglBatchManagementIsRequired")
	private String origlBatchManagementIsRequired;
	@JsonProperty("ProductIsCriticalPrt")
	private Boolean productIsCriticalPrt;
	@JsonProperty("ProductLogisticsHandlingGroup")
	private String productLogisticsHandlingGroup;

}
