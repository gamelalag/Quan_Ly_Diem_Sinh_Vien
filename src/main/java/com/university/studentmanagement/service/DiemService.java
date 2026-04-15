package com.university.studentmanagement.service;

import com.university.studentmanagement.entity.Diem;
import com.university.studentmanagement.repository.DiemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiemService {

    @Autowired
    private DiemRepository diemRepository;

    public List<Diem> getAllDiem() {
        return diemRepository.findAll();
    }

    public void saveDiem(Diem diem) {
        diemRepository.save(diem);
    }

    public void deleteDiem(Long id) {
        diemRepository.deleteById(id);
    }
    public List<Diem> findBySinhVienId(Long sinhVienId) {
        return diemRepository.findBySinhVienId(sinhVienId);
    }

}