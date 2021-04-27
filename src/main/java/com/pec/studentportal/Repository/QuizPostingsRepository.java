package com.pec.studentportal.Repository;

import com.pec.studentportal.Entity.QuizPostings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizPostingsRepository extends JpaRepository<QuizPostings, Integer> {
}
