package com.university.studentmanagement.repository;

import com.university.studentmanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // PHẢI CÓ DÒNG NÀY để code trong StudentManagementApplication không bị đỏ
    Role findByName(String name);
}