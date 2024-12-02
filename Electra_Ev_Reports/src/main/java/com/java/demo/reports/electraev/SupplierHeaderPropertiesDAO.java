package com.java.demo.reports.electraev;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties({"xmlns:d","xmlns:m"})
public class SupplierHeaderPropertiesDAO {
	@JsonProperty("d:DebitCreditCode")
	public String dDebitCreditCode;
	@JsonProperty("d:Material")
	public String dMaterial;
	@JsonProperty("d:Supplier")
	public String dSupplier;
	@JsonProperty("d:MaterialDocument")
	public String dMaterialDocument;
	@JsonProperty("d:ProductName")
	public String dProductName;
	@JsonProperty("d:GoodsMovementType")
	public String dGoodsMovementType;
	@JsonProperty("d:MaterialDocumentItem")
	public String dMaterialDocumentItem;
	@JsonProperty("d:MaterialDocumentYear")
	public String dMaterialDocumentYear;

	@JsonProperty("d:SupplierFullName")
	public String dSupplierFullName;
	@JsonProperty("d:QuantityInEntryUnit")
	public Double dQuantityInEntryUnit;
	@JsonProperty("d:EntryUnit")
	public String dEntryUnit;
	@JsonProperty("d:ID")
	public String dID;
	@JsonProperty("d:PostingDate")
	public Date dPostingDate;
	@JsonProperty("d:FiscalYearPeriod")
	public String dFiscalYearPeriod;
	
	
	public Double Unitentry350;
	public Double Unitentry321;
	public Double Sumation;
	public Double line;
	public Double customer;
	public Double warrenty;
	public Double premium;
	
	   private String id;
	public Double Qualityrating;
	public Double Deliveryrating;
	public Double supplierrating;
}
