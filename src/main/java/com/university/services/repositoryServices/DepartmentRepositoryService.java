package com.university.services.repositoryServices;

import com.university.entities.Department;

import java.util.List;

public interface DepartmentRepositoryService {
    public List<Department> departmentSearch(String name);
    public Department findByName(String name);
    public void saveAndFlush(Department department);
    public void delete(Department department);
}
