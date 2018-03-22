package com.university.Services.repositoryServices;

import com.university.entities.Department;
import com.university.entities.Lecturer;

public interface DepartmentRepositoryService {
    public Department findByName(String name);
    public void saveAndFlush(Department department);
    public void delete(Department department);
}
