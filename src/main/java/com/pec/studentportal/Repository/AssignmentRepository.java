package com.pec.studentportal.Repository;

import com.pec.studentportal.Entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Integer> {
}
