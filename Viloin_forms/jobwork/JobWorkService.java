package com.altrocks.forms.jobwork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.altrocks.forms.incoming.InspectionAPIListService;

import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class JobWorkService {
	
	@Autowired
	InspectionAPIListService apiListService;
	
	@Autowired
	ApiService apiService;
	
	@Autowired
	ApiParameters apiParameters;
	

	
	public List<purchaseOrderPropertiesDAO> getJobworkList(String purchaseOrder)
			throws ParseException, UnirestException, IOException, InterruptedException, ExecutionException {

		List<purchaseOrderPropertiesDAO> purchaseOrderHeader = apiParameters.purchaseOrderAPI(purchaseOrder);
//		List<purchaseOrderPropertiesDAO> purchaseorderlist = purchaseOrderHeader.get();

		// ----------------Filter Functions-----------------------

		List<purchaseOrderPropertiesDAO> afterFilterList = purchaseOrderHeader;

		if (!purchaseOrder.isEmpty()) {
			afterFilterList = afterFilterList.stream()
					.filter(e -> e.getPurchaseOrder().equalsIgnoreCase(purchaseOrder)).collect(Collectors.toList());
		}

//		System.out.println("AfterFilterList ==>" + afterFilterList.size());

		for (purchaseOrderPropertiesDAO objList : afterFilterList) {

			objList.setPurchaseOrder1(purchaseOrder);
		}

		return afterFilterList;

	}

//	public void saveJobWork(String reservation)
//			throws ParseException, UnirestException, IOException, InterruptedException, ExecutionException {
//		List<purchaseOrderPropertiesDAO> jobworkList = getJobworkList(reservation);
//		CompletableFuture<List<PlantAddressPropertiesDAO>> plantAddressAsync = dNoteSApiService.getPlantAddressAsync();
//		
//		List<PlantAddressPropertiesDAO> plantAddressList = plantAddressAsync.get();
//		
//		
//		
//		ArrayList<MRINEntity> objList = new ArrayList<>();
//
//		for (purchaseOrderPropertiesDAO obj : jobworkList) {
//
//
////			List<ReservationDocLinePropertiesDAO> matchingLines = reservationDocumentLine.stream()
////					.filter(e -> e.getReservation().equalsIgnoreCase(reservationNumber)).collect(Collectors.toList());
//
//				String getplant = plantAddressList.stream()
//						.filter(e -> e.getR.equalsIgnoreCase(obj.getReservation())).map(e -> e.getPlant())
//						.findFirst().orElse("");
//				
//			
//						objList.add(mrin);
//			}
//		}
//		// System.out.println("objList:"+objList);
//
//		
//
//		List<MRINEntity> finalList = new ArrayList<>();
//	
//		List<MRINEntity> objFilterList = finalList;
//
//		mrinRepository.saveAll(objFilterList);
//	}
	
}
