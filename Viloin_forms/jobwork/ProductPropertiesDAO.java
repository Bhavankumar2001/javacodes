package com.altrocks.forms.jobwork;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPropertiesDAO {
	
	@JsonProperty("Product")
	private String product;
	@JsonProperty("ProductType")
	private String productType;
	@JsonProperty("CrossPlantStatus")
	private String crossPlantStatus;
	@JsonProperty("CrossPlantStatusValidityDate")
	private Object crossPlantStatusValidityDate;
	@JsonProperty("CreationDate")
	private String creationDate;
	@JsonProperty("CreatedByUser")
	private String createdByUser;
	@JsonProperty("LastChangeDate")
	private String lastChangeDate;
	@JsonProperty("LastChangedByUser")
	private String lastChangedByUser;
	@JsonProperty("LastChangeDateTime")
	private String lastChangeDateTime;
	@JsonProperty("IsMarkedForDeletion")
	private Boolean isMarkedForDeletion;
	@JsonProperty("ProductOldID")
	private String productOldID;
	@JsonProperty("GrossWeight")
	private String grossWeight;
	@JsonProperty("PurchaseOrderQuantityUnit")
	private String purchaseOrderQuantityUnit;
	@JsonProperty("SourceOfSupply")
	private String sourceOfSupply;
	@JsonProperty("WeightUnit")
	private String weightUnit;
	@JsonProperty("NetWeight")
	private String netWeight;
	@JsonProperty("CountryOfOrigin")
	private String countryOfOrigin;
	@JsonProperty("CompetitorID")
	private String competitorID;
	@JsonProperty("ProductGroup")
	private String productGroup;
	@JsonProperty("BaseUnit")
	private String baseUnit;
	@JsonProperty("ItemCategoryGroup")
	private String itemCategoryGroup;
	@JsonProperty("ProductHierarchy")
	private String productHierarchy;
	@JsonProperty("Division")
	private String division;
	@JsonProperty("VarblPurOrdUnitIsActive")
	private String varblPurOrdUnitIsActive;
	@JsonProperty("VolumeUnit")
	private String volumeUnit;
	@JsonProperty("MaterialVolume")
	private String materialVolume;
	@JsonProperty("ANPCode")
	private String aNPCode;
	@JsonProperty("Brand")
	private String brand;
	@JsonProperty("ProcurementRule")
	private String procurementRule;
	@JsonProperty("ValidityStartDate")
	private Object validityStartDate;
	@JsonProperty("LowLevelCode")
	private String lowLevelCode;
	@JsonProperty("ProdNoInGenProdInPrepackProd")
	private String prodNoInGenProdInPrepackProd;
	@JsonProperty("SerialIdentifierAssgmtProfile")
	private String serialIdentifierAssgmtProfile;
	@JsonProperty("SizeOrDimensionText")
	private String sizeOrDimensionText;
	@JsonProperty("IndustryStandardName")
	private String industryStandardName;
	@JsonProperty("ProductStandardID")
	private String productStandardID;
	@JsonProperty("InternationalArticleNumberCat")
	private String internationalArticleNumberCat;
	@JsonProperty("ProductIsConfigurable")
	private Boolean productIsConfigurable;
	@JsonProperty("IsBatchManagementRequired")
	private Boolean isBatchManagementRequired;
	@JsonProperty("ExternalProductGroup")
	private String externalProductGroup;
	@JsonProperty("CrossPlantConfigurableProduct")
	private String crossPlantConfigurableProduct;
	@JsonProperty("SerialNoExplicitnessLevel")
	private String serialNoExplicitnessLevel;
	@JsonProperty("ProductManufacturerNumber")
	private String productManufacturerNumber;
	@JsonProperty("ManufacturerNumber")
	private String manufacturerNumber;
	@JsonProperty("ManufacturerPartProfile")
	private String manufacturerPartProfile;
	@JsonProperty("QltyMgmtInProcmtIsActive")
	private Boolean qltyMgmtInProcmtIsActive;
	@JsonProperty("IndustrySector")
	private String industrySector;
	@JsonProperty("ChangeNumber")
	private String changeNumber;
	@JsonProperty("MaterialRevisionLevel")
	private String materialRevisionLevel;
	@JsonProperty("HandlingIndicator")
	private String handlingIndicator;
	@JsonProperty("WarehouseProductGroup")
	private String warehouseProductGroup;
	@JsonProperty("WarehouseStorageCondition")
	private String warehouseStorageCondition;
	@JsonProperty("StandardHandlingUnitType")
	private String standardHandlingUnitType;
	@JsonProperty("SerialNumberProfile")
	private String serialNumberProfile;
	@JsonProperty("AdjustmentProfile")
	private String adjustmentProfile;
	@JsonProperty("PreferredUnitOfMeasure")
	private String preferredUnitOfMeasure;
	@JsonProperty("IsPilferable")
	private Boolean isPilferable;
	@JsonProperty("IsRelevantForHzdsSubstances")
	private Boolean isRelevantForHzdsSubstances;
	@JsonProperty("QuarantinePeriod")
	private String quarantinePeriod;
	@JsonProperty("TimeUnitForQuarantinePeriod")
	private String timeUnitForQuarantinePeriod;
	@JsonProperty("QualityInspectionGroup")
	private String qualityInspectionGroup;
	@JsonProperty("AuthorizationGroup")
	private String authorizationGroup;
	@JsonProperty("DocumentIsCreatedByCAD")
	private Boolean documentIsCreatedByCAD;
	@JsonProperty("HandlingUnitType")
	private String handlingUnitType;
	@JsonProperty("HasVariableTareWeight")
	private Boolean hasVariableTareWeight;
	@JsonProperty("MaximumPackagingLength")
	private String maximumPackagingLength;
	@JsonProperty("MaximumPackagingWidth")
	private String maximumPackagingWidth;
	@JsonProperty("MaximumPackagingHeight")
	private String maximumPackagingHeight;
	@JsonProperty("UnitForMaxPackagingDimensions")
	private String unitForMaxPackagingDimensions;
}
