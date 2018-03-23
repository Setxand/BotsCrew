package com.university.repository;

import com.university.entities.Department;
import com.university.entities.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Long>{
    public Department findByName(String name);

    @Query("SELECT u FROM Department u WHERE u.name LIKE CONCAT('%',:name,'%')")
    public List<Department> departmentSearch(@Param("name") String name);


}
