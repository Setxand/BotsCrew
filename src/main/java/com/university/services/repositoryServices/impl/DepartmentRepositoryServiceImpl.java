package com.university.services.repositoryServices.impl;

import com.university.services.repositoryServices.DepartmentRepositoryService;
import com.university.entities.Department;
import com.university.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentRepositoryServiceImpl implements DepartmentRepositoryService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> departmentSearch(String name) {
        return departmentRepository.departmentSearch(name);
    }

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
