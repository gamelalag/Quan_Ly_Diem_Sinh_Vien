package com.university.studentmanagement.controller;

import com.university.studentmanagement.entity.Diem;
import com.university.studentmanagement.entity.SinhVien;
import com.university.studentmanagement.service.DiemService;
import com.university.studentmanagement.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sinh-vien") // Đặt gốc là /sinh-vien để quản lý tập trung
public class SinhVienController {

    @Autowired
    private SinhVienService sinhVienService;

    @Autowired
    private DiemService diemService;

    // 1. HIỂN THỊ DANH SÁCH, TÌM KIẾM VÀ PHÂN TRANG
    @GetMapping("")
    public String listSinhVien(Model model,
                               @RequestParam(name = "keyword", required = false) String keyword,
                               @RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
        int pageSize = 5;

        // Đảm bảo sinhVienService của bạn có hàm findPaginated
        Page<SinhVien> page = sinhVienService.findPaginated(keyword, pageNo, pageSize);

        model.addAttribute("listSV", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("keyword", keyword);

        return "sinh-vien";
    }

    // 2. HIỆN FORM THÊM MỚI
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("sinhVien", new SinhVien());
        return "add-sinh-vien";
    }

    // 3. LƯU DỮ LIỆU (Dùng cho cả Thêm và Sửa)
    @PostMapping("/save")
    public String saveSinhVien(@ModelAttribute("sinhVien") SinhVien sv) {
        sinhVienService.saveSinhVien(sv);
        return "redirect:/sinh-vien";
    }

    // 4. HIỆN FORM SỬA
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        // Kiểm tra tên hàm này trong SinhVienService (findById hoặc getSinhVienById)
        SinhVien sv = sinhVienService.getSinhVienById(id);
        model.addAttribute("sinhVien", sv);
        return "edit-sinh-vien";
    }

    // 5. XÓA SINH VIÊN
    @GetMapping("/delete/{id}")
    public String deleteSinhVien(@PathVariable("id") Long id) {
        sinhVienService.deleteSinhVien(id);
        return "redirect:/sinh-vien";
    }

    // 6. XEM CHI TIẾT BẢNG ĐIỂM VÀ TÍNH GPA
    @GetMapping("/chi-tiet/{id}")
    public String xemChiTietDiem(@PathVariable("id") Long id, Model model) {
        SinhVien sv = sinhVienService.getSinhVienById(id);

        // Đảm bảo DiemService có hàm findBySinhVienId
        List<Diem> listDiem = diemService.findBySinhVienId(id);

        double tongDiemHe10 = 0;
        int tongSoTinChi = 0;
        double gpa10 = 0;
        double gpa4 = 0;

        if (listDiem != null && !listDiem.isEmpty()) {
            for (Diem d : listDiem) {
                if (d.getMonHoc() != null) {
                    double diem = d.getDiemSo();
                    int tinChi = d.getMonHoc().getSoTinChi();

                    tongDiemHe10 += diem * tinChi;
                    tongSoTinChi += tinChi;
                }
            }

            if (tongSoTinChi > 0) {
                gpa10 = tongDiemHe10 / tongSoTinChi;
                // Công thức quy đổi chuẩn: (GPA10 / 10) * 4
                gpa4 = (gpa10 / 10.0) * 4.0;
            }
        }

        // Làm tròn
        gpa10 = Math.round(gpa10 * 100.0) / 100.0;
        gpa4 = Math.round(gpa4 * 100.0) / 100.0;

        model.addAttribute("sv", sv);
        model.addAttribute("listDiem", listDiem);
        model.addAttribute("gpa10", gpa10);
        model.addAttribute("gpa4", gpa4);
        model.addAttribute("xepLoai", calculateXepLoai(gpa4));

        return "chi-tiet-diem";
    }

    private String calculateXepLoai(double gpa4) {
        if (gpa4 >= 3.6) return "Xuất sắc";
        if (gpa4 >= 3.2) return "Giỏi";
        if (gpa4 >= 2.5) return "Khá";
        if (gpa4 >= 2.0) return "Trung bình";
        return "Yếu/Kém";
    }
}