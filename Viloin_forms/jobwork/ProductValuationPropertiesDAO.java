package com.altrocks.forms.jobwork;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductValuationPropertiesDAO {
	
	@JsonProperty("Product")
	private String product;
	@JsonProperty("ValuationArea")
	private String valuationArea;
	@JsonProperty("ValuationType")
	private String valuationType;
	@JsonProperty("ValuationClass")
	private String valuationClass;
	@JsonProperty("PriceDeterminationControl")
	private String priceDeterminationControl;
	@JsonProperty("StandardPrice")
	private Double standardPrice;
	@JsonProperty("PriceUnitQty")
	private String priceUnitQty;
	@JsonProperty("InventoryValuationProcedure")
	private String inventoryValuationProcedure;
	@JsonProperty("IsMarkedForDeletion")
	private Boolean isMarkedForDeletion;
	@JsonProperty("MovingAveragePrice")
	private Double movingAveragePrice;
	@JsonProperty("ValuationCategory")
	private String valuationCategory;
	@JsonProperty("ProductUsageType")
	private String productUsageType;
	@JsonProperty("ProductOriginType")
	private String productOriginType;
	@JsonProperty("IsProducedInhouse")
	private Boolean isProducedInhouse;
	@JsonProperty("ProdCostEstNumber")
	private String prodCostEstNumber;
	@JsonProperty("ProjectStockValuationClass")
	private String projectStockValuationClass;
	@JsonProperty("ValuationClassSalesOrderStock")
	private String valuationClassSalesOrderStock;
	@JsonProperty("PlannedPrice1InCoCodeCrcy")
	private String plannedPrice1InCoCodeCrcy;
	@JsonProperty("PlannedPrice2InCoCodeCrcy")
	private String plannedPrice2InCoCodeCrcy;
	@JsonProperty("PlannedPrice3InCoCodeCrcy")
	private String plannedPrice3InCoCodeCrcy;
	@JsonProperty("FuturePlndPrice1ValdtyDate")
	private Object futurePlndPrice1ValdtyDate;
	@JsonProperty("FuturePlndPrice2ValdtyDate")
	private Object futurePlndPrice2ValdtyDate;
	@JsonProperty("FuturePlndPrice3ValdtyDate")
	private Object futurePlndPrice3ValdtyDate;
	@JsonProperty("TaxBasedPricesPriceUnitQty")
	private String taxBasedPricesPriceUnitQty;
	@JsonProperty("PriceLastChangeDate")
	private Object priceLastChangeDate;
	@JsonProperty("PlannedPrice")
	private String plannedPrice;
	@JsonProperty("PrevInvtryPriceInCoCodeCrcy")
	private String prevInvtryPriceInCoCodeCrcy;
	@JsonProperty("Currency")
	private String currency;
	@JsonProperty("BaseUnit")
	private String baseUnit;

}
