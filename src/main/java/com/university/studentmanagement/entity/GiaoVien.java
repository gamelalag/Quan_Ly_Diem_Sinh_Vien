package com.university.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "giao_vien")
@Data
public class GiaoVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String maGV;

    @Column(nullable = false)
    private String hoTen;

    private String email;
    private String soDienThoai;
    private String chuyenMon; // Ví dụ: Công nghệ phần mềm, Toán học...
}