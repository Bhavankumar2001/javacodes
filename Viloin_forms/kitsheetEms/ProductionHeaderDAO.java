package com.altrocks.forms.kitsheetEms;

import java.util.Date;

import lombok.Data;

@Data
public class ProductionHeaderDAO {
	public String productionNo;
	public String orderQuantity;
	public String reservationNo;
	public String salesOrder;
	public String salesorderItem;
	public String assemblypartNo;
	public String assemblyName;
	public Date createdOn;

	public String partNo;
	public String description;
	public String uom;
	
	String kitqty;

	String batchno;

	String storestock;

	String binLocation;

}
