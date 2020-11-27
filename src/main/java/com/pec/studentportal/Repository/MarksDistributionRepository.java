package com.pec.studentportal.Repository;

import com.pec.studentportal.Entity.MarksDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksDistributionRepository extends JpaRepository<MarksDistribution, Integer> {
}
