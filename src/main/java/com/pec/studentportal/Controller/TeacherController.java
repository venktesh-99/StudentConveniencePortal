package com.pec.studentportal.Controller;

import com.pec.studentportal.Services.TeacherService;
import com.pec.studentportal.dto.AssignmentDto;
import com.pec.studentportal.dto.QuizDTO;
import com.pec.studentportal.dto.SubjectsEnrolledDTO;
import com.pec.studentportal.response.GenericApiDataResponse;
import com.pec.studentportal.response.GenericApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(method = RequestMethod.GET, value="/fetchSubjectForATeacher/{id}")
    public GenericApiDataResponse<List<SubjectsEnrolledDTO>> fetchSubjectForATeacher(@PathVariable String id) {
        return teacherService.fetchSubjectForATeacher(Integer.parseInt(id));
    }

    @RequestMapping(method = RequestMethod.POST, value="/postAQuiz")
    public GenericApiResponse postAQuiz(@RequestParam Integer teacherId, @RequestParam String courseCode, @RequestBody QuizDTO quizDetails) {
        return teacherService.postAQuiz(teacherId, courseCode, quizDetails);
    }

    @RequestMapping(method = RequestMethod.POST, value="/postAnAssignment")
    public GenericApiResponse postAnAssignment(@RequestParam Integer teacherId, @RequestParam String courseCode, @RequestBody AssignmentDto assignmentDetails) {
        return teacherService.postAnAssignment(teacherId, courseCode, assignmentDetails);
    }

}
