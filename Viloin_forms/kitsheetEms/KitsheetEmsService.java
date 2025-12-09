package com.altrocks.forms.kitsheetEms;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import com.altrocks.forms.incoming.InspectionAPIListService;
import com.altrocks.forms.incoming.ReservationDocHeaderJsonFormatPropertiesDAO;
import com.altrocks.forms.incoming.ReservationDocHeaderPropertiesDAO;
import com.altrocks.forms.kitsheetindent.KitsheetService;
import com.altrocks.forms.kitsheetindent.ProductionOrderComponent4JsonFormatPropertiesDAO;
import com.altrocks.forms.kitsheetindent.ProductionOrderComponent4PropertiesDAO;
import com.altrocks.forms.kitsheetindent.ViolinMaterialJsonFormatPropertiesDAO;
import com.altrocks.forms.kitsheetindent.ViolinMaterialPropertiesDAO;
import com.altrocks.forms.kitsheetindent.WareHouseJsonFormatPropertiesDAO;
import com.altrocks.forms.kitsheetindent.WareHousePropertiesDAO;
import com.altrocks.forms.master.MasterPlantJsonFormatPropetiesDAO;
import com.altrocks.forms.master.MasterPlantPropetiesDAO;
import com.altrocks.forms.master.MasterService;
import com.altrocks.forms.routecard.APIListService;
import com.altrocks.forms.routecard.ProductionOrderJsonFormatPropertiesDAO;
import com.altrocks.forms.routecard.ProductionOrderPropertiesDAO;
import com.altrocks.forms.utils.UtilsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

//@RestController
@Service
public class KitsheetEmsService {

	@Autowired
	APIListService apiListService;

	@Autowired
	InspectionAPIListService apiListServicemat;

	@Autowired
	UtilsService Utils;

	@Autowired
	MasterService master;

	@Autowired
	KitsheetService kitsheetService;

	@Autowired
	KitsheetEmsService emsService;

	@Autowired
	KitesheetCodeOptimazationService kitesheetOptimizationservice;

	@Autowired
	private ProductionHeaderRepository productionHeaderRepository;

	public List<ProductionOrderJsonFormatPropertiesDAO> getKitsheetEmsList(List<String> productionOrders)
			throws ParseException, UnirestException, IOException, InterruptedException, ExecutionException {

		// String productionOrderNumber = productionOrders.get(0);

		// CompletableFuture<List<ProductionOrderPropertiesDAO>> productionOrderList2 =
		// apiListService.ProductionOrderList();

		// CompletableFuture.allOf(productionOrderList2);
		// List<ProductionOrderPropertiesDAO> productionOrderList
		// =productionOrderList2.get();

		List<ProductionOrderJsonFormatPropertiesDAO> productionOrderList = apiListService
				.ProductionOrderList1(productionOrders);

		// Normalize input
		List<String> normalizedInput = productionOrders.stream().map(s -> s.trim().toLowerCase())
				.collect(Collectors.toList());

		List<ProductionOrderJsonFormatPropertiesDAO> afterFilterList = productionOrderList.stream()
				.filter(e -> e.getManufacturingOrder() != null
						&& normalizedInput.contains(e.getManufacturingOrder().trim().toLowerCase()))
				.collect(Collectors.toList());

		// System.out.println("Filtered List: " + afterFilterList);

		return afterFilterList;
	}

//	public KitsheetEmsWrapper savekitsheetEmsDetails(String productionorder)
//	        throws ParseException, UnirestException, IOException, InterruptedException, ExecutionException {
//
//	    // Async API Calls
//	    CompletableFuture<List<ProductDescriptionPropertiesDAO>> productDescriptionListAsync = apiListService.getProductDescriptionListAsync();
//	    CompletableFuture<List<MaterialBOMItemPropertiesDAO>> materialBOMItem_complitable = apiListService.BomAPI();
//	    CompletableFuture<List<MasterPlantPropetiesDAO>> Completable_plantList = master.plantListAPI();
//
//	    CompletableFuture.allOf(materialBOMItem_complitable, productDescriptionListAsync, Completable_plantList).join();
//
//	    List<MasterPlantPropetiesDAO> plantlist = Completable_plantList.get();
//	    List<ViolinMaterialPropertiesDAO> violinMaterial = kitsheetService.getViolinMaterial();
//	    List<WareHousePropertiesDAO> getwareHouse = kitsheetService.getwareHouse();
//	    List<ReservationDocHeaderPropertiesDAO> reservationDocumentHeader = kitsheetService.getReservationDocumentHeader();
//	    List<ProductionOrderComponent4PropertiesDAO> productionOrderList4 = kitsheetService.getProductionOrderList4();
//	    List<SaleOrderItemPropertiesDAO> salesOrderItemList = getSalesOrderItem();
//	    List<AReservationDocumentItemPropertiesDAO> reservationDocumentItem = getReservationDocumentItem();
//	    List<String> productionList = Utils.salesOrderSeparatedComma(productionorder);
//	    List<ProductionOrderPropertiesDAO> afterFilterList = getKitsheetEmsList(productionList);
//	    List<ProductionOrderPropertiesDAO> productionOrderList = apiListService.getProductionOrderList();
//
//	    List<ProductionHeaderEntity> objList = new ArrayList<>();
//	    StringBuilder prodOrdersQtyStr = new StringBuilder();
//	    StringBuilder reservationsStr = new StringBuilder();
//
//	    Map<String, List<AReservationDocumentItemPropertiesDAO>> globalGroupedMap = new HashMap<>();
//	    Map<String, String> partToOrderMap = new HashMap<>();
//	    Map<String, String> partToReservationMap = new HashMap<>();
//	    Map<String, ProductionOrderPropertiesDAO> partToProductionDetails = new HashMap<>();
//
//	    for (ProductionOrderPropertiesDAO obj : afterFilterList) {
//	        String currentOrder = obj.getManufacturingOrder();
//	        String plantName = plantlist.stream()
//	                .filter(e -> e.getPlant().equalsIgnoreCase(obj.getPlant()))
//	                .map(MasterPlantPropetiesDAO::getPlantName)
//	                .findFirst().orElse("");
//	        String salesOrder = obj.getSalesOrder();
//	        String salesOrderItem = obj.getSalesOrderItem();
//	        String assemblypartNo = salesOrderItem != null ? salesOrderItem.trim() : "";
//	        String assemblyName = salesOrderItemList.stream()
//	                .filter(e -> e.getDSalesOrder().equalsIgnoreCase(salesOrder)
//	                        && e.getDSalesOrderItem().equalsIgnoreCase(salesOrderItem))
//	                .map(SaleOrderItemPropertiesDAO::getDSalesOrderItemText)
//	                .findFirst().orElse("");
//
//	        String reser = reservationDocumentHeader.stream()
//	                .filter(e -> e.getGoodsRecipientName().equalsIgnoreCase(currentOrder))
//	                .map(ReservationDocHeaderPropertiesDAO::getReservation)
//	                .findFirst().orElse("");
//
//	        prodOrdersQtyStr.append(currentOrder).append("(")
//	                .append(obj.getTotalQuantity() != null ? obj.getTotalQuantity().toString() : "0").append("),");
//
//	        if (!reser.isEmpty()) {
//	            reservationsStr.append(reser).append(",");
//	        }
//
//	        List<AReservationDocumentItemPropertiesDAO> filteredItems = reservationDocumentItem.stream()
//	                .filter(e -> e.getDReservation().equalsIgnoreCase(reser))
//	                .collect(Collectors.toList());
//
//	        for (AReservationDocumentItemPropertiesDAO item : filteredItems) {
//	            String key = item.getDProduct() + "||" + item.getDBaseUnit();
//	            globalGroupedMap.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
//	            partToOrderMap.putIfAbsent(key, currentOrder);
//	            partToReservationMap.putIfAbsent(key, reser);
//	            partToProductionDetails.putIfAbsent(key, obj);
//	        }
//	    }
//
//	    List<String> allowedStorageLocations = Arrays.asList("1005", "2005", "3005", "4005", "5005", "6016");
//
//	    int lineIdCounter = 1; // Global line ID counter
//	    Set<String> uniqueLineKeySet = new HashSet<>(); // Tracks unique part+uom+order combos
//
//	    for (Map.Entry<String, List<AReservationDocumentItemPropertiesDAO>> entry : globalGroupedMap.entrySet()) {
//	        String[] keys = entry.getKey().split("\\|\\|");
//	        String partNo = keys[0];
//	        String uom = keys.length > 1 ? keys[1] : "";
//
//	        String description = violinMaterial.stream()
//	                .filter(v -> v.getDProduct().equalsIgnoreCase(partNo))
//	                .map(ViolinMaterialPropertiesDAO::getDMaterialDescription)
//	                .findFirst().orElse("");
//	     
//	        
//	        BigDecimal totalRequiredQty = entry.getValue().stream()
//	        	    .map(AReservationDocumentItemPropertiesDAO::getDResvnItmRequiredQtyInBaseUnit)
//	        	    .map(this::safeParseBigDecimal) // call the helper method
//	        	    .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//	        String currentOrder = partToOrderMap.get(entry.getKey());
//	        String reser = partToReservationMap.get(entry.getKey());
//	        ProductionOrderPropertiesDAO productionDetails = partToProductionDetails.get(entry.getKey());
//
//	        String plantName = plantlist.stream()
//	                .filter(e -> e.getPlant().equalsIgnoreCase(productionDetails.getPlant()))
//	                .map(MasterPlantPropetiesDAO::getPlantName)
//	                .findFirst().orElse("");
//
//	        String salesOrder = productionDetails.getSalesOrder();
//	        String salesOrderItem = productionDetails.getSalesOrderItem();
//	        String assemblypartNo = salesOrderItem != null ? salesOrderItem.trim() : "";
//	        String assemblyName = salesOrderItemList.stream()
//	                .filter(e -> e.getDSalesOrder().equalsIgnoreCase(salesOrder)
//	                        && e.getDSalesOrderItem().equalsIgnoreCase(salesOrderItem))
//	                .map(SaleOrderItemPropertiesDAO::getDSalesOrderItemText)
//	                .findFirst().orElse("");
//
//	        // Key for line ID assignment (currentOrder + partNo + uom)
//	        String partKey = currentOrder + "||" + partNo + "||" + uom;
//	        boolean assignLineId = uniqueLineKeySet.add(partKey); // returns true if newly added
//
//	        List<WareHousePropertiesDAO> batchEntries = getwareHouse.stream()
//	                .filter(e -> e.getDMaterial().equalsIgnoreCase(partNo))
//	                .filter(e -> allowedStorageLocations.contains(e.getDStorageLocation()))
//	                .filter(e -> e.getDBatch() != null && !e.getDBatch().isEmpty())
//	                .collect(Collectors.toList());
//
//	        boolean isFirst = true;
//
//	        Set<String> shownBatchKeys = new HashSet<>(); // For suppressing duplicate batch/stock/bin
//
//	        for (WareHousePropertiesDAO wh : batchEntries) {
//	            ProductionHeaderEntity batchRow = new ProductionHeaderEntity();
//	            batchRow.setHeaderProduction(currentOrder);
//	            batchRow.setPlantName(plantName);
//	            batchRow.setSalesOrder(salesOrder);
//	            batchRow.setSalesorderItem(salesOrderItem);
//	            batchRow.setProductionNo(currentOrder);
//	            batchRow.setOrderQuantity(productionDetails.getTotalQuantity());
//	            batchRow.setAssemblypartNo(assemblypartNo);
//	            batchRow.setAssemblyName(assemblyName);
//	            batchRow.setReservationNo(reser);
//	            
//	            
//
//	            if (isFirst) {
//	                batchRow.setPartNo(partNo);
//	                batchRow.setDescription(description);
//	                batchRow.setUom(uom);
//	                batchRow.setRequiredQty(totalRequiredQty.toPlainString());
//
//	                if (assignLineId) {
//	                    batchRow.setLineId(lineIdCounter++);
//	                }
//
//	                isFirst = false;
//	            } else {
//	                batchRow.setPartNo("");
//	                batchRow.setDescription("");
//	                batchRow.setUom("");
//	                batchRow.setRequiredQty("");
//	            }
//
//	            // 👇 Suppress duplicate batch/stock/bin values
//	            String batchKey = wh.getDBatch() + "||" + wh.getDAvailableEWMStockQty() + "||" + wh.getDEWMStorageBin();
//	            if (shownBatchKeys.add(batchKey)) {
//	                batchRow.setBatchno(wh.getDBatch());
//	                batchRow.setStorestock(wh.getDAvailableEWMStockQty());
//	                batchRow.setBinLocation(wh.getDEWMStorageBin());
//	            } else {
//	                batchRow.setBatchno("");
//	                batchRow.setStorestock("");
//	                batchRow.setBinLocation("");
//	            }
//
//	            objList.add(batchRow);
//	        }
//
//
//	        if (batchEntries.isEmpty()) {
//	            ProductionHeaderEntity fallbackRow = new ProductionHeaderEntity();
//	            fallbackRow.setHeaderProduction(currentOrder);
//	            fallbackRow.setPlantName(plantName);
//	            fallbackRow.setSalesOrder(salesOrder);
//	            fallbackRow.setSalesorderItem(salesOrderItem);
//	            fallbackRow.setProductionNo(currentOrder);
//	            fallbackRow.setOrderQuantity(productionDetails.getTotalQuantity());
//	            fallbackRow.setAssemblypartNo(assemblypartNo);
//	            fallbackRow.setAssemblyName(assemblyName);
//	            fallbackRow.setReservationNo(reser);
//	            fallbackRow.setPartNo(partNo);
//	            fallbackRow.setDescription(description);
//	            fallbackRow.setUom(uom);
//	            fallbackRow.setRequiredQty(totalRequiredQty.toPlainString());
//	            fallbackRow.setBatchno("");
//	            fallbackRow.setStorestock("");
//	            fallbackRow.setBinLocation("");
//
//	            if (assignLineId) {
//	                fallbackRow.setLineId(lineIdCounter++);
//	            }
//
//	            objList.add(fallbackRow);
//	        }
//	    }
//
//	    if (!objList.isEmpty()) {
//	        productionHeaderRepository.saveAll(objList);
//	    }
//
//	    return new KitsheetEmsWrapper(
//	            objList,
//	            prodOrdersQtyStr.toString().replaceAll(",$", ""),
//	            reservationsStr.toString().replaceAll(",$", "")
//	    );
//	}

//	private BigDecimal safeParseBigDecimal(String input) {
//	    try {
//	        return new BigDecimal(input);
//	    } catch (Exception e) {
//	        return BigDecimal.ZERO;
//	    }
//	}

	private BigDecimal safeParseBigDecimal(String value) {
		try {
			return new BigDecimal(value);
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

	public KitsheetEmsWrapper savekitsheetEmsDetails(String productionorder)
			throws ParseException, UnirestException, IOException, InterruptedException, ExecutionException {

		List<String> productionList = Utils.salesOrderSeparatedComma(productionorder);
		List<ProductionOrderJsonFormatPropertiesDAO> afterFilterList = getKitsheetEmsList(productionList);

		/*---------------Guna Code Optimization Started ----------------------------------------------*/
		List<String> pleantList = afterFilterList.stream().map(ProductionOrderJsonFormatPropertiesDAO::getPlant)
				.distinct().collect(Collectors.toList());

		List<MasterPlantJsonFormatPropetiesDAO> plantlist = kitesheetOptimizationservice.plantListAPI(pleantList);
		List<WareHouseJsonFormatPropertiesDAO> getwareHouselist = kitesheetOptimizationservice.getwareHouse(pleantList);

		List<ReservationDocHeaderJsonFormatPropertiesDAO> reservationDocumentHeader = kitesheetOptimizationservice
				.getReservationDocumentHeader(productionList);
		List<ProductionOrderComponent4JsonFormatPropertiesDAO> productionOrderList4 = kitesheetOptimizationservice
				.getProductionOrderList4(productionList);

		List<String> reservationList = reservationDocumentHeader.stream()
				.map(ReservationDocHeaderJsonFormatPropertiesDAO::getReservation).distinct()
				.collect(Collectors.toList());

		List<AReservationDocumentItemJsonFormatPropertiesDAO> reservationDocumentItem = kitesheetOptimizationservice
				.getReservationDocumentItem(reservationList);

		List<String> productlist = reservationDocumentItem.stream()
				.map(AReservationDocumentItemJsonFormatPropertiesDAO::getDProduct).distinct()
				.collect(Collectors.toList());

		List<ViolinMaterialJsonFormatPropertiesDAO> violinMaterialList = kitesheetOptimizationservice
				.getViolinMaterial(productlist);

		List<String> salesOrderList = afterFilterList.stream()
				.map(ProductionOrderJsonFormatPropertiesDAO::getSalesOrder).collect(Collectors.toList());
		List<SaleOrderItemJsonFormatPropertiesDAO> salesOrderItemList = kitesheetOptimizationservice
				.salesorderItem(salesOrderList);
		// List<SaleOrderItemPropertiesDAO> salesOrderItemList = getSalesOrderItem();

		// CompletableFuture<List<ProductionOrderPropertiesDAO>> productionOrderList2 =
		// apiListService.ProductionOrderList();
		// CompletableFuture.allOf(productionOrderList2);
		// List<ProductionOrderPropertiesDAO> productionOrderList =
		// productionOrderList2.get();

		List<ProductionOrderJsonFormatPropertiesDAO> productionOrderList = afterFilterList;

		/*---------------Guna Code Optimization Ended ----------------------------------------------*/

		List<ProductionHeaderEntity> objList = new ArrayList<>();
		StringBuilder prodOrdersQtyStr = new StringBuilder();
		StringBuilder reservationsStr = new StringBuilder();

		Map<String, List<AReservationDocumentItemJsonFormatPropertiesDAO>> globalGroupedMap = new HashMap<>();
		Map<String, String> partToOrderMap = new HashMap<>();
		Map<String, String> partToReservationMap = new HashMap<>();
		Map<String, ProductionOrderJsonFormatPropertiesDAO> partToProductionDetails = new HashMap<>();

		for (ProductionOrderJsonFormatPropertiesDAO obj : afterFilterList) {
			String currentOrder = obj.getManufacturingOrder();
			String salesOrder = obj.getSalesOrder();
			String salesOrderItem = obj.getSalesOrderItem();

			String reser = reservationDocumentHeader.stream()
					.filter(e -> e.getGoodsRecipientName().equalsIgnoreCase(currentOrder))
					.map(ReservationDocHeaderJsonFormatPropertiesDAO::getReservation).findFirst().orElse("");

			prodOrdersQtyStr.append(currentOrder).append("(")
					.append(obj.getTotalQuantity() != null ? obj.getTotalQuantity().toString() : "0").append("),");

			if (!reser.isEmpty()) {
				reservationsStr.append(reser).append(",");
			}

			List<AReservationDocumentItemJsonFormatPropertiesDAO> filteredItems = reservationDocumentItem.stream()
					.filter(e -> e.getDReservation().equalsIgnoreCase(reser)).collect(Collectors.toList());

			for (AReservationDocumentItemJsonFormatPropertiesDAO item : filteredItems) {
				String key = item.getDProduct() + "||" + item.getDBaseUnit();
				globalGroupedMap.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
				partToOrderMap.putIfAbsent(key, currentOrder);
				partToReservationMap.putIfAbsent(key, reser);
				partToProductionDetails.putIfAbsent(key, obj);
			}
		}

		List<String> allowedStorageLocations = getwareHouselist.stream()
				.map(WareHouseJsonFormatPropertiesDAO::getEWMWarehouse).filter(Objects::nonNull).distinct() // optional // - avoid duplicates
				.collect(Collectors.toList());
		int lineIdCounter = 1;

		Set<String> uniqueLineKeySet = new HashSet<>();
		Set<String> shownQtyAssemKeys = new HashSet<>();

		for (Map.Entry<String, List<AReservationDocumentItemJsonFormatPropertiesDAO>> entry : globalGroupedMap
				.entrySet()) {
			String[] keys = entry.getKey().split("\\|\\|");
			String partNo = keys[0];
			String uom = keys.length > 1 ? keys[1] : "";
			String description = violinMaterialList.stream().filter(v -> v.getDProduct().equalsIgnoreCase(partNo))
					.map(ViolinMaterialJsonFormatPropertiesDAO::getDMaterialDescription).findFirst().orElse("");

			BigDecimal totalRequiredQty = entry.getValue().stream()
					.map(AReservationDocumentItemJsonFormatPropertiesDAO::getDResvnItmRequiredQtyInBaseUnit)
					.map(this::safeParseBigDecimal).reduce(BigDecimal.ZERO, BigDecimal::add);

			String currentOrder = partToOrderMap.get(entry.getKey());
			String reser = partToReservationMap.get(entry.getKey());

			ProductionOrderJsonFormatPropertiesDAO productionDetails = partToProductionDetails.get(entry.getKey());

			String plantName = plantlist.stream()
					.filter(e -> e.getPlant().equalsIgnoreCase(productionDetails.getPlant()))
					.map(MasterPlantJsonFormatPropetiesDAO::getPlantName).findFirst().orElse("");

			String salesOrder = productionDetails.getSalesOrder();
			String salesOrderItem = productionDetails.getSalesOrderItem();
//			String assemblypartNo = productionDetails.getMaterial() != null ? productionDetails.getMaterial().trim()
//					: "";

			String assemblypartNo = salesOrderItemList.stream()
					.filter(e -> e.getDSalesOrder().equalsIgnoreCase(salesOrder)
							&& e.getDSalesOrderItem().equalsIgnoreCase(salesOrderItem))
					.map(SaleOrderItemJsonFormatPropertiesDAO::getDMaterial).findFirst().orElse("");

			List<ViolinMaterialJsonFormatPropertiesDAO> violinMaterialList1 = kitesheetOptimizationservice
					.getViolinMaterialOpti(assemblypartNo);

//			String assemblyoldpartNo = violinMaterialList1.stream()
//					.filter(e -> e.getDProduct().equalsIgnoreCase(assemblypartNo))
//					.map(ViolinMaterialJsonFormatPropertiesDAO::getProductOldID).findFirst().orElse("");

			String assemblyoldpartNo = violinMaterialList1.stream()
					.filter(e -> e != null && assemblypartNo != null
							&& assemblypartNo.equalsIgnoreCase(e.getDProduct()))
					.map(ViolinMaterialJsonFormatPropertiesDAO::getProductOldID).filter(Objects::nonNull).findFirst()
					.orElse(""); // safe, no Optional.of(null)

			String assemblyName = salesOrderItemList.stream()
					.filter(e -> e.getDSalesOrder().equalsIgnoreCase(salesOrder)
							&& e.getDSalesOrderItem().equalsIgnoreCase(salesOrderItem))
					.map(SaleOrderItemJsonFormatPropertiesDAO::getDSalesOrderItemText).findFirst().orElse("");

			Optional<String> totalQtyStrOpt = productionOrderList.stream()
					.filter(p -> p.getManufacturingOrder().equalsIgnoreCase(currentOrder))
					.map(ProductionOrderJsonFormatPropertiesDAO::getTotalQuantity).findFirst();

			Optional<String> reqQtyStrOpt = productionOrderList4.stream()
					.filter(p -> p.getDManufacturingOrder().equalsIgnoreCase(currentOrder))
					.filter(p -> p.getDMaterial().equalsIgnoreCase(partNo))
					.map(p -> p.getDRequiredQuantity() != null ? p.getDRequiredQuantity().toString() : null)
					.filter(Objects::nonNull).findFirst();

			String reqQtyStrOptPlant = productionOrderList4.stream()
					.filter(p -> p.getDManufacturingOrder().equalsIgnoreCase(currentOrder))
					.filter(p -> p.getDMaterial().equalsIgnoreCase(partNo)).map(p -> p.getDPlant()).findFirst()
					.orElse("");
//			System.out.println("reqQtyStrOptPlant ::"+ reqQtyStrOptPlant);

			BigDecimal totalQty = totalQtyStrOpt.map(this::safeParseBigDecimal).orElse(BigDecimal.ZERO);
			BigDecimal reqQty = reqQtyStrOpt.map(this::safeParseBigDecimal).orElse(BigDecimal.ZERO);
			BigDecimal qtyAssem = BigDecimal.ZERO;

			if (totalQty.compareTo(BigDecimal.ZERO) > 0) {
				qtyAssem = reqQty.divide(totalQty, 4, RoundingMode.HALF_UP);
			}

			boolean assignLineId = uniqueLineKeySet.add(currentOrder + "||" + partNo + "||" + uom);

//			List<WareHousePropertiesDAO> batchEntries = getwareHouselist.stream()
//					.filter(e -> e.getDMaterial().equalsIgnoreCase(partNo)
//							&& e.getDPlant().equalsIgnoreCase(reqQtyStrOptPlant))
//					.filter(e -> allowedStorageLocations.contains(e.getDStorageLocation()))
//					.filter(e -> e.getDBatch() != null && !e.getDBatch().isEmpty()).collect(Collectors.toList());

			List<WareHouseJsonFormatPropertiesDAO> batchEntries = getwareHouselist.stream()
					.filter(e -> e.getDMaterial().equalsIgnoreCase(partNo)
							&& e.getDPlant().equalsIgnoreCase(reqQtyStrOptPlant))
					.filter(e -> allowedStorageLocations.contains(e.getDStorageLocation()))
					.filter(e -> e.getDBatch() != null && !e.getDBatch().isEmpty())
					.sorted(Comparator.comparing(WareHouseJsonFormatPropertiesDAO::getDBatch)) // 👈 sort ascending
					.collect(Collectors.toList());

			boolean isFirst = true;
			Set<String> shownBatchKeys = new HashSet<>();

			if (partNo.equalsIgnoreCase("50170010044")) {
//				System.out.println("Tes");

			}

			for (WareHouseJsonFormatPropertiesDAO wh : batchEntries) {
				ProductionHeaderEntity batchRow = new ProductionHeaderEntity();
				batchRow.setHeaderProduction(currentOrder);
				batchRow.setPlantName(plantName);
				batchRow.setSalesOrder(salesOrder);
				batchRow.setSalesorderItem(salesOrderItem);
				batchRow.setProductionNo(currentOrder);
				batchRow.setOrderQuantity(productionDetails.getTotalQuantity());
				batchRow.setAssemblypartNo(assemblypartNo);
				batchRow.setPartOldNo(assemblyoldpartNo);
				batchRow.setOldPartNo(assemblypartNo);
				batchRow.setAssemblyName(assemblyName);
				batchRow.setReservationNo(reser);
				batchRow.setSortPartNo(partNo);

				// Show qtyAssem only once
				String qtyKey = currentOrder + "||" + partNo + "||" + qtyAssem;
				if (shownQtyAssemKeys.add(qtyKey)) {
					batchRow.setQtyAssem(qtyAssem.setScale(3, RoundingMode.HALF_UP).toPlainString());
				} else {
					batchRow.setQtyAssem("");
				}

				if (isFirst) {
					batchRow.setPartNo(partNo);
					batchRow.setDescription(description);
					batchRow.setUom(uom);
					batchRow.setRequiredQty(totalRequiredQty.toPlainString());

					if (assignLineId) {
						batchRow.setLineId(lineIdCounter++);
					}

					isFirst = false;
				} else {
					batchRow.setPartNo("");
					batchRow.setDescription("");
					batchRow.setUom("");
					batchRow.setRequiredQty("");
				}

				String batchKey = wh.getDBatch() + "||" + wh.getEWMStockQuantityInBaseUnit() + "||"
						+ wh.getDEWMStorageBin();
				if (shownBatchKeys.add(batchKey)) {
					batchRow.setBatchno(wh.getDBatch());
					batchRow.setStorestock(wh.getEWMStockQuantityInBaseUnit());
					batchRow.setBinLocation(wh.getDEWMStorageBin());
				} else {
					batchRow.setBatchno("");
					batchRow.setStorestock("");
					batchRow.setBinLocation("");
				}

				objList.add(batchRow);
			}

			if (batchEntries.isEmpty()) {
				ProductionHeaderEntity fallbackRow = new ProductionHeaderEntity();
				fallbackRow.setHeaderProduction(currentOrder);
				fallbackRow.setPlantName(plantName);
				fallbackRow.setSalesOrder(salesOrder);
				fallbackRow.setSalesorderItem(salesOrderItem);
				fallbackRow.setProductionNo(currentOrder);
				fallbackRow.setOrderQuantity(productionDetails.getTotalQuantity());
				fallbackRow.setAssemblypartNo(assemblypartNo);
				fallbackRow.setPartOldNo(assemblyoldpartNo);
				fallbackRow.setAssemblyName(assemblyName);
				fallbackRow.setReservationNo(reser);
				fallbackRow.setPartNo(partNo);
				fallbackRow.setDescription(description);
				fallbackRow.setUom(uom);
				fallbackRow.setRequiredQty(totalRequiredQty.toPlainString());
				fallbackRow.setSortPartNo(partNo); // always set

				String qtyKey = currentOrder + "||" + partNo + "||" + qtyAssem;
				if (shownQtyAssemKeys.add(qtyKey)) {
					fallbackRow.setQtyAssem(qtyAssem.setScale(3, RoundingMode.HALF_UP).toPlainString());
				} else {
					fallbackRow.setQtyAssem("");
				}

				fallbackRow.setBatchno("");
				fallbackRow.setStorestock("");
				fallbackRow.setBinLocation("");

				if (assignLineId) {
//					fallbackRow.setLineId(lineIdCounter++);
				}

				objList.add(fallbackRow);
			}
		}

		if (!objList.isEmpty()) {
			objList.sort(Comparator.comparing(ProductionHeaderEntity::getSortPartNo,
					Comparator.nullsLast(String::compareToIgnoreCase)));

			// Reset S.No / LineId after sorting
			int counter = 1;
			for (ProductionHeaderEntity row : objList) {
				// Only assign LineId for rows that are the "first" in their group (partNo not
				// blank)
				if (row.getPartNo() != null && !row.getPartNo().isEmpty()) {
					row.setLineId(counter++);
				}
			}

			productionHeaderRepository.saveAll(objList);
		}

		return new KitsheetEmsWrapper(objList, prodOrdersQtyStr.toString().replaceAll(",$", ""),
				reservationsStr.toString().replaceAll(",$", ""));

	}

	/*------------------------------------End------------------------------------*/

//	@GetMapping("/getsaleorder")
	@Description(value = "Reservation Document Header API Consume")
	public List<SaleOrderItemPropertiesDAO> getSalesOrderItem(String apiUrl)
			throws UnirestException, IOException, ParseException {

		// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
		// "-" + APIConstants.KitSheet.A_SalesOrderItem);
		List<SaleOrderItemPropertiesDAO> data = new ArrayList<>();
		try {

			HttpResponse<String> jsonresponse = Utils.ApiCall(apiUrl);

			JsonNode getJsonValues = Utils.XmlToJsonConversionTestContent(jsonresponse.getBody());

			ObjectMapper objectMapper = new ObjectMapper();

			List<SaleOrderItemDAO> entryNodesValues = objectMapper.readValue(getJsonValues.traverse(),
					objectMapper.getTypeFactory().constructCollectionType(List.class, SaleOrderItemDAO.class));

			data = entryNodesValues.stream().map(e -> e.getContent().getProperties()).collect(Collectors.toList());

			return data;
		} catch (Exception e2) {
			// TODO: handle exception

			System.out.println("KitsheetEmsService.getSalesOrderItem() - Error : " + e2.getLocalizedMessage());
		}
		return data;

	}

	@Description(value = "SalesOrder Item API Consume")
	public List<SaleOrderItemJsonFormatPropertiesDAO> getSalesOrderItemForKitSheetEMS(String apiUrl)
			throws UnirestException, IOException, ParseException {

		// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
		// "-" + APIConstants.KitSheet.A_SalesOrderItem);
		List<SaleOrderItemJsonFormatPropertiesDAO> data = new ArrayList<>();
		try {

			HttpResponse<String> jsonresponse = Utils.ApiCall(apiUrl + "&$format=json");

			ObjectMapper objectMapper = new ObjectMapper();

			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");

			// Convert using readValue with TypeReference
			List<SaleOrderItemJsonFormatPropertiesDAO> resultsList = objectMapper.readValue(resultsNode.toString(),
					new TypeReference<List<SaleOrderItemJsonFormatPropertiesDAO>>() {
					});
			data.addAll(resultsList);
		} catch (Exception e2) {
			// TODO: handle exception

			System.out.println(
					"KitsheetEmsService.getSalesOrderItemForKitSheetEMS() - Error : " + e2.getLocalizedMessage());
		}
		return data;

	}

//	@GetMapping("/ReservationDocumentItem")
	@Description(value = "AReservationDocumentItem API Consume")
	public List<AReservationDocumentItemPropertiesDAO> getReservationDocumentItem(String apiUrl)
			throws UnirestException, IOException, ParseException {

		List<AReservationDocumentItemPropertiesDAO> data = new ArrayList<>();
		try {
			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.MRINReport.AReservationDocumentItem);
			HttpResponse<String> jsonresponse = Utils.ApiCall(apiUrl);

			JsonNode getJsonValues = Utils.XmlToJsonConversionTestContent(jsonresponse.getBody());

			ObjectMapper objectMapper = new ObjectMapper();

			List<ReservationDocumentItemDAO> entryNodesValues = objectMapper.readValue(getJsonValues.traverse(),
					objectMapper.getTypeFactory().constructCollectionType(List.class,
							ReservationDocumentItemDAO.class));

			data = entryNodesValues.stream().map(e -> e.getContentDAO().getProperties()).collect(Collectors.toList());

			return data;
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println("KitsheetEmsService.getReservationDocumentItem() - Error: " + e2.getLocalizedMessage());
		}

		return data;

	}

	@Description(value = "AReservationDocumentItem API Consume")
	public List<AReservationDocumentItemJsonFormatPropertiesDAO> getReservationDocumentItemForKitSheetEMS(String apiUrl)
			throws UnirestException, IOException, ParseException {

		List<AReservationDocumentItemJsonFormatPropertiesDAO> data = new ArrayList<>();
		try {
			// HttpResponse<String> jsonresponse = Utils.ApiCall("https://" + Utils.port +
			// "-" + APIConstants.MRINReport.AReservationDocumentItem);
			HttpResponse<String> jsonresponse = Utils.ApiCall(apiUrl + "&$format=json");

			ObjectMapper objectMapper = new ObjectMapper();

			JsonNode rootNode = objectMapper.readTree(jsonresponse.getBody());
			// Navigate to the "results" node
			JsonNode resultsNode = rootNode.path("d").path("results");

			// Convert using readValue with TypeReference
			List<AReservationDocumentItemJsonFormatPropertiesDAO> resultsList = objectMapper.readValue(
					resultsNode.toString(), new TypeReference<List<AReservationDocumentItemJsonFormatPropertiesDAO>>() {
					});
			data.addAll(resultsList);
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println("KitsheetEmsService.getReservationDocumentItemForKitSheetEMS() - Error: "
					+ e2.getLocalizedMessage());
		}

		return data;

	}

}
