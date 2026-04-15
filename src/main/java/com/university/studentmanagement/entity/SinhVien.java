package com.university.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "sinh_vien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SinhVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_sv", unique = true, nullable = false)
    private String maSV;

    @Column(name = "ho_ten", nullable = false)
    private String hoTen;

    @Column(name = "email")
    private String email;
}