package com.altrocks.forms.kitsheetEms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.altrocks.forms.master.MasterService;
import com.altrocks.forms.routecard.ProductionOrderJsonFormatPropertiesDAO;
import com.altrocks.forms.routecard.ProductionOrderPropertiesDAO;
import com.altrocks.forms.utils.UtilsService;
import com.mashape.unirest.http.exceptions.UnirestException;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Controller
@RequestMapping(value = "/violin/forms")
public class KitsheetEmsController {

	@Value("${app.routecard.path}")
	public String layOutPath;

	@Value("${app.logo.path}")
	public String logoPath;

	@Autowired
	UtilsService Utils;

	@Autowired
	KitsheetEmsService emsService;

	@Autowired
	JdbcTemplate jdbc;

	@Autowired
	MasterService masterService;

	@GetMapping("/kit-sheetEms")
	public String kitSheet(Model model) {
		model.addAttribute("client", Utils.clientDetails);
		model.addAttribute("environment", Utils.applicationEnvironment);
		return "KitSheet/KitSheetEms.html";

	}

	
	
	@PostMapping(value = "/kit-sheetEms/filter")
	public ResponseEntity<List<ProductionOrderJsonFormatPropertiesDAO>> inspectionFormFilter(
	        @RequestBody KitsheetFilterRequest request,
	        Model model
	) throws UnirestException, IOException, ParseException, InterruptedException, ExecutionException {

		List<String> reservationNumbers = request.getReservationNumbers();

		model.addAttribute("client", Utils.clientDetails);
		model.addAttribute("environment", Utils.applicationEnvironment);

		// ✅ Pass the full list at once
		List<ProductionOrderJsonFormatPropertiesDAO> allKitsheetDetails = emsService.getKitsheetEmsList(reservationNumbers);

		model.addAttribute("data", allKitsheetDetails);

		return ResponseEntity.ok(allKitsheetDetails);
	}



//	@Description(value = "EMs Form Generate")
//	@GetMapping("/KitsheetEms/list/{salesorder}")
//	public ResponseEntity<byte[]> EmsformsGenerate(@PathVariable String salesorder)
//			throws UnirestException, IOException, ParseException, InterruptedException, ExecutionException {
//
//		if ("undefined".equals(salesorder)) {
//
//			salesorder = "";
//		}
//
//		String UpdateExisitngLog = "DELETE FROM kitsheet_ems WHERE production_no = '" + salesorder + "';";
//
//
//		jdbc.update(UpdateExisitngLog);
//
//		emsService.savekitsheetEmsDetails(salesorder);
//
//		HashMap<String, Object> map = new HashMap();
//
//		List<String> productionList = Arrays.stream(salesorder.split(","))
//			    .map(String::trim)
//			    .collect(Collectors.toList());
//
//			map.put("productionorders", productionList);
//			
//			map.put("logo", logoPath);
//
//		map.put("logo", logoPath);
//
////		map.put("logo", Utils.paymentAdviceJasperImagePath);
//
//		String mainLayOutPath = layOutPath + "\\KitsheetEms.jrxml";
//		byte[] reportBytes = null;
//		try {
//
//			JasperReport reportNew = JasperCompileManager.compileReport(mainLayOutPath);
//
//			try (Connection con = DriverManager.getConnection(Utils.databaseConnectionUrl, Utils.databaseUsername,
//					Utils.databasePassword)) {
//
//				// Fill the report
//				JasperPrint jasperPrint = JasperFillManager.fillReport(reportNew, map, con);
//
//				// Export to PDF
//				reportBytes = JasperExportManager.exportReportToPdf(jasperPrint);
//
//			} catch (JRException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//
//		} catch (Exception e) {
//			System.out.println("LayOut Path Error :" + e.getMessage());
//		}
//
//		return ResponseEntity.ok().header("Content-Type", "application/pdf")
//				.header("Content-Disposition", "inline; filename=KitsheetEms" + ".pdf").body(reportBytes);
//
//	}

	
	@GetMapping("/KitsheetEms/list/{salesorder}")
	public ResponseEntity<byte[]> EmsformsGenerate(@PathVariable String salesorder)
	        throws UnirestException, IOException, ParseException, InterruptedException, ExecutionException {

	    if ("undefined".equals(salesorder)) salesorder = "";

	    // Delete old entries
	    String deleteQuery = "DELETE FROM kitsheet_ems WHERE production_no = '" + salesorder + "';";
	    jdbc.update(deleteQuery);

	    // Call service
	    KitsheetEmsWrapper wrapper = emsService.savekitsheetEmsDetails(salesorder);

	    List<ProductionHeaderEntity> savedList = wrapper.getEntityList();

	    List<String> productionList = Arrays.stream(salesorder.split(","))
	            .map(String::trim).collect(Collectors.toList());

	    HashMap<String, Object> map = new HashMap<>();
	    map.put("productionorders", productionList);
	    map.put("finalProdOrdersQty", wrapper.getFinalProdOrdersQty());
	    map.put("finalReservations", wrapper.getFinalReservations());
	    map.put("logo", logoPath);

	    byte[] reportBytes = null;
	    String mainLayOutPath = layOutPath + "KitsheetEms.jrxml";

	    try {
	        JasperReport reportNew = JasperCompileManager.compileReport(mainLayOutPath);
	        try (Connection con = DriverManager.getConnection(
	                Utils.databaseConnectionUrl, Utils.databaseUsername, Utils.databasePassword)) {
	            JasperPrint jasperPrint = JasperFillManager.fillReport(reportNew, map, con);
	            reportBytes = JasperExportManager.exportReportToPdf(jasperPrint);
	        }
	    } catch (Exception e) {
	        System.out.println("KitsheetEmsController.EmsformsGenerate() - Error: " + e.getLocalizedMessage());
	    }

	    return ResponseEntity.ok()
	            .header("Content-Type", "application/pdf")
	            .header("Content-Disposition", "inline; filename=KitsheetEms.pdf")
	            .body(reportBytes);
	}

}
