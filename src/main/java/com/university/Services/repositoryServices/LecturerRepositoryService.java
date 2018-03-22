package com.university.Services.repositoryServices;

import com.university.entities.Lecturer;

public interface LecturerRepositoryService {
    public Lecturer findOne(Long id);
    public void saveAndFlush(Lecturer lecturer);
    public void delete(Lecturer lecturer);
}
