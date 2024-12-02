package com.java.demo.reports.electraev;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties({"id","title","category","updated","link"})
public class SupplierPerfHeaderDAO {
	@JsonProperty("content")
	public SupplierHeaderContentDAO content;

}
