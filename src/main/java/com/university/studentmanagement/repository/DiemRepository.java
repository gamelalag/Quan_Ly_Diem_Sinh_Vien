package com.university.studentmanagement.repository;

import com.university.studentmanagement.entity.Diem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiemRepository extends JpaRepository<Diem, Long> {
    @Query("SELECT AVG(d.diemSo) FROM Diem d")
    Double getAverageDiem();
    List<Diem> findBySinhVienId(Long sinhVienId);

}