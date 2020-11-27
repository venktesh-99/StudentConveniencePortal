package com.pec.studentportal.Repository;

import com.pec.studentportal.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    public Subject findByCourseCode(String courseCode);

}
