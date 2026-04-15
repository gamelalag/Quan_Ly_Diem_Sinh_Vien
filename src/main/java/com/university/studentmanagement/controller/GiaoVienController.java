package com.university.studentmanagement.controller;

import com.university.studentmanagement.entity.GiaoVien;
import com.university.studentmanagement.service.GiaoVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/giao-vien")
public class GiaoVienController {
    @Autowired
    private GiaoVienService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("listGV", service.getAll());
        return "giao-vien"; // Tạo file giao-vien.html
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("giaoVien", new GiaoVien());
        return "add-giao-vien"; // Tạo file add-giao-vien.html
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("giaoVien") GiaoVien gv) {
        service.save(gv);
        return "redirect:/giao-vien";
    }
}