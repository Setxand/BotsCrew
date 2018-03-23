package com.university.Services.supportServices.impl;

import com.university.Services.repositoryServices.DepartmentRepositoryService;
import com.university.Services.repositoryServices.LecturerRepositoryService;
import com.university.Services.repositoryServices.UserRepositoryService;
import com.university.Services.supportServices.SupportService;
import com.university.entities.Department;
import com.university.entities.Lecturer;
import com.university.entities.Register.User;
import com.university.models.Statistic;
import com.university.enums.Degree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SupportServiceImpl implements SupportService {
    @Autowired
    private LecturerRepositoryService lecturerRepositoryService;
    @Autowired
    private UserRepositoryService userRepositoryService;

    @Override
    public Integer countOfEmployee(Department department) {

        return department.getLecturers().size();
    }

    @Override
    public Float avgSalary(Department department) {
        return lecturerRepositoryService.avgSalary();

    }

    @Override
    public Statistic stat(Department department) {
        Statistic statistic = new Statistic();
        for (Lecturer lecturer : department.getLecturers()) {


            Degree degree = lecturer.getDegree();
            if (degree == Degree.ASSISTANT) {
                statistic.setAssistantsCount(statistic.getAssistantsCount() + 1);

            } else if (degree == Degree.ASSOCIATE_PROFESSOR) {
                statistic.setAssociateProfessorsCount(statistic.getAssociateProfessorsCount() + 1);

            } else if (degree == Degree.PROFESSOR) {
                statistic.setProfessorsCount(statistic.getProfessorsCount() + 1);

            }
        }
        return statistic;

    }

    @Override
    public void changeUserStatus(Long userId, String status) {
        User user = userRepositoryService.findById(userId);
        user.setUserStatus(status);
        userRepositoryService.saveAndFlush(user);
    }
}
