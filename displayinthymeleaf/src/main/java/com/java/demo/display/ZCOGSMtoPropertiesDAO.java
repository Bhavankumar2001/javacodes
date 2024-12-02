package com.java.demo.display;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@JsonIgnoreProperties({ "xmlns:d", "xmlns:m", "d:PostingDate", "d:DocumentDate","d:TotalGoodsMvtAmtInCCCrcy" })

public class ZCOGSMtoPropertiesDAO {
	@JsonProperty("d:ID")
    private String id;

    @JsonProperty("d:PurchaseOrder")
    private String purchaseOrder;

    @JsonProperty("d:PurchaseOrderItem")
    private String purchaseOrderItem;

//    @JsonProperty("d:TotalGoodsMvtAmtInCCCrcy")
//    private String totalGoodsMvtAmtInCCCrcy;

    @JsonProperty("d:BillingDocument")
    private String billingDocument;

    @JsonProperty("d:BillingDocumentItem")
    private String billingDocumentItem;

    @JsonProperty("d:SalesOrder")
    private String salesOrder;

    @JsonProperty("d:SalesOrderItem")
    private String salesOrderItem;

    @JsonProperty("d:CompanyCodeCurrency")
    private String companyCodeCurrency;

    @JsonProperty("d:BillingDocumentItemText")
    private String billingDocumentItemText;

    @JsonProperty("d:SalesDocumentItemCategory")
    private String salesDocumentItemCategory;

    @JsonProperty("d:MaterialDocument")
    private String materialDocument;

//    @JsonProperty("d:PostingDate")
//    private String postingDate;
//
//    @JsonProperty("d:DocumentDate")
//    private String documentDate;

    @JsonProperty("d:AccountingDocument")
    private String accountingDocument;

    @JsonProperty("d:GRNUMBER")
    private String grNumber;
}
