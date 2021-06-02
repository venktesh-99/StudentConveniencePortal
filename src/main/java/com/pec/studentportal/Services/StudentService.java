package com.pec.studentportal.Services;

import com.pec.studentportal.Entity.AttendanceRecord;
import com.pec.studentportal.Entity.MarksDistribution;
import com.pec.studentportal.dto.*;
import com.pec.studentportal.enums.EvaluationType;
import com.pec.studentportal.response.GenericApiDataResponse;

import java.util.List;
import java.util.Map;

public interface StudentService {

    GenericApiDataResponse<List<SubjectsEnrolledDTO>> fetchSubjectEnrollments(Integer studentId);

    GenericApiDataResponse<MarksDetailDTO> fetchMarksForASubject(Integer studentId, String courseCode);

    GenericApiDataResponse<AttendanceRecordDTO> fetchAttendanceForASubject(Integer studentId, String courseCode);

    GenericApiDataResponse<Map<String, Map<EvaluationType, List<MarksDistribution>>>> fetchMarks(Integer studentId);

    GenericApiDataResponse<Map<String, List<AttendanceRecord>>> fetchAttendance(Integer studentId);

    GenericApiDataResponse<List<QuizDetailsDto>> fetchQuizFeed(Integer studentId);

    GenericApiDataResponse<List<AssignmentDetailsDto>> fetchAssignmentFeed(Integer studentId);

    GenericApiDataResponse<List<EvaluationComponentDto>> getEvaluationComponentsForASubject(String courseCode);

}
