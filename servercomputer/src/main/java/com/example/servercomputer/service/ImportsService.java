package com.example.servercomputer.service;

import com.example.servercomputer.dto.PhieuNhapDTO;
import com.example.servercomputer.dto.PhieuNhapResponseDTO;
import com.example.servercomputer.exception.ResourceNotFoundException;

import java.util.List;

public interface ImportsService {
    public List<PhieuNhapResponseDTO> retrievePhieuNhaps();

//    public PhieuNhapResponseDTO getPhieuNhap(Long nhapId) throws ResourceNotFoundException;

    public PhieuNhapDTO savePN(PhieuNhapDTO nhapDTO) throws ResourceNotFoundException;

    public Boolean deletePN(Long nhapId) throws ResourceNotFoundException;

    public PhieuNhapDTO updatePN(PhieuNhapDTO dto, Long id) throws ResourceNotFoundException;
}
