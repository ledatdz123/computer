package com.example.servercomputer.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.servercomputer.dto.CTPhieuNhapResponseDTO;
import com.example.servercomputer.dto.ResponseDTO;
import com.example.servercomputer.dto.SuccessCode;
import com.example.servercomputer.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.servercomputer.service.ReportService;

import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/report")
@AllArgsConstructor
public class ReportAPI {
	private ReportService reportService;
	
	@GetMapping("/product")
	public List<Object[]> getReport(){
		return reportService.getReportByProduct();
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/time")
	public List<Object[]> getReportByTime(
							@RequestParam String startDate, 
							@RequestParam String endDate){
		LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
		LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
		return reportService.getReportByTime(start, end);
	}
//	@GetMapping("/date")
//	public List<Object[]> getReportDate(@RequestParam String startDate,
//										@RequestParam String endDate){
//		return reportService.getReportByDate(startDate, endDate);
//	}
//	@GetMapping("/topfiveproduct")
//	public List<Object[]> getTopFiveProduct(){
//
//		return reportService.getTopFiveProduct();
//	}

	@GetMapping("/topfiveproduct")
	public ResponseEntity<ResponseDTO> getTopFiveProduct(){
		ResponseDTO responseDTO = new ResponseDTO();
		List<Object[]> response = reportService.getTopFiveProduct();
		responseDTO.setData(response);
		responseDTO.setSuccessCode(SuccessCode.FIND_ORDER_DETAIL_SUCCESS);
		return ResponseEntity.ok(responseDTO);
	}
	@GetMapping("/date")
	public ResponseEntity<ResponseDTO> getReportByDate(@RequestParam String startDate,
													   @RequestParam String endDate){
		ResponseDTO responseDTO = new ResponseDTO();
		List<Object[]> response = reportService.getReportByDate(startDate, endDate);
		responseDTO.setData(response);
		responseDTO.setSuccessCode(SuccessCode.FIND_ORDER_DETAIL_SUCCESS);
		return ResponseEntity.ok(responseDTO);
	}
}
