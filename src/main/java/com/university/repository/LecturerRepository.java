package com.university.repository;

import com.university.entities.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer,Long> {
}
