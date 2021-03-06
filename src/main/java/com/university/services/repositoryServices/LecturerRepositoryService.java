package com.university.services.repositoryServices;

import com.university.entities.Lecturer;

import java.util.List;

public interface LecturerRepositoryService {
    public List<Lecturer>lecturerSearch(String searchString);
    public Lecturer findOne(Long id);
    public void saveAndFlush(Lecturer lecturer);
    public void delete(Lecturer lecturer);
    public Float avgSalary();
}
