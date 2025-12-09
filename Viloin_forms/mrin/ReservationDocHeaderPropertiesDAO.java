package com.altrocks.forms.mrin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationDocHeaderPropertiesDAO {

	@JsonProperty("d:Reservation")
	private String reservation;
	@JsonProperty("d:OrderID")
	private String orderID;
	@JsonProperty("d:GoodsMovementType")
	private String goodsMovementType;
	@JsonProperty("d:CostCenter")
	private String costCenter;
	@JsonProperty("d:GoodsRecipientName")
	private String goodsRecipientName;
	@JsonProperty("d:ReservationDate")
	private JsonNode reservationDate;
	@JsonProperty("d:IsCheckedAgainstFactoryCal")
	private Boolean isCheckedAgainstFactoryCal;
	@JsonProperty("d:Customer")
	private String customer;
	@JsonProperty("d:WBSElement")
	private String wBSElement;
	@JsonProperty("d:ControllingArea")
	private String controllingArea;
	@JsonProperty("d:SalesOrder")
	private String salesOrder;
	@JsonProperty("d:SalesOrderItem")
	private String salesOrderItem;
	@JsonProperty("d:SalesOrderScheduleLine")
	private String salesOrderScheduleLine;
	@JsonProperty("d:AssetNumber")
	private String assetNumber;
	@JsonProperty("d:AssetSubNumber")
	private String assetSubNumber;
	@JsonProperty("d:NetworkNumberForAcctAssgmt")
	private String networkNumberForAcctAssgmt;
	@JsonProperty("d:IssuingOrReceivingPlant")
	private String issuingOrReceivingPlant;
	@JsonProperty("d:IssuingOrReceivingStorageLoc")
	private String issuingOrReceivingStorageLoc;

	private String reservationDateUpdate;

	private String reservationNumber;

	private String plant;

	private String material;

	private String costCenterNew;

}
