package com.example.servercomputer.repository;

import com.example.servercomputer.dto.CTPhieuNhapResponseDTO;
import com.example.servercomputer.entity.DetailImport;
import com.example.servercomputer.entity.Import;
import com.example.servercomputer.entity.Imports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailImportRepository extends JpaRepository<DetailImport, Long> {
    List<DetailImport> findDetailImportByImportsId(Imports imports);
}
