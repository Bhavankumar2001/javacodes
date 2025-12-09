package com.altrocks.forms.mrin;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="mrin")
public class MRINEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="line_id")
	private Integer lineId;
	
	@Column(name="reservation")
	private String reservation;
	
	@Column(name="department")
	private String department;
	
	@Column(name="mrin_no")
	private String mrinNo;
	
	@Column(name="mrin_date")
	private String mrinDate;
	
	
	@Column(name="req_name")
	private String reqName;
	
	@Column(name="plant_name")
	private String plantName;
	
	@Column(name="part_number")
	private String partNumber;
	
	
	@Column(name="item_name")
	private String itemName;
	
	@Column(name="required_qty")
	private String requiredQty;
	
	@Column(name="unit_price")
	private String unitPrice;
	
	
	
	@Column(name="batch_no")
	private String batchNo;
	
	@Column(name="store_stock")
	private String storeStock;
	
	@Column(name="location")
	private String location;
	
	@Column(name="storage_bin")
	private String storageBin;
	
	@Column(name="total_price")
	private String totalPrice;
	
	
	@Column(name="plant")
	private String plant;
	
	@Column(name="material")
	private String material;
	
	@Column(name="cost_center")
	private String costCenter;
	
	@Column(name="title")
	private String title;
	
	@Column(name="remarks")
	private String remarks;
	

	@Column(name="baseunit")
	private String baseunit;
	

}
