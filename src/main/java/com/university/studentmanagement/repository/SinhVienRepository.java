package com.university.studentmanagement.repository;

import com.university.studentmanagement.entity.SinhVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {
    // Tìm kiếm có phân trang
    Page<SinhVien> findByHoTenContainingIgnoreCase(String hoTen, Pageable pageable);
}