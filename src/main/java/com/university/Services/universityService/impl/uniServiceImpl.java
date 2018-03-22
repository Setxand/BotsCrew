package com.university.Services.universityService.impl;

import com.university.Services.messangerService.MessageSenderService;
import com.university.Services.repositoryServices.DepartmentRepositoryService;
import com.university.Services.repositoryServices.LecturerRepositoryService;
import com.university.Services.universityService.UniService;
import com.university.entities.Department;
import com.university.entities.Lecturer;
import com.university.models.messanger.Messaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class uniServiceImpl implements UniService {
    @Autowired
    private DepartmentRepositoryService departmentRepositoryService;
    @Autowired
    private LecturerRepositoryService lecturerRepositoryService;
    @Autowired
    private MessageSenderService messageSenderService;

    @Override
    public void headOfDepartment(String name, Messaging messaging) {
        Department department = departmentRepositoryService.findByName(name);
        Lecturer lecturer = lecturerRepositoryService.findOne(department.getHeadLecturerId());
        messageSenderService.sendSimpleMessage("The head of the "+department.getName()+" department is "+lecturer.getName(),messaging.getSender().getId());
    }
}
