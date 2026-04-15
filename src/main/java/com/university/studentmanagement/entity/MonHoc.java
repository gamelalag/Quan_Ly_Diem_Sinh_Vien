package com.university.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mon_hoc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonHoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_mon", unique = true, nullable = false)
    private String maMon;

    @Column(name = "ten_mon", nullable = false)
    private String tenMon;

    @Column(name = "so_tin_chi", nullable = false)
    private Integer soTinChi;
}