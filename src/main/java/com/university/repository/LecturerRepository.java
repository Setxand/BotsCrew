package com.university.repository;

import com.university.entities.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LecturerRepository extends JpaRepository<Lecturer,Long> {
    @Query("SELECT u FROM Lecturer u WHERE u.name LIKE CONCAT('%',:name,'%')")
    public List<Lecturer> lecturerSearch(@Param("name") String name);
}
