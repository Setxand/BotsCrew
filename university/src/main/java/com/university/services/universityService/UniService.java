package com.university.services.universityService;

import com.university.models.messanger.Messaging;

public interface UniService {
    public void headOfDepartment( Messaging messaging);
    public void depStatistic( Messaging messaging);
    public void avgSalary(Messaging messaging);
    public void countOfEmployee(Messaging messaging);
    public void lecturersSearch(Messaging messaging);
    public void departmentsSearch(Messaging messaging);
}
