package com.altrocks.forms.kitsheetEms;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Kitsheet_Ems")
public class ProductionHeaderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // or any strategy you're using
	private Long id;
	String plantName;
	public String productionNo;
	public String orderQuantity;
	public String reservationNo;
	public String salesOrder;
	public String salesorderItem;
	public String assemblypartNo;
	public String partOldNo;
	public String oldPartNo;
	public String assemblyName;
	public Date createdOn;

	public String partNo;
	public String description;
	public String uom;
	public String requiredQty;
	public String qtyAssem;

	String kitqty;

	String batchno;

	String storestock;
	private String sortPartNo;

	String binLocation;
	String headerProduction;
	String finalProdOrdersQty;
	String finalReservations;
	Integer lineId;

}
