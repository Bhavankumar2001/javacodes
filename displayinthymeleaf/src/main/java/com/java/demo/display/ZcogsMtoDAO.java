package com.java.demo.display;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
@JsonIgnoreProperties({"link","id","title","category","updated"})
public class ZcogsMtoDAO {
	@JsonProperty("content")
	public contentMtoDAO content;
}
