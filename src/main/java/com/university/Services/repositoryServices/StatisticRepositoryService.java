package com.university.Services.repositoryServices;

import com.university.entities.Department;
import com.university.entities.Statistic;

public interface StatisticRepositoryService {
    public Statistic saveAndFlush(Statistic statistic);
    public void delete(Statistic statistic);
}
