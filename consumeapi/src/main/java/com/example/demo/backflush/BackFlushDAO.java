package com.example.demo.backflush;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties({"link","id","title","category","updated"})
public class BackFlushDAO {

	@JsonProperty(value="content")
	private BackFlushContentDAO content;
}
