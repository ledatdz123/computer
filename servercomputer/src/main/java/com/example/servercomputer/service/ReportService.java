package com.example.servercomputer.service;

import com.example.servercomputer.dto.ResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
	List<Object[]> getReportByProduct();
	
	List<Object[]> getReportByTime(LocalDate startDate, LocalDate endDate);

	List<Object[]> getReportByDate(String startDate, String endDate);
	List<Object[]> getTopFiveProduct();
	List<Object[]> getTopFive();
}
