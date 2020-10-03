package com.pec.studentportal.Controller;

import com.pec.studentportal.Entity.RegisteredUser;
import com.pec.studentportal.Entity.Student;
import com.pec.studentportal.Services.TestingService;
import com.pec.studentportal.dto.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/testing")
public class TestingController {

    @Autowired
    private TestingService testingService;

    @RequestMapping(method = RequestMethod.POST, value = "/saveStudent")
    public void saveStudent(@RequestBody Student student) {
        testingService.saveStudent(student);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getStudentById/{id}")
    public Student getStudentById(@PathVariable String id) {
        return testingService.getStudentById(Integer.parseInt(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveUser")
    public void saveRegisteredUser(@RequestBody RegistrationDTO user) {
        testingService.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getStudentById")
    public RegisteredUser getUserByEmailId(@RequestParam String email) {
        return testingService.getUserByEmail(email);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/health")
    public String healthCheck() {
        return "All is Well!!!";
    }
}
