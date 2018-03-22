package com.university.Services.supportServices.impl;

import com.university.Services.repositoryServices.DepartmentRepositoryService;
import com.university.Services.repositoryServices.StatisticRepositoryService;
import com.university.Services.repositoryServices.UserRepositoryService;
import com.university.Services.supportServices.SupportService;
import com.university.entities.Department;
import com.university.entities.Lecturer;
import com.university.entities.Register.User;
import com.university.entities.Statistic;
import com.university.enums.Degree;
import com.university.repository.Register.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.*;

@Service
public class SupportServiceImpl implements SupportService {
    @Autowired
    private DepartmentRepositoryService departmentRepositoryService;
    @Autowired
    private StatisticRepositoryService statisticRepositoryService;
    @Autowired
    private UserRepositoryService userRepositoryService;
    @Override
    public Integer countOfEmployee(Department department) {

       return null;
    }

    @Override
    public Double avgSalary(Department department) {
        List<Double>list = new ArrayList<>();
        for(Lecturer lecturer: department.getLecturers()){
            list.add(lecturer.getSalary());
        }
        OptionalDouble average = list
                .stream()
                .mapToDouble(a -> a)
                .average();


         return average.isPresent() ? average.getAsDouble() : 0;

    }

    @Override
    public void addInStatistic(Lecturer lecturer,Department department) {
        Statistic statistic = department.getStatistic();
        Degree degree = lecturer.getDegree();
        if(degree == Degree.ASSISTANT){
            statistic.setAssistantsCount(statistic.getAssistantsCount()+1);
            department.setStatistic(statistic);
        }
        else if(degree == Degree.ASSOCIATE_PROFESSOR){
            statistic.setAssociateProfessorsCount(statistic.getAssociateProfessorsCount()+1);
            department.setStatistic(statistic);
        }
        else if(degree == Degree.PROFESSOR){
            statistic.setProfessorsCount(statistic.getProfessorsCount()+1);
            department.setStatistic(statistic);
        }
        statisticRepositoryService.saveAndFlush(statistic);
        departmentRepositoryService.saveAndFlush(department);

    }

    @Override
    public void changeUserStatus(Long userId, String status) {
        User user = userRepositoryService.findById(userId);
        user.setUserStatus(status);
        userRepositoryService.saveAndFlush(user);
    }
}
