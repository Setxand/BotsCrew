package com.university.Services.repositoryServices.impl;

import com.university.Services.repositoryServices.StatisticRepositoryService;
import com.university.entities.Statistic;
import com.university.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticRepositoryServiceImpl implements StatisticRepositoryService {
    @Autowired
    private StatisticRepository statisticRepository;
    @Override
    public Statistic saveAndFlush(Statistic statistic) {
        statisticRepository.saveAndFlush(statistic);
        return statistic;
    }

    @Override
    public void delete(Statistic statistic) {
        statisticRepository.delete(statistic);
    }
}
