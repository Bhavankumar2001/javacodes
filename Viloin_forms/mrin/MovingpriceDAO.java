package com.altrocks.forms.mrin;

import com.altrocks.forms.kitsheetindent.ViolinMaterialContentDAO;
import com.altrocks.forms.kitsheetindent.ViolinMaterialDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovingpriceDAO {
	@JsonProperty(value="content")
	private MovingPriceContentDAO content;

}

