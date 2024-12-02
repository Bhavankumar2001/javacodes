package com.java.demo.reports.electraev.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UtilsService {

	public String AppendPlusOneDayToDate(String dateStr) {
		// String dateString = "2023-09-21";

		// Parse the original string to a LocalDate
		LocalDate localDate = LocalDate.parse(dateStr);

		// Add one day to the LocalDate
		LocalDate nextDay = localDate.plusDays(1);

		// Convert the LocalDate to a Date
		Date date = Date.from(nextDay.atStartOfDay(ZoneId.systemDefault()).toInstant());

		// Format the resulting date as a string (optional)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String formattedDate = sdf.format(date);

		return sdf.format(date);
	}

	public Date convertToStartOfMonth(String yearMonth) {
		try {
			// Define the formatter for parsing
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");

			// Parse the input string to a Date
			Date parsedDate = formatter.parse(yearMonth);

			// Use Calendar to set the date to the first day of the month
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(parsedDate);
			calendar.set(Calendar.DAY_OF_MONTH, 1);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = dateFormat.format(calendar.getTime());
			// Return the Date object representing the first day of the month
			return dateFormat.parse(formattedDate);
		} catch (ParseException e) {
			// Handle parsing error
			System.err.println("Invalid date format: " + e.getMessage());
			return null;
		}
	}

	public  Date getEndOfMonth(String yearMonth) {
		try {
			// Define the formatter for parsing the input string
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");

			// Parse the input string to a Date
			Date parsedDate = formatter.parse(yearMonth);

			// Use Calendar to set the date to the first day of the month
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(parsedDate);
			calendar.set(Calendar.DAY_OF_MONTH, 1);

			// Move to the last day of the month
			calendar.add(Calendar.MONTH, 1); // Move to the next month
			calendar.add(Calendar.DAY_OF_MONTH, -1); // Move back one day to get the last day of the current month

			// Format the Date as "yyyy-MM-dd"
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = dateFormat.format(calendar.getTime());

			// Return the Date object representing the last day of the month
			return dateFormat.parse(formattedDate);
		} catch (ParseException e) {
			// Handle parsing error
			System.err.println("Invalid date format: " + e.getMessage());
			return null;
		}
	}
	public List<String> getStringListSepratedByComma(String supplier) {
		List<String> FinalObjList = new ArrayList<>();
		if (!supplier.isEmpty()) {
			String[] SelectionList = supplier.split(",");
			for (String obj : SelectionList) {
				FinalObjList.add(obj);
			}
		}
		System.out.println(FinalObjList);
		return FinalObjList;
	}


}
