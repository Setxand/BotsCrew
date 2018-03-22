package com.university.repository;

import com.university.entities.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic,Long> {
}
