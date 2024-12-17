package com.example.demo.mrptype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties({"link","id","title","category","updated"})
public class MrpTypeDAO {

	@JsonProperty(value="content")
	private MrpTypeContentDAO content;
}

