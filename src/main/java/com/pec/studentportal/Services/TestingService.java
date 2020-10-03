package com.pec.studentportal.Services;

import com.pec.studentportal.Entity.RegisteredUser;
import com.pec.studentportal.Entity.Student;
import com.pec.studentportal.Repository.RegisteredUserRepository;
import com.pec.studentportal.Repository.StudentRepository;
import com.pec.studentportal.dto.RegistrationDTO;
import com.pec.studentportal.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestingService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findByStudentId(id);
    }

    public void saveUser(RegistrationDTO registrationDTO) {
        RegisteredUser user = RegisteredUser.builder().emailId(registrationDTO.getEmailId()).password(registrationDTO.getPassword()).userRole(Role.valueOf(registrationDTO.getUserRole())).build();
        registeredUserRepository.save(user);
    }

    public RegisteredUser getUserByEmail(String emailId) {
        return registeredUserRepository.findByEmailId(emailId);
    }

}
