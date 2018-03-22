package com.university.repository;

import com.university.entities.Department;
import com.university.entities.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long>{
    public Department findByName(String name);

}
