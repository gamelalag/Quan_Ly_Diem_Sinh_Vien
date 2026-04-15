package com.university.studentmanagement.service;

import com.university.studentmanagement.entity.GiaoVien;
import com.university.studentmanagement.repository.GiaoVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GiaoVienService {
    @Autowired
    private GiaoVienRepository repo;

    public List<GiaoVien> getAll() { return repo.findAll(); }
    public void save(GiaoVien gv) { repo.save(gv); }
    public GiaoVien getById(Long id) { return repo.findById(id).orElse(null); }
    public void delete(Long id) { repo.deleteById(id); }
}