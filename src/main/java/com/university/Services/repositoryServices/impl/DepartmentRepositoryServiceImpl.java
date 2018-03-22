package com.university.Services.repositoryServices.impl;

import com.university.Services.repositoryServices.DepartmentRepositoryService;
import com.university.entities.Department;
import com.university.entities.Lecturer;
import com.university.repository.DepartmentRepository;
import com.university.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentRepositoryServiceImpl implements DepartmentRepositoryService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Override
    public void saveAndFlush(Department department) {
        departmentRepository.saveAndFlush(department);
    }

    @Override
    public void delete(Department department) {
        departmentRepository.delete(department);
    }
}
