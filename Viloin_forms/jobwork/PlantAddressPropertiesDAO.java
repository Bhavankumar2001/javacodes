package com.altrocks.forms.jobwork;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlantAddressPropertiesDAO {

	@JsonProperty("Plant")
	private String plant;
	@JsonProperty("PlantName")
	private String plantName;
	@JsonProperty("Country")
	private String country;
	@JsonProperty("Region")
	private String region;
	@JsonProperty("CityName")
	private String cityName;
	@JsonProperty("Street")
	private String street;
	@JsonProperty("StreetName")
	private String streetName;
	@JsonProperty("StreetPrefixName1")
	private String streetPrefixName1;
	@JsonProperty("StreetPrefixName2")
	private String streetPrefixName2;
	@JsonProperty("StreetSuffixName1")
	private String streetSuffixName1;
	@JsonProperty("StreetSuffixName2")
	private String streetSuffixName2;
	@JsonProperty("PostalCode")
	private String postalCode;
	@JsonProperty("AddresseeFullName")
	private String addresseeFullName;
	@JsonProperty("IN_GSTIdentificationNumber")
	private String iNGSTIdentificationNumber;

}
