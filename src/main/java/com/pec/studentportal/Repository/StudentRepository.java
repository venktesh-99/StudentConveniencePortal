package com.pec.studentportal.Repository;

import com.pec.studentportal.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByStudentId(Integer studentId);

}
