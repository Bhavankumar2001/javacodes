package com.altrocks.forms.mrin;

import com.altrocks.forms.kitsheetindent.WareHouseContentDAO;
import com.altrocks.forms.kitsheetindent.WareHousePropertiesDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovingPriceContentDAO {

	@JsonProperty(value="m:properties")
	private MovingPricePropertiesDAO  properties;
}
