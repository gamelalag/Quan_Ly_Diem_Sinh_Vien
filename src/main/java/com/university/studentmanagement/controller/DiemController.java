package com.university.studentmanagement.controller;

import com.university.studentmanagement.entity.Diem;
import com.university.studentmanagement.service.DiemService;
import com.university.studentmanagement.service.MonHocService;
import com.university.studentmanagement.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/diem") // Gốc của mọi đường dẫn trong controller này là /diem
public class DiemController {

    @Autowired
    private DiemService diemService;

    @Autowired
    private SinhVienService sinhVienService;

    @Autowired
    private MonHocService monHocService;

    // 1. Trang danh sách điểm (nếu bạn cần xem tất cả điểm)
    @GetMapping("")
    public String listDiem(Model model) {
        model.addAttribute("listDiem", diemService.getAllDiem());
        return "diem";
    }

    // 2. Hiển thị Form nhập điểm - Khớp với th:href="@{/diem/them-moi}"
    @GetMapping("/them-moi")
    public String showAddForm(Model model) {
        model.addAttribute("diem", new Diem());

        // Đổ dữ liệu vào dropdown để chọn
        model.addAttribute("listSinhVien", sinhVienService.getAllSinhVien());
        model.addAttribute("listMonHoc", monHocService.getAllMonHoc());

        return "add-diem"; // Tên file HTML phải là add-diem.html
    }

    // 3. Xử lý lưu điểm khi bấm nút Submit
    @PostMapping("/save")
    public String saveDiem(@ModelAttribute("diem") Diem diem) {
        diemService.saveDiem(diem);
        // Sau khi lưu, quay về trang danh sách sinh viên để kiểm tra kết quả
        return "redirect:/sinh-vien";
    }

    // 4. Xóa điểm
    @GetMapping("/delete/{id}")
    public String deleteDiem(@PathVariable("id") Long id) {
        diemService.deleteDiem(id);
        return "redirect:/sinh-vien";
    }
}