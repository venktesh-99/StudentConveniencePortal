package com.pec.studentportal.Services;

import com.pec.studentportal.dto.AssignmentDto;
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

    GenericApiResponse uploadMarks(String courseCode, String description, String evaluationType, Double maximumMarks, MultipartFile file);

    GenericApiResponse uploadAttendance(String courseCode, String attendanceDate, Integer attendanceCount, MultipartFile file);

}
