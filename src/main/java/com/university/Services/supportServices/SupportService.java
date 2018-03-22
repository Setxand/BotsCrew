package com.university.Services.supportServices;

import com.university.entities.Department;
import com.university.entities.Lecturer;
import com.university.entities.Register.User;
import com.university.enums.Degree;

import java.util.Map;

public interface SupportService {
    public Integer countOfEmployee(Department department);
    public Double avgSalary(Department department);
    public void addInStatistic(Lecturer lecturer, Department department);
    public void changeUserStatus(Long userId,String status);
}
