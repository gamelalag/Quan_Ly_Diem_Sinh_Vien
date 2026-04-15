package com.university.studentmanagement.controller;

import com.university.studentmanagement.service.DiemService;
import com.university.studentmanagement.service.MonHocService;
import com.university.studentmanagement.service.SinhVienService;
import com.university.studentmanagement.repository.DiemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired private SinhVienService svService;
    @Autowired private MonHocService mhService;
    @Autowired private DiemRepository diemRepo; // Gọi trực tiếp repo để lấy điểm AVG

    @GetMapping("/")
    public String index(Model model) {
        // 1. Đếm tổng số sinh viên
        model.addAttribute("countSV", svService.getAllSinhVien().size());

        // 2. Đếm tổng số môn học
        model.addAttribute("countMH", mhService.getAllMonHoc().size());

        // 3. Tính điểm trung bình (Nếu chưa có điểm thì mặc định là 0)
        Double avg = diemRepo.getAverageDiem();
        model.addAttribute("avgDiem", avg != null ? Math.round(avg * 100.0) / 100.0 : 0.0);

        return "index"; // Trả về file index.html
    }
}