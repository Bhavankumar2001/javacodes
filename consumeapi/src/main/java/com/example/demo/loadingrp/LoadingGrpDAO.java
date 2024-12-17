package com.example.demo.loadingrp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

@Data
@JsonIgnoreProperties({"link","id","title","category","updated"})
public class LoadingGrpDAO {
	
	@JsonProperty(value="content")
	private LoadingGrpContentDAO content;

}
