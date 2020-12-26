package com.pec.studentportal.Controller;

import com.pec.studentportal.Entity.AttendanceRecord;
import com.pec.studentportal.Entity.MarksDistribution;
import com.pec.studentportal.Services.StudentService;
import com.pec.studentportal.dto.AttendanceRecordDTO;
import com.pec.studentportal.dto.MarksDetailDTO;
import com.pec.studentportal.dto.SubjectsEnrolledDTO;
import com.pec.studentportal.enums.EvaluationType;
import com.pec.studentportal.response.GenericApiDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


//This is my branch, Venky fuck off
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET, value = "/fetchSubjectEnrollments/{id}")
    public GenericApiDataResponse<List<SubjectsEnrolledDTO>> fetchSubjectEnrollments(@PathVariable String id) {
        return studentService.fetchSubjectEnrollments(Integer.parseInt(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fetchMarksForASubject")
    public GenericApiDataResponse<MarksDetailDTO> fetchMarksForASubject(@RequestParam String id, @RequestParam String courseCode) {
        return studentService.fetchMarksForASubject(Integer.parseInt(id), courseCode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fetchAttendanceForASubject")
    public GenericApiDataResponse<AttendanceRecordDTO> fetchAttendanceForASubject(@RequestParam String id, @RequestParam String courseCode) {
        return studentService.fetchAttendanceForASubject(Integer.parseInt(id), courseCode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fetchMarks/{id}")
    public GenericApiDataResponse<Map<String, Map<EvaluationType,List<MarksDistribution>>>> fetchMarks(@PathVariable String id) {
        return studentService.fetchMarks(Integer.parseInt(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fetchAttendance/{id}")
    public GenericApiDataResponse<Map<String, List<AttendanceRecord>>> fetchAttendance(@PathVariable String id) {
        return studentService.fetchAttendance(Integer.parseInt(id));
    }
}
