package com.university.studentmanagement.service;

import com.university.studentmanagement.entity.SinhVien;
import com.university.studentmanagement.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SinhVienService {

    @Autowired
    private SinhVienRepository sinhVienRepository;

    public List<SinhVien> getAllSinhVien() {
        return sinhVienRepository.findAll();
    }

    public void saveSinhVien(SinhVien sv) {
        sinhVienRepository.save(sv);
    }

    public SinhVien getSinhVienById(Long id) {
        return sinhVienRepository.findById(id).orElse(null);
    }

    public void deleteSinhVien(Long id) {
        sinhVienRepository.deleteById(id);
    }

    // Hàm phân trang chuẩn
    public Page<SinhVien> findPaginated(String keyword, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        if (keyword != null && !keyword.isEmpty()) {
            return sinhVienRepository.findByHoTenContainingIgnoreCase(keyword, pageable);
        }
        return sinhVienRepository.findAll(pageable);
    }
}