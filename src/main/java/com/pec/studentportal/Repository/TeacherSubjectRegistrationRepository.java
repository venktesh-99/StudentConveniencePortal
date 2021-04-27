package com.pec.studentportal.Repository;

import com.pec.studentportal.Entity.TeacherSubjectRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherSubjectRegistrationRepository extends JpaRepository<TeacherSubjectRegistration, Integer> {
}
