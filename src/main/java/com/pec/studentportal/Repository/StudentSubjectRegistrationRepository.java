package com.pec.studentportal.Repository;

import com.pec.studentportal.Entity.StudentSubjectRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSubjectRegistrationRepository extends JpaRepository<StudentSubjectRegistration, Integer> {
}
