package com.example.demo.riverrider;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AProductPropertiesDAO {

	@JsonProperty("d:SourceOfSupply")
	private String dSourceOfSupply;
	@JsonProperty("d:ProdNoInGenProdInPrepackProd")
	private String dProdNoInGenProdInPrepackProd;
	@JsonProperty("d:SizeOrDimensionText")
	private String dSizeOrDimensionText;
	@JsonProperty("d:CrossPlantStatus")
	private String dCrossPlantStatus;
	@JsonProperty("d:StandardHandlingUnitType")
	private String dStandardHandlingUnitType;
	@JsonProperty("d:TimeUnitForQuarantinePeriod")
	private String dTimeUnitForQuarantinePeriod;
	@JsonProperty("d:ProductGroup")
	private String dProductGroup;
	@JsonProperty("d:IndustryStandardName")
	private String dIndustryStandardName;
	@JsonProperty("d:HandlingUnitType")
	private String dHandlingUnitType;
	@JsonProperty("d:NetWeight")
	private String dNetWeight;
	@JsonProperty("d:AuthorizationGroup")
	private String dAuthorizationGroup;
	@JsonProperty("d:HasVariableTareWeight")
	private String dHasVariableTareWeight;
	@JsonProperty("d:QuarantinePeriod")
	private String dQuarantinePeriod;
	@JsonProperty("d:IsRelevantForHzdsSubstances")
	private String dIsRelevantForHzdsSubstances;
	@JsonProperty("xmlns:d")
	private String xmlnsD;
	@JsonProperty("d:ProductIsConfigurable")
	private String dProductIsConfigurable;
	@JsonProperty("d:MaterialVolume")
	private String dMaterialVolume;
	@JsonProperty("d:LastChangeDateTime")
	private String dLastChangeDateTime;
	@JsonProperty("d:BaseUnit")
	private String dBaseUnit;
	@JsonProperty("d:AdjustmentProfile")
	private String dAdjustmentProfile;
	@JsonProperty("d:LowLevelCode")
	private String dLowLevelCode;
	@JsonProperty("xmlns:m")
	private String xmlnsM;
	@JsonProperty("d:LastChangedByUser")
	private String dLastChangedByUser;
	@JsonProperty("d:GrossWeight")
	private String dGrossWeight;
	@JsonProperty("d:CrossPlantConfigurableProduct")
	private String dCrossPlantConfigurableProduct;
	@JsonProperty("d:SerialNoExplicitnessLevel")
	private String dSerialNoExplicitnessLevel;
	@JsonProperty("d:WarehouseStorageCondition")
	private String dWarehouseStorageCondition;
	@JsonProperty("d:MaximumPackagingHeight")
	private String dMaximumPackagingHeight;
	@JsonProperty("d:ChangeNumber")
	private String dChangeNumber;
	@JsonProperty("d:ManufacturerPartProfile")
	private String dManufacturerPartProfile;
	@JsonProperty("d:MaximumPackagingWidth")
	private String dMaximumPackagingWidth;
	@JsonProperty("d:UnitForMaxPackagingDimensions")
	private String dUnitForMaxPackagingDimensions;
	
	@JsonProperty("d:DocumentIsCreatedByCAD")
	private String dDocumentIsCreatedByCAD;
	@JsonProperty("d:CreatedByUser")
	private String dCreatedByUser;
	
	@JsonProperty("d:WeightUnit")
	private String dWeightUnit;
	@JsonProperty("d:ItemCategoryGroup")
	private String dItemCategoryGroup;
	@JsonProperty("d:Product")
	private String dProduct;
	@JsonProperty("d:CreationDate")
	private String dCreationDate;
	@JsonProperty("d:QltyMgmtInProcmtIsActive")
	private String dQltyMgmtInProcmtIsActive;
	@JsonProperty("d:MaximumPackagingLength")
	private String dMaximumPackagingLength;
	@JsonProperty("d:CountryOfOrigin")
	private String dCountryOfOrigin;
	@JsonProperty("d:CompetitorID")
	private String dCompetitorID;
	@JsonProperty("d:InternationalArticleNumberCat")
	private String dInternationalArticleNumberCat;
	@JsonProperty("d:ProductManufacturerNumber")
	private String dProductManufacturerNumber;
	@JsonProperty("d:IndustrySector")
	private String dIndustrySector;
	@JsonProperty("d:MaterialRevisionLevel")
	private String dMaterialRevisionLevel;
	@JsonProperty("d:VolumeUnit")
	private String dVolumeUnit;
	@JsonProperty("d:WarehouseProductGroup")
	private String dWarehouseProductGroup;
	@JsonProperty("d:ExternalProductGroup")
	private String dExternalProductGroup;
	@JsonProperty("d:ProductStandardID")
	private String dProductStandardID;
	@JsonProperty("d:IsBatchManagementRequired")
	private String dIsBatchManagementRequired;
	@JsonProperty("d:PurchaseOrderQuantityUnit")
	private String dPurchaseOrderQuantityUnit;
	@JsonProperty("d:SerialIdentifierAssgmtProfile")
	private String dSerialIdentifierAssgmtProfile;
	@JsonProperty("d:SerialNumberProfile")
	private String dSerialNumberProfile;
	@JsonProperty("d:IsMarkedForDeletion")
	private String dIsMarkedForDeletion;
	@JsonProperty("d:PreferredUnitOfMeasure")
	private String dPreferredUnitOfMeasure;
	@JsonProperty("d:Division")
	private String dDivision;
	@JsonProperty("d:VarblPurOrdUnitIsActive")
	private String dVarblPurOrdUnitIsActive;
	@JsonProperty("d:ANPCode")
	private String dANPCode;
	@JsonProperty("d:Brand")
	private String dBrand;
	@JsonProperty("d:ManufacturerNumber")
	private String dManufacturerNumber;
	@JsonProperty("d:ProductHierarchy")
	private String dProductHierarchy;
	@JsonProperty("d:ProductOldID")
	private String dProductOldID;
	@JsonProperty("d:IsPilferable")
	private String dIsPilferable;
	@JsonProperty("d:ProcurementRule")
	private String dProcurementRule;
	@JsonProperty("d:ProductType")
	private String dProductType;
	@JsonProperty("d:HandlingIndicator")
	private String dHandlingIndicator;
	@JsonProperty("d:LastChangeDate")
	private String dLastChangeDate;
	@JsonProperty("d:QualityInspectionGroup")
	private String dQualityInspectionGroup;
}
