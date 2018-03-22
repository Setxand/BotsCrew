package com.university.Services.repositoryServices.impl;

import com.university.Services.repositoryServices.DepartmentRepositoryService;
import com.university.Services.repositoryServices.LecturerRepositoryService;
import com.university.Services.repositoryServices.StatisticRepositoryService;
import com.university.Services.supportServices.SupportService;
import com.university.Services.universityService.UniService;
import com.university.entities.Department;
import com.university.entities.Lecturer;
import com.university.entities.Statistic;
import com.university.enums.Degree;
import com.university.university.UniversityApplicationTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.Assert.*;

public class DepartmentRepositoryServiceImplTest extends UniversityApplicationTests {

    @Autowired
    private DepartmentRepositoryService departmentRepositoryService;
    @Autowired
    private LecturerRepositoryService lecturerRepositoryService;
    @Autowired
    private StatisticRepositoryService statisticRepositoryService;
    @Autowired
    private SupportService supportService;
    private Department department;
    private Lecturer lecturer;
    private Statistic statistic;


    @Before
    public void setUp() {
        statisticSetUp();
        departmentSetUp();
        lecturerSetUp();

        department.addLecturer(lecturer);

        departmentRepositoryService.saveAndFlush(department);
        lecturerRepositoryService.saveAndFlush(lecturer);
        supportService.addInStatistic(lecturer,department);
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
        logger.info(department.getName()+" statistic :\n\n"+department.getStatistic());

    }

    @Test
    public void countOfEmployee()throws Exception{
        String departmentName = "ASC";
        Department department = departmentRepositoryService.findByName(departmentName);
        logger.info("Employee count: " + department.getLecturers().size());
    }







    private void statisticSetUp() {
        statistic = new Statistic();

    }

    private void lecturerSetUp() {
        lecturer = new Lecturer("Martin Garrix",300D,Degree.PROFESSOR);
        Lecturer lecturer1 = new Lecturer("Balich Bohdan", 56D,Degree.ASSISTANT);

        Lecturer lecturer2 = new Lecturer("Kovivchak Yar",67D,Degree.ASSOCIATE_PROFESSOR);
        department.addLecturer(lecturer1);

        department.addLecturer(lecturer2);
        departmentRepositoryService.saveAndFlush(department);
        lecturerRepositoryService.saveAndFlush(lecturer1);
        supportService.addInStatistic(lecturer1,department);
        lecturerRepositoryService.saveAndFlush(lecturer2);
        supportService.addInStatistic(lecturer2,department);
    }

    private void departmentSetUp() {
        department = new Department();
        department.setName("ASC");
        department.setStatistic(statistic);
        statisticRepositoryService.saveAndFlush(statistic);

        departmentRepositoryService.saveAndFlush(department);


    }
}