package com.example.demo.mrpcontroller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties({"link","id","title","category","updated"})
public class MrpDAO {

	@JsonProperty(value="content")
	private MrpContentDAO content;
}
