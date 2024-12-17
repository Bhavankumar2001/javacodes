package com.example.demo.riverrider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties({"link","id","title","category","updated"})
public class AProductDAO {

	@JsonProperty(value="content")
	private AProductContentDAO content;
}
