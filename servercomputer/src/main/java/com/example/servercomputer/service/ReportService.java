package com.example.servercomputer.service;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
	List<Object[]> getReportByProduct();
	
	List<Object[]> getReportByTime(LocalDate startDate, LocalDate endDate);

	List<Object[]> getReportByDate(String startDate, String endDate);
}
