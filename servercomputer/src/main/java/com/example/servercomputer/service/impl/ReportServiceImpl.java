package com.example.servercomputer.service.impl;

import java.time.LocalDate;
import java.util.List;

import com.example.servercomputer.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.servercomputer.repository.BillRepository;
import com.example.servercomputer.service.ReportService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService{
	private BillRepository billRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Override
	public List<Object[]> getReportByProduct() {
		return billRepository.getReportByProduct();
	}

	@Override
	public List<Object[]> getReportByTime(LocalDate startDate, LocalDate endDate) {
		return billRepository.getReportByTime(startDate, endDate);
	}

	@Override
	public List<Object[]> getReportByDate(String startDate, String endDate) {
		return orderRepository.getReportbyDate(startDate, endDate);
	}

}
