package com.altrocks.forms.kitsheetEms;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AReservationDocumentItemPropertiesDAO {
	
	@JsonProperty("d:MatlCompRequirementDate")
	private String dMatlCompRequirementDate;
	@JsonProperty("d:PurchasingDocumentItem")
	private Integer dPurchasingDocumentItem;
	@JsonProperty("d:ReservationItemText")
	private String dReservationItemText;
	@JsonProperty("d:GoodsRecipientName")
	private String dGoodsRecipientName;
	@JsonProperty("d:CompanyCodeCurrency")
	private String dCompanyCodeCurrency;
	@JsonProperty("d:StorageLocation")
	private Integer dStorageLocation;
	@JsonProperty("d:GoodsMovementType")
	private String dGoodsMovementType;
	@JsonProperty("d:RequirementType")
	private String dRequirementType;
	@JsonProperty("d:ReservationItemIsFinallyIssued")
	private Boolean dReservationItemIsFinallyIssued;
	@JsonProperty("d:ResvnItmRequiredQtyInEntryUnit")
	private Integer dResvnItmRequiredQtyInEntryUnit;
	@JsonProperty("d:ResvnItmWithdrawnAmtInCCCrcy")
	private Integer dResvnItmWithdrawnAmtInCCCrcy;
	@JsonProperty("d:GoodsMovementIsAllowed")
	private Boolean dGoodsMovementIsAllowed;
	@JsonProperty("xmlns:d")
	private String xmlnsD;
	@JsonProperty("d:IssuingOrReceivingPlant")
	private String dIssuingOrReceivingPlant;
	@JsonProperty("d:ReservationItem")
	private Integer dReservationItem;
	@JsonProperty("d:BaseUnit")
	private String dBaseUnit;
	@JsonProperty("xmlns:m")
	private String xmlnsM;
	@JsonProperty("d:DebitCreditCode")
	private String dDebitCreditCode;
	@JsonProperty("d:QuantityIsFixed")
	private Boolean dQuantityIsFixed;
	@JsonProperty("d:Supplier")
	private String dSupplier;
	@JsonProperty("d:GLAccount")
	private Integer dGLAccount;
	@JsonProperty("d:UnloadingPointName")
	private String dUnloadingPointName;
	@JsonProperty("d:PurchasingDocument")
	private String dPurchasingDocument;
	@JsonProperty("d:ManufacturingOrderOperation")
	private String dManufacturingOrderOperation;
	@JsonProperty("d:ResvnItmRequiredQtyInBaseUnit")
	private String dResvnItmRequiredQtyInBaseUnit;
	@JsonProperty("d:RecordType")
	private String dRecordType;
	@JsonProperty("d:IssuingOrReceivingStorageLoc")
	private String dIssuingOrReceivingStorageLoc;
	@JsonProperty("d:ResvnItmWithdrawnQtyInBaseUnit")
	private Integer dResvnItmWithdrawnQtyInBaseUnit;
	@JsonProperty("d:Plant")
	private Integer dPlant;
	@JsonProperty("d:ReservationItmIsMarkedForDeltn")
	private Boolean dReservationItmIsMarkedForDeltn;
	@JsonProperty("d:ResvnAccountIsEnteredManually")
	private Boolean dResvnAccountIsEnteredManually;
	@JsonProperty("d:EntryUnit")
	private String dEntryUnit;
	@JsonProperty("d:Reservation")
	private String dReservation;
	@JsonProperty("d:Product")
	private String dProduct;
	@JsonProperty("d:Batch")
	private String dBatch;

}
