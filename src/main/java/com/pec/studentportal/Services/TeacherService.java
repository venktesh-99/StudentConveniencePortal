package com.pec.studentportal.Services;

import com.pec.studentportal.dto.AssignmentDto;
import com.pec.studentportal.dto.EvaluationComponentDto;
import com.pec.studentportal.dto.QuizDTO;
import com.pec.studentportal.dto.SubjectsEnrolledDTO;
import com.pec.studentportal.response.GenericApiDataResponse;
import com.pec.studentportal.response.GenericApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeacherService {

    GenericApiDataResponse<List<SubjectsEnrolledDTO>> fetchSubjectForATeacher(Integer teacherId);

    GenericApiResponse postAQuiz(Integer teacherId, String courseCode, QuizDTO quizDetails);

    GenericApiResponse postAnAssignment(Integer teacherId, String courseCode, AssignmentDto assignmentDetails);

    GenericApiResponse uploadMarks(Integer evaluationComponentId, String courseCode, String description, String evaluationType, Double maximumMarks, MultipartFile file);

    GenericApiResponse uploadAttendance(String courseCode, String attendanceDate, Integer attendanceCount, MultipartFile file);

    GenericApiResponse addEvaluationComponent(Integer teacherId, String courseCode, EvaluationComponentDto evaluationComponentDto);

    GenericApiDataResponse<List<EvaluationComponentDto>> getEvaluationComponents(Integer teacherId, String courseCode);

    GenericApiDataResponse<List<EvaluationComponentDto>> getEvaluationComponentsForDropDown(Integer teacherId, String courseCode, String evaluationType);

    GenericApiDataResponse<List<EvaluationComponentDto>> getEvaluationComponentsForMarksUploadDropDown(Integer teacherId, String courseCode);

}
