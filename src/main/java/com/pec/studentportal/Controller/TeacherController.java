package com.pec.studentportal.Controller;

import com.pec.studentportal.Services.TeacherService;
import com.pec.studentportal.dto.AssignmentDto;
import com.pec.studentportal.dto.EvaluationComponentDto;
import com.pec.studentportal.dto.QuizDTO;
import com.pec.studentportal.dto.SubjectsEnrolledDTO;
import com.pec.studentportal.response.GenericApiDataResponse;
import com.pec.studentportal.response.GenericApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(method = RequestMethod.GET, value = "/fetchSubjectForATeacher/{id}")
    public GenericApiDataResponse<List<SubjectsEnrolledDTO>> fetchSubjectForATeacher(@PathVariable String id) {
        return teacherService.fetchSubjectForATeacher(Integer.parseInt(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/postAQuiz")
    public GenericApiResponse postAQuiz(@RequestParam Integer teacherId, @RequestParam String courseCode, @RequestBody QuizDTO quizDetails) {
        return teacherService.postAQuiz(teacherId, courseCode, quizDetails);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/postAnAssignment")
    public GenericApiResponse postAnAssignment(@RequestParam Integer teacherId, @RequestParam String courseCode, @RequestBody AssignmentDto assignmentDetails) {
        return teacherService.postAnAssignment(teacherId, courseCode, assignmentDetails);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/uploadMarks")
    public GenericApiResponse uploadMarks(@RequestParam String courseCode, @RequestParam String description, @RequestParam String evaluationType, @RequestParam Double maximumMarks, @RequestParam MultipartFile file) {
        return teacherService.uploadMarks(courseCode, description, evaluationType, maximumMarks, file);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/uploadAttendance")
    public GenericApiResponse uploadAttendance(@RequestParam String courseCode, @RequestParam String attendanceDate, @RequestParam Integer attendanceCount, @RequestParam MultipartFile file) {
        return teacherService.uploadAttendance(courseCode, attendanceDate, attendanceCount, file);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addEvaluationComponent")
    public GenericApiResponse addEvaluationComponent(@RequestParam Integer teacherId, @RequestParam String courseCode, @RequestBody EvaluationComponentDto evaluationComponentDto) {
        return teacherService.addEvaluationComponent(teacherId, courseCode, evaluationComponentDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getEvaluationComponents")
    public GenericApiDataResponse<List<EvaluationComponentDto>> getEvaluationComponents(@RequestParam Integer teacherId, @RequestParam String courseCode) {
        return teacherService.getEvaluationComponents(teacherId, courseCode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getEvaluationComponentsForDropDown")
    public GenericApiDataResponse<List<EvaluationComponentDto>> getEvaluationComponentsForDropDown(@RequestParam Integer teacherId, @RequestParam String courseCode) {
        return teacherService.getEvaluationComponentsForDropDown(teacherId, courseCode);
    }

}
