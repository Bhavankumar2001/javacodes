package com.example.demo.riverrider;

import java.util.List;

import lombok.Data;

@Data
public class ProductPlantMapping {

	private String product;
	private String productgroup;
	private String productdescription;
	private List<Plant> plant;
}
