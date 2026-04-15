package com.university.studentmanagement.service;

import com.university.studentmanagement.entity.MonHoc;
import com.university.studentmanagement.repository.MonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonHocService {

    @Autowired
    private MonHocRepository monHocRepository;

    public List<MonHoc> getAllMonHoc() {
        return monHocRepository.findAll();
    }

    public void saveMonHoc(MonHoc monHoc) {
        monHocRepository.save(monHoc);
    }

    public MonHoc getMonHocById(Long id) {
        return monHocRepository.findById(id).get();
    }

    public void deleteMonHoc(Long id) {
        monHocRepository.deleteById(id);
    }
}