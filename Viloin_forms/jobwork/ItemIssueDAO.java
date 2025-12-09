package com.altrocks.forms.jobwork;

import lombok.Data;

@Data
public class ItemIssueDAO {
	
	public String itemCode;
	public String particulars;
	public String hsnCode;
	public String weight;
	public Double qty;
	public String uom;
	public Double netWt;
	public Double rate;
	public Double amount;

}
