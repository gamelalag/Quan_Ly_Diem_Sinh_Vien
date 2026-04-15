package com.university.studentmanagement.repository;

import com.university.studentmanagement.entity.GiaoVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiaoVienRepository extends JpaRepository<GiaoVien, Long> {
    // Có thể thêm hàm tìm kiếm theo tên nếu muốn
}