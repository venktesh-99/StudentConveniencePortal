package com.pec.studentportal.Repository;

import com.pec.studentportal.Entity.AssignmentPostings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentPostingsRepository extends JpaRepository<AssignmentPostings,Integer> {
}
