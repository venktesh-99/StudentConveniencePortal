package com.pec.studentportal.Services.impl;

import com.pec.studentportal.Entity.*;
import com.pec.studentportal.Repository.QuizPostingsRepository;
import com.pec.studentportal.Repository.QuizRepository;
import com.pec.studentportal.Repository.SubjectRepository;
import com.pec.studentportal.Repository.TeacherRepository;
import com.pec.studentportal.Services.TeacherService;
import com.pec.studentportal.dto.QuizDTO;
import com.pec.studentportal.dto.SubjectsEnrolledDTO;
import com.pec.studentportal.response.GenericApiDataResponse;
import com.pec.studentportal.response.GenericApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private static final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizPostingsRepository quizPostingsRepository;

    public GenericApiDataResponse<List<SubjectsEnrolledDTO>> fetchSubjectForATeacher(Integer teacherId) {
        try {
            Teacher teacher = teacherRepository.findByTeacherId(teacherId);
            List<TeacherSubjectRegistration> teacherSubjectRegistrations = teacher.getSubjectRegistrations();
            List<SubjectsEnrolledDTO> subjectsEnrolledDTOS = new ArrayList<>();
            teacherSubjectRegistrations.forEach(teacherSubjectRegistration -> {
                Subject subject = teacherSubjectRegistration.getSubject();
                SubjectsEnrolledDTO subjectsEnrolledDTO = SubjectsEnrolledDTO.builder().courseId(subject.getId()).courseCode(subject.getCourseCode()).courseName(subject.getCourseName()).build();
                subjectsEnrolledDTOS.add(subjectsEnrolledDTO);
            });
            return new GenericApiDataResponse<>(true, "Success", subjectsEnrolledDTOS);
        } catch (Exception e) {
            log.error("fetch_subject_enrollments_error:teacherId:{} with error:{}", teacherId, e);
            return new GenericApiDataResponse<>(false, "Some error occurred.", null);
        }
    }

    @Override
    public GenericApiResponse postAQuiz(Integer teacherId, String courseCode, QuizDTO quizDetails) {
        try {
            Teacher teacher = teacherRepository.findByTeacherId(teacherId);
            Subject subject = subjectRepository.findByCourseCode(courseCode);
            Quiz quiz = Quiz.builder()
                    .quizDate(quizDetails.getQuizDate())
                    .quizTimings(quizDetails.getQuizTimings())
                    .syllabus(quizDetails.getSyllabus())
                    .quizInstructions(quizDetails.getQuizInstructions())
                    .teacher(teacher)
                    .subject(subject)
                    .build();
            quizRepository.save(quiz);

            List<Student> studentsEnrolled = getStudentsEnrolledInACourse(subject);
            studentsEnrolled.forEach(student -> {
                QuizPostings quizPosting = QuizPostings.builder().quiz(quiz).student(student).build();
                quizPostingsRepository.save(quizPosting);
            });
            return new GenericApiResponse(true, "Success.");
        } catch (Exception e) {
            log.error("post_quiz_error:teacherId:{}, courseCode:{}, quizDetails:{} with error:{}", teacherId, courseCode, quizDetails, e);
            return new GenericApiDataResponse<>(false, "Some error occurred.", null);
        }
    }

    private List<Student> getStudentsEnrolledInACourse(Subject subject) {
        List<StudentSubjectRegistration> studentSubjectRegistrations = subject.getStudentRegistrations();
        List<Student> studentList = new ArrayList<>();
        studentSubjectRegistrations.forEach(studentSubjectRegistration -> studentList.add(studentSubjectRegistration.getStudent()));
        return studentList;
    }
}
