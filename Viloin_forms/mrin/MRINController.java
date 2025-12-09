package com.altrocks.forms.mrin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.altrocks.forms.incoming.InspectionAPIListService;

import com.altrocks.forms.utils.UtilsService;
import com.mashape.unirest.http.exceptions.UnirestException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Controller
@RequestMapping("/violin/forms")
public class MRINController {

	@Autowired
	UtilsService Utils;

	@Autowired
	InspectionAPIListService apiListService;

	@Autowired
	MRINService mrinService;

	@Value("${app.inspection.path}")
	private String layOutPath;

	@Autowired
	JdbcTemplate jdbc;
	
	@Value("${app.logo.path}")
	public String logoPath;


	@Autowired
	MRINRepository mrinRepository;

	@Description(value = " Home Page!!")
	@GetMapping(value ="/mrin" )
	public String MRINReportHomePage(Model model) throws ParseException, UnirestException, IOException {

		model.addAttribute("client", Utils.clientDetails);
		model.addAttribute("environment", Utils.applicationEnvironment);

//		try {
//			model.addAttribute("plantList", apiListService.getMastersList().get(0));
//			model.addAttribute("materialList", apiListService.getMastersList().get(2));
//		} catch (Exception e) {
//			e.getMessage();
//		}

		return "Incoming/MRIN";
	}

	@Description(value = "Display MRIN Form")
	@PostMapping(value = "/mrin/filter")
	public ResponseEntity<List<ReservationDocHeaderPropertiesDAO>> inspectionFormFilter(HttpServletRequest request,
			Model model) throws UnirestException, IOException, ParseException, InterruptedException, ExecutionException {

		String reservationNumber = request.getParameter("reservationNumber");
//		String plant = request.getParameter("plant");
//		String material = request.getParameter("material");
//		String costCenter = request.getParameter("costCenter");

		model.addAttribute("client", Utils.clientDetails);
		model.addAttribute("environment", Utils.applicationEnvironment);

//		System.out.println("Reservation Number : " + reservationNumber);
//		System.out.println("Plant : " + plant);
//		System.out.println("Material : " + material);
//		System.out.println("Cost Center : " + costCenter);

		List<ReservationDocHeaderPropertiesDAO> mrinList = mrinService.getMRINList(reservationNumber);

		// System.out.println("saveMRINList :"+saveMRINList);

		//model.addAttribute("plantList", apiListService.getMastersList().get(0));
		//model.addAttribute("materialList", apiListService.getMastersList().get(2));

		return ResponseEntity.ok(mrinList);
	}
	
	

	@Description(value = "MRIN Form Generate")
	@GetMapping("/mrin/list/{reservation}")
	public ResponseEntity<byte[]> InspectionFormGenerate(@PathVariable String reservation) throws UnirestException, IOException, ParseException, InterruptedException, ExecutionException {

		//Delete the Exisitng data based on Reservation
		String UpdateExisitngLog = "DELETE FROM mrin WHERE reservation = '" + reservation + "' ";
		jdbc.update(UpdateExisitngLog);

		mrinService.saveMRIN(reservation);

		HashMap<String, Object> map = new HashMap();

		map.put("reservation", reservation);
//		map.put("plant", "");
//		map.put("material", "");
//		map.put("costCenter", "");
		map.put("logo", logoPath);

//		map.put("logo", Utils.paymentAdviceJasperImagePath);

		String mainLayOutPath = layOutPath + "MRIN.jrxml";
		byte[] reportBytes = null;
		try {

			JasperReport reportNew = JasperCompileManager.compileReport(mainLayOutPath);

			
			try (Connection con = DriverManager.getConnection(Utils.databaseConnectionUrl, Utils.databaseUsername,
					Utils.databasePassword)) {

				// Fill the report
				JasperPrint jasperPrint = JasperFillManager.fillReport(reportNew, map, con);

				// Export to PDF
				reportBytes = JasperExportManager.exportReportToPdf(jasperPrint);

			} catch (JRException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println("LayOut Path Error :" + e.getMessage());
		}

		return ResponseEntity.ok().header("Content-Type", "application/pdf")
				.header("Content-Disposition", "inline; filename=MRIN" + ".pdf").body(reportBytes);

	}

}
