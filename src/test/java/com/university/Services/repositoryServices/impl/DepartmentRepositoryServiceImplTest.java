package com.university.Services.repositoryServices.impl;

import com.university.Services.repositoryServices.DepartmentRepositoryService;
import com.university.Services.repositoryServices.LecturerRepositoryService;
import com.university.Services.supportServices.SupportService;
import com.university.entities.Department;
import com.university.entities.Lecturer;
import com.university.enums.Degree;
import com.university.university.UniversityApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentRepositoryServiceImplTest extends UniversityApplicationTests {

    @Autowired
    private DepartmentRepositoryService departmentRepositoryService;
    @Autowired
    private LecturerRepositoryService lecturerRepositoryService;
    @Autowired
    private SupportService supportService;
    private Department department;
    private Lecturer lecturer;


    @Before
    public void setUp() {
        departmentSetUp();
        lecturerSetUp();

        department.addLecturer(lecturer);

        departmentRepositoryService.saveAndFlush(department);
        lecturerRepositoryService.saveAndFlush(lecturer);
        department.setHeadLecturerId(lecturer.getId());
        departmentRepositoryService.saveAndFlush(department);

        lecturerRepositoryService.saveAndFlush(lecturer);



        logger.debug("\"setUp()\" successfully ended...");
    }




    @Test
    public void findByName() throws Exception {
        String departmentName = "ASC";
        Department department = departmentRepositoryService.findByName(departmentName);
        Lecturer lecturer = lecturerRepositoryService.findOne(department.getHeadLecturerId());
        logger.info("Head of " + departmentName + " department is " + lecturer.getName());
    }

    @Test
    public void findByNameShowStatistic() throws Exception{
        String departmentName = "ASC";
        Department department = departmentRepositoryService.findByName(departmentName);

    }

    @Test
    public void countOfEmployee()throws Exception{
        String departmentName = "ASC";
        Department department = departmentRepositoryService.findByName(departmentName);
        logger.info("Employee count: " + department.getLecturers().size());
    }









    private void lecturerSetUp() {
        lecturer = new Lecturer("Martin Garrix",300D,Degree.PROFESSOR);
        Lecturer lecturer1 = new Lecturer("Balich Bohdan", 56D,Degree.ASSISTANT);

        Lecturer lecturer2 = new Lecturer("Kovivchak Yar",67D,Degree.ASSOCIATE_PROFESSOR);
        department.addLecturer(lecturer1);

        department.addLecturer(lecturer2);
        departmentRepositoryService.saveAndFlush(department);
        lecturerRepositoryService.saveAndFlush(lecturer1);
        lecturerRepositoryService.saveAndFlush(lecturer2);
    }

    private void departmentSetUp() {
        department = new Department();
        department.setName("ASC");

        departmentRepositoryService.saveAndFlush(department);


    }
}