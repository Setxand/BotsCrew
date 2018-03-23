package com.university.Services.universityService.impl;

import com.university.Services.messangerService.MessageSenderService;
import com.university.Services.repositoryServices.DepartmentRepositoryService;
import com.university.Services.repositoryServices.LecturerRepositoryService;
import com.university.Services.supportServices.SupportService;
import com.university.Services.universityService.UniService;
import com.university.entities.Department;
import com.university.entities.Lecturer;
import com.university.models.Statistic;
import com.university.models.messanger.Messaging;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class uniServiceImpl implements UniService {
    @Autowired
    private DepartmentRepositoryService departmentRepositoryService;
    @Autowired
    private LecturerRepositoryService lecturerRepositoryService;
    @Autowired
    private MessageSenderService messageSenderService;
    @Autowired
    private SupportService supportService;

    private static  final Logger logger = Logger.getLogger(uniServiceImpl.class);
    @Override
    public void headOfDepartment(Messaging messaging) {
        String name = messaging.getMessage().getText();
        Department department = departmentRepositoryService.findByName(name);
        Lecturer lecturer = lecturerRepositoryService.findOne(department.getHeadLecturerId());
        messageSenderService.sendSimpleMessage("The head of the "+department.getName()+" department is "+lecturer.getName(),messaging.getSender().getId());
    }

    @Override
    public void depStatistic( Messaging messaging) {
        String name = messaging.getMessage().getText();
        Department department = departmentRepositoryService.findByName(name);
        Statistic statistic = supportService.stat(department);
        messageSenderService.sendSimpleMessage(statistic.toString(),messaging.getSender().getId());
    }

    @Override
    public void avgSalary(Messaging messaging) {
        String name = messaging.getMessage().getText();
        Department department = departmentRepositoryService.findByName(name);
        Double avgSalary = supportService.avgSalary(department);
        messageSenderService.sendSimpleMessage("Average salary of "+department.getName()+" is - "+avgSalary+"$",messaging.getSender().getId());
    }

    @Override
    public void countOfEmployee(Messaging messaging) {
        String name = messaging.getMessage().getText();
        Department department = departmentRepositoryService.findByName(name);
        Integer countOfEmployee = supportService.countOfEmployee(department);
        messageSenderService.sendSimpleMessage("Count of employee:"+ countOfEmployee,messaging.getSender().getId());

    }

    @Override
    public void lecturersSearch(Messaging messaging) {
        List<Lecturer> lecturers= null;
        try {
            lecturers = lecturerRepositoryService.lecturerSearch(messaging.getMessage().getText());
        }
        catch (Exception ex){
            logger.warn(ex);
            messageSenderService.errorMessage(messaging.getSender().getId());
            messageSenderService.sendUniActions(messaging);
        }

        messageSenderService.sendSimpleMessage("Found matches:"+lecturers,messaging.getSender().getId());

    }


    @Override
    public void departmentsSearch(Messaging messaging) {
        List<Department> departments = null;
        try {
            departments = departmentRepositoryService.departmentSearch(messaging.getMessage().getText());
        }
        catch (Exception ex){
            logger.warn(ex);
            messageSenderService.errorMessage(messaging.getSender().getId());
            messageSenderService.sendUniActions(messaging);
        }

        messageSenderService.sendSimpleMessage("Found matches:"+departments,messaging.getSender().getId());

    }
}
