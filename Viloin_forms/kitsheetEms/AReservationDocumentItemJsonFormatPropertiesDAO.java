package com.altrocks.forms.kitsheetEms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AReservationDocumentItemJsonFormatPropertiesDAO {
	
	@JsonProperty("MatlCompRequirementDate")
	private String dMatlCompRequirementDate;
	@JsonProperty("PurchasingDocumentItem")
	private Integer dPurchasingDocumentItem;
	@JsonProperty("ReservationItemText")
	private String dReservationItemText;
	@JsonProperty("GoodsRecipientName")
	private String dGoodsRecipientName;
	@JsonProperty("CompanyCodeCurrency")
	private String dCompanyCodeCurrency;
	@JsonProperty("StorageLocation")
	private String dStorageLocation;
	@JsonProperty("GoodsMovementType")
	private String dGoodsMovementType;
	@JsonProperty("RequirementType")
	private String dRequirementType;
	@JsonProperty("ReservationItemIsFinallyIssued")
	private Boolean dReservationItemIsFinallyIssued;
	@JsonProperty("ResvnItmRequiredQtyInEntryUnit")
	private double dResvnItmRequiredQtyInEntryUnit;
	@JsonProperty("ResvnItmWithdrawnAmtInCCCrcy")
	private double dResvnItmWithdrawnAmtInCCCrcy;
	@JsonProperty("GoodsMovementIsAllowed")
	private Boolean dGoodsMovementIsAllowed;
	@JsonProperty("IssuingOrReceivingPlant")
	private String dIssuingOrReceivingPlant;
	@JsonProperty("ReservationItem")
	private Integer dReservationItem;
	@JsonProperty("BaseUnit")
	private String dBaseUnit;
	@JsonProperty("DebitCreditCode")
	private String dDebitCreditCode;
	@JsonProperty("QuantityIsFixed")
	private Boolean dQuantityIsFixed;
	@JsonProperty("Supplier")
	private String dSupplier;
	@JsonProperty("GLAccount")
	private Integer dGLAccount;
	@JsonProperty("UnloadingPointName")
	private String dUnloadingPointName;
	@JsonProperty("PurchasingDocument")
	private String dPurchasingDocument;
	@JsonProperty("ManufacturingOrderOperation")
	private String dManufacturingOrderOperation;
	@JsonProperty("ResvnItmRequiredQtyInBaseUnit")
	private String dResvnItmRequiredQtyInBaseUnit;
	@JsonProperty("RecordType")
	private String dRecordType;
	@JsonProperty("IssuingOrReceivingStorageLoc")
	private String dIssuingOrReceivingStorageLoc;
	@JsonProperty("ResvnItmWithdrawnQtyInBaseUnit")
	private Double dResvnItmWithdrawnQtyInBaseUnit;
	@JsonProperty("Plant")
	private String Plant;
	@JsonProperty("ReservationItmIsMarkedForDeltn")
	private Boolean dReservationItmIsMarkedForDeltn;
	@JsonProperty("ResvnAccountIsEnteredManually")
	private Boolean dResvnAccountIsEnteredManually;
	@JsonProperty("EntryUnit")
	private String dEntryUnit;
	@JsonProperty("Reservation")
	private String dReservation;
	@JsonProperty("Product")
	private String dProduct;
	@JsonProperty("Batch")
	private String dBatch;

}
