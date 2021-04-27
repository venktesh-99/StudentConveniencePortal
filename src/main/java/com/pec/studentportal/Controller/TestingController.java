package com.pec.studentportal.Controller;

import com.pec.studentportal.Entity.RegisteredUser;
import com.pec.studentportal.Entity.Student;
import com.pec.studentportal.Services.TestingService;
import com.pec.studentportal.dto.RegistrationDTO;
import com.pec.studentportal.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/testing")
public class TestingController {

    @Autowired
    private TestingService testingService;

    @RequestMapping(method = RequestMethod.POST, value = "/saveStudent")
    public void saveStudent(@RequestBody StudentPojo student) {
        testingService.saveStudent(student);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveTeacher")
    public void saveTeacher(@RequestBody TeacherPojo teacher) {
        testingService.saveTeacher(teacher);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveSubject")
    public void saveSubject(@RequestBody SubjectPojo subjectPojo) {
        testingService.saveSubject(subjectPojo);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registerStudentForCourse")
    public void registerStudentForCourse(@RequestBody SubjectRegistrationPojo subjectRegistrationPojo) {
        testingService.registerStudentForCourse(subjectRegistrationPojo);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registerTeacherForCourse")
    public void registerTeacherForCourse(@RequestBody SubjectRegistrationPojo subjectRegistrationPojo) {
        testingService.registerTeacherForCourse(subjectRegistrationPojo);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addMarks")
    public void addMarks(@RequestBody MarksDistributionPojo marksDistributionPojo) {
        testingService.addMarks(marksDistributionPojo);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addAttendance")
    public void addAttendance(@RequestBody AttendancePojo attendancePojo) {
        testingService.addAttendance(attendancePojo);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveDepartment")
    public void saveDepartment(@RequestBody DepartmentPojo department) {
        testingService.saveDepartment(department);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveBook")
    public void saveBook(@RequestBody BookPojo bookPojo) {
        testingService.saveBook(bookPojo);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issueBook")
    public void issueBook(@RequestBody BookIssueRecordPojo bookIssueRecordPojo) {
        testingService.issueBook(bookIssueRecordPojo);
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
