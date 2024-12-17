package com.example.demo.utils;

import org.springframework.context.annotation.Description;

public class APIConstants {

	@Description(value = "Masters API")
	public static class masterReport {
		// Access the static keyword constant without creating an object
		//To access the variable, you must create an  object(instance)
 		public static final String YY1_LOADINGGRP = "api.s4hana.cloud.sap/sap/opu/odata/sap/YY1_LOADINGGRP_CDS/YY1_LOADINGGRP";
 		public static final String YY1_MRPTYPE = "api.s4hana.cloud.sap/sap/opu/odata/sap/YY1_MRPTYPE_CDS/YY1_MRPTYPE";
 		public static final String YY1_MRPCONTROLLER = "api.s4hana.cloud.sap/sap/opu/odata/sap/YY1_MRPCONTROLLER_CDS/YY1_MRPCONTROLLER";
 		public static final String YY1_LOTSIZINGPROCEDURE = "api.s4hana.cloud.sap/sap/opu/odata/sap/YY1_LOTSIZINGPROCEDURE_CDS/YY1_LOTSIZINGPROCEDURE";
 		public static final String YY1_PROCUREMENTTYPE = "api.s4hana.cloud.sap/sap/opu/odata/sap/YY1_PROCUREMENTTYPE_CDS/YY1_PROCUREMENTTYPE";
 		public static final String YY1_SPECIALPROCUREMENTTYPE = "api.s4hana.cloud.sap/sap/opu/odata/sap/YY1_SPECIALPROCUREMENTTYPE_CDS/YY1_SPECIALPROCUREMENTTYPE";
 		public static final String YY1_BFLUSH = "api.s4hana.cloud.sap/sap/opu/odata/sap/YY1_BFLUSH_CDS/YY1_BFLUSH";
 		public static final String YY1_STOCKDETERMINATIONGROU = "api.s4hana.cloud.sap/sap/opu/odata/sap/YY1_STOCKDETERMINATIONGROU_CDS/YY1_STOCKDETERMINATIONGROU";
 		public static final String A_Product = "api.s4hana.cloud.sap/sap/opu/odata/sap/API_PRODUCT_SRV/A_Product";
 		public static final String A_ProductDescription = "api.s4hana.cloud.sap/sap/opu/odata/sap/API_PRODUCT_SRV/A_ProductDescription";
 		public static final String A_ProductPlant = "api.s4hana.cloud.sap/sap/opu/odata/sap/API_PRODUCT_SRV/A_ProductPlant";

	}

}
