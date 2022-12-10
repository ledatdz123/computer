package com.example.servercomputer.dto;

import com.example.servercomputer.entity.Imports;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PhieuNhapDTO {
    private Long id;
    private LocalDate ngaylapphieu;
    private Long iduser;
    private String status;
    public PhieuNhapDTO entityToDTO(Imports phieuNhap) {
        PhieuNhapDTO dto = new PhieuNhapDTO();
        dto.setId(phieuNhap.getId());
        dto.setNgaylapphieu(phieuNhap.getNgaylapphieu());
        dto.setIduser(phieuNhap.getUser().getId());
        dto.setStatus(phieuNhap.getStatus());
        return dto;
    }

    public Imports dtoToEntity(PhieuNhapDTO dto) {
        Imports phieuNhap = new Imports();
        phieuNhap.setId(dto.getId());
        phieuNhap.setNgaylapphieu(dto.getNgaylapphieu());
        return phieuNhap;
    }

    public List<Imports> dtoToEntity(List<PhieuNhapDTO> dtos) {
        List<Imports> list = new ArrayList<>();
        dtos.forEach(x -> list.add(dtoToEntity(x)));
        return list;
    }

    public List<PhieuNhapDTO> entityToDTO(List<Imports> phieuNhaps) {
        List<PhieuNhapDTO> list = new ArrayList<>();
        phieuNhaps.forEach(x-> list.add(entityToDTO(x)));

        return list;
    }
}
