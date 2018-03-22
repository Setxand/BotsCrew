package com.university.Services.repositoryServices.impl;

import com.university.Services.repositoryServices.LecturerRepositoryService;
import com.university.entities.Lecturer;
import com.university.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LecturerRepositoryServiceImpl implements LecturerRepositoryService {
    @Autowired
    private LecturerRepository lecturerRepository;

    @Override
    public Lecturer findOne(Long id) {
        return lecturerRepository.getOne(id);
    }

    @Override
    public void saveAndFlush(Lecturer lecturer) {
        lecturerRepository.saveAndFlush(lecturer);
    }

    @Override
    public void delete(Lecturer lecturer) {
        lecturerRepository.delete(lecturer);
    }
}
