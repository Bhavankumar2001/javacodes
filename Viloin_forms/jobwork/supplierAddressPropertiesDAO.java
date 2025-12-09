package com.altrocks.forms.jobwork;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class supplierAddressPropertiesDAO {

	@JsonProperty("Supplier")
	private String supplier;
	@JsonProperty("SupplierName")
	private String supplierName;
	@JsonProperty("BusinessPartnerName1")
	private String businessPartnerName1;
	@JsonProperty("BusinessPartnerName2")
	private String businessPartnerName2;
	@JsonProperty("BusinessPartnerName3")
	private String businessPartnerName3;
	private String businessPartnerName4;
	@JsonProperty("BPAddrCityName")
	private String bPAddrCityName;
	@JsonProperty("BPAddrStreetName")
	private String bPAddrStreetName;
	@JsonProperty("StreetPrefixName1")
	private String streetPrefixName1;
	@JsonProperty("StreetPrefixName2")
	private String streetPrefixName2;
	@JsonProperty("StreetSuffixName1")
	private String streetSuffixName1;
	@JsonProperty("StreetSuffixName2")
	private String streetSuffixName2;
	@JsonProperty("DistrictName")
	private String districtName;
	@JsonProperty("CityName")
	private String cityName;
	@JsonProperty("PostalCode")
	private String postalCode;
	@JsonProperty("TaxNumber3")
	private String taxNumber3;
	@JsonProperty("HouseNumber")
	private String houseNumber;
	@JsonProperty("EmailAddress")
	private String emailAddress;
	@JsonProperty("Region")
	private String region;

}
