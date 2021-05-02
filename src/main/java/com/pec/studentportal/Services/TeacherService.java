package com.pec.studentportal.Services;

import com.pec.studentportal.dto.AssignmentDto;
import com.pec.studentportal.dto.QuizDTO;
import com.pec.studentportal.dto.SubjectsEnrolledDTO;
import com.pec.studentportal.response.GenericApiDataResponse;
import com.pec.studentportal.response.GenericApiResponse;

import java.util.List;

public interface TeacherService {

    GenericApiDataResponse<List<SubjectsEnrolledDTO>> fetchSubjectForATeacher(Integer teacherId);

    GenericApiResponse postAQuiz(Integer teacherId, String courseCode, QuizDTO quizDetails);

    GenericApiResponse postAnAssignment(Integer teacherId, String courseCode, AssignmentDto assignmentDetails);

}
