package com.java.demo.display;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties({ "xmlns:d", "xmlns:m", "d:ID", "d:IsReversed", "d:IsReversal", "d:IsReversed_1",
		"d:IsReversal_1" ,"d:BillingDocumentDate","d:DocumentDate","d:PostingDate","d:SalesGroupName","d:SalesGroup"})


public class ZCOGSPropertiesDAO {
	@JsonProperty("d:BillingDocument_1")
	private String billingDocument_1;
	@JsonProperty("d:BillingQuantityUnit")
	private String billingQuantityUnit;
	@JsonProperty("d:NetAmount")
	private String netAmount;
	@JsonProperty("d:ProductGroup")
	private String productGroup;
	@JsonProperty("d:BillingDocumentType")
	private String billingDocumentType;
	@JsonProperty("d:ReferenceSDDocument")
	private String referenceSDDocument;
	@JsonProperty("d:SalesOrder")
	private String salesOrder;
	@JsonProperty("d:BPCustomerName")
	private String bpCustomerName;
	@JsonProperty("d:QuantityInBaseUnit")
	private String quantityInBaseUnit;
	@JsonProperty("d:TransactionCurrency")
	private String transactionCurrency;
	@JsonProperty("d:TotalGoodsMvtAmtInCCCrcy")
	private String totalGoodsMvtAmtInCCCrcy;
	@JsonProperty("d:SalesDocumentItem")
	private String salesDocumentItem;
	@JsonProperty("d:BillingDocumentItemText")
	private String billingDocumentItemText;
	@JsonProperty("d:SalesOffice")
	private String salesOffice;
//	@JsonProperty("d:BillingDocumentDate")
//	private String billingDocumentDate;
	@JsonProperty("xmlns:d")
	private String xmlnsD;
	@JsonProperty("d:Country_1")
	private String country_1;
//	@JsonProperty("d:DocumentDate")
//	private String documentDate;
	@JsonProperty("d:AccountingDocument")
	private String accountingDocument;
	@JsonProperty("d:CityNumber")
	private String cityNumber;
	@JsonProperty("xmlns:m")
	private String xmlnsM;
	@JsonProperty("d:ZPF2VALUE")
	private String zpf2Value;
	@JsonProperty("d:ZLSDRATEVALUE")
	private String zlsdRateValue;
	@JsonProperty("d:DeliveryDocumentItem")
	private String deliveryDocumentItem;
	@JsonProperty("d:ZPF2RATEVALUE")
	private String zpf2RateValue;
	@JsonProperty("d:Street")
	private String street;
	@JsonProperty("d:ProfitCenter")
	private String profitCenter;
	@JsonProperty("d:ZFI2RATEVALUE")
	private String zfi2RateValue;
	@JsonProperty("d:Floor")
	private String floor;
	@JsonProperty("d:Region")
	private String region;
	@JsonProperty("d:DocumentReferenceID")
	private String documentReferenceID;
	@JsonProperty("d:StreetName")
	private String streetName;
	@JsonProperty("d:IsReversal_1")
	private String isReversal_1;
	@JsonProperty("d:MaterialDocument")
	private String materialDocument;
	@JsonProperty("d:PostalCode")
	private String postalCode;
	@JsonProperty("d:ZPF1VALUE")
	private String zpf1Value;
//	@JsonProperty("d:PostingDate")
//	private String postingDate;
	@JsonProperty("d:ReverseDocumentFiscalYear")
	private String reverseDocumentFiscalYear;
	@JsonProperty("d:ReferenceSDDocumentItem")
	private String referenceSDDocumentItem;
	@JsonProperty("d:Plant_1")
	private String plant_1;
	@JsonProperty("d:ReversalReason")
	private String reversalReason;
	@JsonProperty("d:SalesOrderItem")
	private String salesOrderItem;
	@JsonProperty("d:MaterialBaseUnit")
	private String materialBaseUnit;
	@JsonProperty("d:MaterialABCClassificationDesc")
	private String materialABCClassificationDesc;
	@JsonProperty("d:GINUMBER")
	private String giNumber;
	@JsonProperty("d:Plant")
	private String plant;
	@JsonProperty("d:AdditionalCustomerGroup2")
	private String additionalCustomerGroup2;
	@JsonProperty("d:AdditionalCustomerGroup1")
	private String additionalCustomerGroup1;
	@JsonProperty("d:CustomerName")
	private String customerName;
	@JsonProperty("d:SalesDocument")
	private String salesDocument;
	@JsonProperty("d:Product")
	private String product;
	@JsonProperty("d:ReferenceSDDocumentCategory")
	private String referenceSDDocumentCategory;
	@JsonProperty("d:ZFI4VALUE")
	private String zfi4Value;
	@JsonProperty("d:ZFI1RATEVALUE")
	private String zfi1RateValue;
	@JsonProperty("d:ZFI1VALUE")
	private String zfi1Value;
	@JsonProperty("d:BillingQuantity")
	private String billingQuantity;
	@JsonProperty("d:AccountingDocument_1")
	private String accountingDocument_1;
	@JsonProperty("d:CityName")
	private String cityName;
	@JsonProperty("d:POBox")
	private String pOBox;
	@JsonProperty("d:OriginalReferenceDocument")
	private String originalReferenceDocument;
	@JsonProperty("d:BillingDocument")
	private String billingDocument;
	@JsonProperty("d:CompanyCodeCurrency")
	private String companyCodeCurrency;
	@JsonProperty("d:ZPF1RATEVALUE")
	private String zpf1RateValue;
	@JsonProperty("d:ZFI2VALUE")
	private String zfi2Value;
	@JsonProperty("d:ZFI3RATEVALUE")
	private String zfi3RateValue;
	@JsonProperty("d:HouseNumber")
	private String houseNumber;
	@JsonProperty("d:SoldToParty")
	private String soldToParty;
	@JsonProperty("d:ReverseDocument")
	private String reverseDocument;
	@JsonProperty("d:CancelledBillingDocument")
	private String cancelledBillingDocument;
	@JsonProperty("d:IsReversed_1")
	private String isReversed_1;
	@JsonProperty("d:DeliveryDocument")
	private String deliveryDocument;
	@JsonProperty("d:ABCIndicator")
	private String abcIndicator;
	@JsonProperty("d:ID")
	private String id;
	@JsonProperty("d:StreetPrefixName2")
	private String streetPrefixName2;
	@JsonProperty("d:BillingDocumentIsCancelled")
	private String billingDocumentIsCancelled;
	@JsonProperty("d:Segment")
	private String segment;
	@JsonProperty("d:StreetPrefixName1")
	private String streetPrefixName1;
	@JsonProperty("d:ZFI3VALUE")
	private String zfi3Value;
	@JsonProperty("d:BillingDocumentItem")
	private String billingDocumentItem;
	@JsonProperty("d:AdditionalCustomerGroup2Name")
	private String additionalCustomerGroup2Name;
	@JsonProperty("d:Division")
	private String division;
	@JsonProperty("d:Country")
	private String country;
	@JsonProperty("d:CompanyCode_1")
	private String companyCode_1;
	@JsonProperty("d:StreetSuffixName1")
	private String streetSuffixName1;
	@JsonProperty("d:DocumentReferenceID_1")
	private String documentReferenceID_1;
	@JsonProperty("d:StreetSuffixName2")
	private String streetSuffixName2;
	@JsonProperty("d:ZFI4RATEVALUE")
	private String zfi4RateValue;
	@JsonProperty("d:ZLSDVALUE")
	private String zlsdValue;
	@JsonProperty("d:IsReversal")
	private String isReversal;
	@JsonProperty("d:BillingDocumentCategory")
	private String billingDocumentCategory;
	@JsonProperty("d:IsReversed")
	private String isReversed;
	@JsonProperty("d:AdditionalCustomerGroup1Name")
	private String additionalCustomerGroup1Name;
	
	private String secondApiValue;

}
