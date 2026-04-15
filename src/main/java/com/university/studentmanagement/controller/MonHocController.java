package com.university.studentmanagement.controller;

import com.university.studentmanagement.entity.MonHoc;
import com.university.studentmanagement.service.MonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mon-hoc")
public class MonHocController {

    @Autowired
    private MonHocService monHocService;

    // Hiển thị danh sách môn học
    @GetMapping
    public String listMonHoc(Model model) {
        model.addAttribute("listMonHoc", monHocService.getAllMonHoc());
        return "mon-hoc"; // Gọi file mon-hoc.html
    }

    // Hiển thị form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("monHoc", new MonHoc());
        return "add-mon-hoc";
    }

    // Hiển thị form sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("monHoc", monHocService.getMonHocById(id));
        return "edit-mon-hoc";
    }

    // Lưu dữ liệu (dùng chung cho cả thêm và sửa)
    @PostMapping("/save")
    public String saveMonHoc(@ModelAttribute("monHoc") MonHoc monHoc) {
        monHocService.saveMonHoc(monHoc);
        return "redirect:/mon-hoc";
    }

    // Xóa môn học
    @GetMapping("/delete/{id}")
    public String deleteMonHoc(@PathVariable("id") Long id) {
        monHocService.deleteMonHoc(id);
        return "redirect:/mon-hoc";
    }
}