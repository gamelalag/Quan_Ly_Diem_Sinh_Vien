package com.university.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "diem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nối với bảng Sinh Viên (Nhiều điểm có thể thuộc về 1 sinh viên)
    @ManyToOne
    @JoinColumn(name = "sinh_vien_id", nullable = false)
    private SinhVien sinhVien;

    // Nối với bảng Môn Học (Nhiều điểm có thể thuộc về 1 môn học)
    @ManyToOne
    @JoinColumn(name = "mon_hoc_id", nullable = false)
    private MonHoc monHoc;

    @Column(name = "diem_so", nullable = false)
    private Double diemSo;
}