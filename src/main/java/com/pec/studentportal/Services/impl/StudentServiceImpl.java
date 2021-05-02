package com.pec.studentportal.Services.impl;

import com.pec.studentportal.Entity.*;
import com.pec.studentportal.Repository.StudentRepository;
import com.pec.studentportal.Services.StudentService;
import com.pec.studentportal.dto.*;
import com.pec.studentportal.enums.EvaluationType;
import com.pec.studentportal.pojo.AttendanceDetail;
import com.pec.studentportal.pojo.MarksDetail;
import com.pec.studentportal.response.GenericApiDataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public GenericApiDataResponse<List<SubjectsEnrolledDTO>> fetchSubjectEnrollments(Integer studentId) {
        try{
            Student student = studentRepository.findByStudentId(studentId);
            List<StudentSubjectRegistration> studentSubjectRegistrations = student.getSubjectRegistrations();
            List<SubjectsEnrolledDTO> subjectsEnrolledDTOS = new ArrayList<>();
            studentSubjectRegistrations.forEach(studentSubjectRegistration -> {
                SubjectsEnrolledDTO subjectsEnrolledDTO = SubjectsEnrolledDTO.builder().courseCode(studentSubjectRegistration.getSubject().getCourseCode()).courseName(studentSubjectRegistration.getSubject().getCourseName()).build();
                subjectsEnrolledDTOS.add(subjectsEnrolledDTO);
            });
            return new GenericApiDataResponse<>(true,"Success",subjectsEnrolledDTOS);
        } catch (Exception e) {
            log.error("fetch_subject_enrollments_error:studentId:{} with error:{}",studentId,e);
            return new GenericApiDataResponse<>(false,"Some error occurred.",null);
        }
    }

    @Override
    public GenericApiDataResponse<MarksDetailDTO> fetchMarksForASubject(Integer studentId, String courseCode) {
        try {
            Student student = studentRepository.findByStudentId(studentId);
            List<StudentSubjectRegistration> studentSubjectRegistrations = student.getSubjectRegistrations();
            List<StudentSubjectRegistration> studentSubjectRegistrationsForCourse = studentSubjectRegistrations.stream().filter(studentSubjectRegistration1 -> studentSubjectRegistration1.getSubject().getCourseCode().equals(courseCode)).collect(Collectors.toList());
            if(CollectionUtils.isEmpty(studentSubjectRegistrations)) {
                return new GenericApiDataResponse<>(true,studentId + " is not registered for course: "+courseCode,null);
            }
            StudentSubjectRegistration studentSubjectRegistration = studentSubjectRegistrationsForCourse.get(0);
            List<MarksDistribution> marksDistributions = studentSubjectRegistration.getMarksDistributionList();
            Map<String, List<MarksDetail>> evaluationTypeToMarksDistribution = new HashMap<>();
            marksDistributions.forEach(marksDistribution -> {
                MarksDetail marksDetail = MarksDetail.builder().marksObtained(marksDistribution.getMarksObtained()).maximumMarks(marksDistribution.getMaximumMarks()).description(marksDistribution.getDescription()).build();
                if(evaluationTypeToMarksDistribution.containsKey(marksDistribution.getEvaluationType().toString())) {
                    evaluationTypeToMarksDistribution.get(marksDistribution.getEvaluationType().toString()).add(marksDetail);
                }
                else {
                    List<MarksDetail> marksDetails = new ArrayList<>();
                    marksDetails.add(marksDetail);
                    evaluationTypeToMarksDistribution.put(marksDistribution.getEvaluationType().toString(),marksDetails);
                }
            });
            MarksDetailDTO marksDetailDTO = MarksDetailDTO.builder()
                    .finalMarksObtained(studentSubjectRegistration.getFinalMarksObtained())
                    .finalGradeObtained(studentSubjectRegistration.getFinalGradeObtained())
                    .marksDistribution(evaluationTypeToMarksDistribution)
                    .build();
            return new GenericApiDataResponse<>(true,"Success",marksDetailDTO);
        } catch (Exception e) {
            log.error("fetch_marks_error:studentId:{} with error:{}",studentId,e);
            return new GenericApiDataResponse<>(false,"Some error occurred.",null);
        }
    }

    @Override
    public GenericApiDataResponse<AttendanceRecordDTO> fetchAttendanceForASubject(Integer studentId, String courseCode) {
        try {
            Student student = studentRepository.findByStudentId(studentId);
            List<StudentSubjectRegistration> studentSubjectRegistrations = student.getSubjectRegistrations();
            List<StudentSubjectRegistration> studentSubjectRegistrationsForCourse = studentSubjectRegistrations.stream().filter(studentSubjectRegistration1 -> studentSubjectRegistration1.getSubject().getCourseCode().equals(courseCode)).collect(Collectors.toList());
            if(CollectionUtils.isEmpty(studentSubjectRegistrations)) {
                return new GenericApiDataResponse<>(true,studentId + " is not registered for course: "+courseCode,null);
            }
            StudentSubjectRegistration studentSubjectRegistration = studentSubjectRegistrationsForCourse.get(0);
            List<AttendanceRecord> attendanceRecords = studentSubjectRegistration.getAttendanceRecords();
            List<AttendanceDetail> attendanceDetails = new ArrayList<>();
            attendanceRecords.forEach(attendanceRecord -> {
                AttendanceDetail attendanceDetail = AttendanceDetail.builder()
                        .date(attendanceRecord.getDate())
                        .attendanceCount(attendanceRecord.getAttendanceCount())
                        .attendanceStatus(attendanceRecord.getAttendanceStatus().toString())
                        .build();
                attendanceDetails.add(attendanceDetail);
            });
            AttendanceRecordDTO attendanceRecordDTO = AttendanceRecordDTO.builder()
                    .totalLectures(studentSubjectRegistration.getTotalLectures())
                    .totalLecturesAttended(studentSubjectRegistration.getTotalLecturesAttended())
                    .attendanceDetails(attendanceDetails)
                    .build();
            return new GenericApiDataResponse<>(true,"Success",attendanceRecordDTO);
        } catch (Exception e) {
            log.error("fetch_attendance_error:studentId:{} , courseCode:{} with error:{}",studentId,courseCode,e);
            return new GenericApiDataResponse<>(false,"Some error occurred.",null);
        }
    }

    @Override
    public GenericApiDataResponse<Map<String, Map<EvaluationType,List<MarksDistribution>>>> fetchMarks(Integer studentId) {
        try {
            Student student = studentRepository.findByStudentId(studentId);
            List<StudentSubjectRegistration> studentSubjectRegistrations = student.getSubjectRegistrations();
            Map<String, Map<EvaluationType,List<MarksDistribution>>> subjectToMarksDetails = new HashMap<>();
            studentSubjectRegistrations.forEach(studentSubjectRegistration -> {
                List<MarksDistribution> marksDistributions = studentSubjectRegistration.getMarksDistributionList();
                Map<EvaluationType,List<MarksDistribution>> evaluationTypeListMap = marksDistributions.stream().collect(Collectors.groupingBy(
                        MarksDistribution::getEvaluationType, Collectors.mapping(marksDistribution -> marksDistribution, Collectors.toList())
                ));
                String key = studentSubjectRegistration.getSubject().getCourseCode() + " " + studentSubjectRegistration.getSubject().getCourseName();
                subjectToMarksDetails.put(key, evaluationTypeListMap);
            });
            return new GenericApiDataResponse<>(true,"Success",subjectToMarksDetails);
        } catch (Exception e) {
            log.error("fetch_marks_error:studentId:{} with error:{}",studentId,e);
            return new GenericApiDataResponse<>(false,"Some error occurred.",null);
        }
    }

    @Override
    public GenericApiDataResponse<Map<String, List<AttendanceRecord>>> fetchAttendance(Integer studentId) {
        try{
            Student student = studentRepository.findByStudentId(studentId);
            List<StudentSubjectRegistration> studentSubjectRegistrations = student.getSubjectRegistrations();
            Map<String, List<AttendanceRecord>> subjectToAttendanceDetails = new HashMap<>();
            studentSubjectRegistrations.forEach(studentSubjectRegistration -> {
                List<AttendanceRecord> attendanceRecords = studentSubjectRegistration.getAttendanceRecords();
                String key = studentSubjectRegistration.getSubject().getCourseCode() + " " + studentSubjectRegistration.getSubject().getCourseName();
                subjectToAttendanceDetails.put(key, attendanceRecords);
            });
            return new GenericApiDataResponse<>(true,"Success",subjectToAttendanceDetails);
        } catch (Exception e) {
            log.error("fetch_attendance_error:studentId:{} with error:{}",studentId,e);
            return new GenericApiDataResponse<>(false,"Some error occurred.",null);
        }
    }

    @Override
    public GenericApiDataResponse<List<QuizDetailsDto>> fetchQuizFeed(Integer studentId) {
        try {
            Student student = studentRepository.findByStudentId(studentId);
            List<QuizDetailsDto> quizDetailsDtoList = new ArrayList<>();
            List<QuizPostings> quizPostings = student.getQuizPostings();
            if (CollectionUtils.isEmpty(quizPostings)) {
                return new GenericApiDataResponse<>(true, "Success", quizDetailsDtoList);
            }
            quizPostings.forEach(quizPosting -> {
                Quiz quiz = quizPosting.getQuiz();
                QuizDetailsDto quizDetailsDto = QuizDetailsDto.builder()
                        .quizSubjectCode(quiz.getSubject().getCourseCode())
                        .quizSubjectName(quiz.getSubject().getCourseName())
                        .quizTitle(quiz.getQuizTitle())
                        .quizDate(quiz.getQuizDate())
                        .quizInstructions(quiz.getQuizInstructions())
                        .quizTimings(quiz.getQuizTimings())
                        .syllabus(quiz.getSyllabus())
                        .build();
                quizDetailsDtoList.add(quizDetailsDto);
            });
            return new GenericApiDataResponse<>(true, "Success", quizDetailsDtoList);
        } catch (Exception e) {
            log.error("fetch_quiz_feed:studentId:{} with error:{}",studentId,e);
            return new GenericApiDataResponse<>(false,"Some error occurred.",new ArrayList<>());
        }
    }

    @Override
    public GenericApiDataResponse<List<AssignmentDetailsDto>> fetchAssignmentFeed(Integer studentId) {
        try {
            Student student = studentRepository.findByStudentId(studentId);
            List<AssignmentDetailsDto> assignmentDetailsDtoList = new ArrayList<>();
            List<AssignmentPostings> assignmentPostings = student.getAssignmentPostings();
            if (CollectionUtils.isEmpty(assignmentPostings)) {
                return new GenericApiDataResponse<>(true, "Success", assignmentDetailsDtoList);
            }
            assignmentPostings.forEach(assignmentPosting -> {
                Assignment assignment = assignmentPosting.getAssignment();
                AssignmentDetailsDto assignmentDetailsDto = AssignmentDetailsDto.builder()
                        .assignmentSubjectCode(assignment.getSubject().getCourseCode())
                        .assignmentSubjectName(assignment.getSubject().getCourseName())
                        .assignmentTitle(assignment.getAssignmentTitle())
                        .deadlineDate(assignment.getDeadlineDate())
                        .deadlineTimings(assignment.getDeadlineTimings())
                        .description(assignment.getDescription())
                        .build();
                assignmentDetailsDtoList.add(assignmentDetailsDto);
            });
            return new GenericApiDataResponse<>(true, "Success", assignmentDetailsDtoList);
        } catch (Exception e) {
            log.error("fetch_assignment_feed:studentId:{} with error:{}",studentId,e);
            return new GenericApiDataResponse<>(false,"Some error occurred.",new ArrayList<>());
        }
    }
}
