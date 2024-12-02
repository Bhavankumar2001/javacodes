package com.java.demo.display;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

@Data
@JsonIgnoreProperties({"link","id","title","category","updated"})
public class ZcogsDAO {
	@JsonProperty("content")
	public contentDAO content;
}
