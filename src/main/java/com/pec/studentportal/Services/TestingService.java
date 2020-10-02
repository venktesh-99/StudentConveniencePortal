package com.pec.studentportal.Services;

import com.pec.studentportal.Entity.Student;
import com.pec.studentportal.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestingService {

    @Autowired
    private StudentRepository studentRepository;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findByStudentId(id);
    }
}
