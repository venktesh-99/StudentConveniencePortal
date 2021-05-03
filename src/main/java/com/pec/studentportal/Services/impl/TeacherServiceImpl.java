package com.pec.studentportal.Services.impl;

import com.pec.studentportal.Entity.*;
import com.pec.studentportal.Repository.*;
import com.pec.studentportal.Services.TeacherService;
import com.pec.studentportal.dto.AssignmentDto;
import com.pec.studentportal.dto.QuizDTO;
import com.pec.studentportal.dto.SubjectsEnrolledDTO;
import com.pec.studentportal.enums.AttendanceStatus;
import com.pec.studentportal.enums.EvaluationType;
import com.pec.studentportal.response.GenericApiDataResponse;
import com.pec.studentportal.response.GenericApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private static final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizPostingsRepository quizPostingsRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AssignmentPostingsRepository assignmentPostingsRepository;

    @Autowired
    private MarksDistributionRepository marksDistributionRepository;

    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

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
                    .quizTitle(quizDetails.getQuizTitle())
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
            return new GenericApiResponse(false, "Some error occurred.");
        }
    }

    @Override
    public GenericApiResponse postAnAssignment(Integer teacherId, String courseCode, AssignmentDto assignmentDetails) {
        try {
            Teacher teacher = teacherRepository.findByTeacherId(teacherId);
            Subject subject = subjectRepository.findByCourseCode(courseCode);
            Assignment assignment = Assignment.builder()
                    .assignmentTitle(assignmentDetails.getAssignmentTitle())
                    .deadlineDate(assignmentDetails.getDeadlineDate())
                    .deadlineTimings(assignmentDetails.getDeadlineTimings())
                    .description(assignmentDetails.getDescription())
                    .teacher(teacher)
                    .subject(subject)
                    .build();
            assignmentRepository.save(assignment);

            List<Student> studentsEnrolled = getStudentsEnrolledInACourse(subject);
            studentsEnrolled.forEach(student -> {
                AssignmentPostings assignmentPosting = AssignmentPostings.builder().assignment(assignment).student(student).build();
                assignmentPostingsRepository.save(assignmentPosting);
            });
            return new GenericApiResponse(true, "Success.");
        } catch (Exception e) {
            log.error("post_assignment_error:teacherId:{}, courseCode:{}, assignmentDetails:{} with error:{}", teacherId, courseCode, assignmentDetails, e);
            return new GenericApiResponse(false, "Some error occurred.");
        }
    }

    @Override
    public GenericApiResponse uploadMarks(String courseCode, String description, String evaluationType, Double maximumMarks, MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            ByteArrayInputStream inputFileStream = new ByteArrayInputStream(bytes);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFileStream));
            String line = "";
            while ((line = br.readLine()) != null) {
                List<String> strings = Arrays.asList(line.split(","));
                Integer studentId = Integer.parseInt(strings.get(0));
                Double marksObtained = Double.parseDouble(strings.get(1));
                Student student = studentRepository.findByStudentId(studentId);
                List<StudentSubjectRegistration> studentSubjectRegistrationList = student.getSubjectRegistrations().stream().filter(studentSubjectRegistration1 -> studentSubjectRegistration1.getSubject().getCourseCode().equals(courseCode)).collect(Collectors.toList());
                if(!CollectionUtils.isEmpty(studentSubjectRegistrationList)) {
                    StudentSubjectRegistration studentSubjectRegistration = studentSubjectRegistrationList.get(0);
                    MarksDistribution marksDistribution = MarksDistribution.builder()
                            .maximumMarks(maximumMarks)
                            .marksObtained(marksObtained)
                            .evaluationType(EvaluationType.valueOf(evaluationType))
                            .description(description)
                            .studentSubjectRegistration(studentSubjectRegistration)
                            .build();
                    marksDistributionRepository.save(marksDistribution);
                }
            }
            br.close();
            return new GenericApiResponse(true, "Success.");
        } catch (Exception e) {
            log.error("upload_marks_error:courseCode:{}, evaluationType:{} with error:{}", courseCode, evaluationType, e);
            return new GenericApiResponse(false, "Some error occurred.");
        }
    }

    @Override
    public GenericApiResponse uploadAttendance(String courseCode, String attendanceDate, Integer attendanceCount, MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            ByteArrayInputStream inputFileStream = new ByteArrayInputStream(bytes);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFileStream));
            String line = "";
            while ((line = br.readLine()) != null) {
                List<String> strings = Arrays.asList(line.split(","));
                Integer studentId = Integer.parseInt(strings.get(0));
                AttendanceStatus attendanceStatus = getAttendanceStatus(strings.get(1));
                Student student = studentRepository.findByStudentId(studentId);
                List<StudentSubjectRegistration> studentSubjectRegistrationList = student.getSubjectRegistrations().stream().filter(studentSubjectRegistration1 -> studentSubjectRegistration1.getSubject().getCourseCode().equals(courseCode)).collect(Collectors.toList());
                if(!CollectionUtils.isEmpty(studentSubjectRegistrationList)) {
                    StudentSubjectRegistration studentSubjectRegistration = studentSubjectRegistrationList.get(0);
                    AttendanceRecord attendanceRecord = AttendanceRecord.builder()
                            .date(LocalDate.parse(attendanceDate, DateTimeFormatter.ofPattern("dd/MM/uuuu")))
                            .attendanceCount(attendanceCount)
                            .attendanceStatus(attendanceStatus)
                            .studentSubjectRegistration(studentSubjectRegistration)
                            .build();
                    attendanceRecordRepository.save(attendanceRecord);
                }
            }
            br.close();
            return new GenericApiResponse(true, "Success.");
        } catch (Exception e) {
            log.error("upload_attendance_error:courseCode:{}, attendanceDate:{} with error:{}", courseCode, attendanceDate, e);
            return new GenericApiResponse(false, "Some error occurred.");
        }
    }

    private List<Student> getStudentsEnrolledInACourse(Subject subject) {
        List<StudentSubjectRegistration> studentSubjectRegistrations = subject.getStudentRegistrations();
        List<Student> studentList = new ArrayList<>();
        studentSubjectRegistrations.forEach(studentSubjectRegistration -> studentList.add(studentSubjectRegistration.getStudent()));
        return studentList;
    }

    private AttendanceStatus getAttendanceStatus(String attendanceStatus) {
        if(attendanceStatus.equals("P") || attendanceStatus.equals("p") || attendanceStatus.toLowerCase().equals("present")) {
            return AttendanceStatus.PRESENT;
        }
        return AttendanceStatus.ABSENT;
    }
}
