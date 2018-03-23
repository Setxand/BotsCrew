package com.university.Services.supportServices;

import com.university.entities.Department;
import com.university.models.Statistic;

public interface SupportService {
    public Integer countOfEmployee(Department department);
    public Double avgSalary(Department department);
    public Statistic stat(Department department);
    public void changeUserStatus(Long userId,String status);
}
