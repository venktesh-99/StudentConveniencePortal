package com.pec.studentportal.Services;

import com.pec.studentportal.Entity.*;
import com.pec.studentportal.Repository.*;
import com.pec.studentportal.dto.RegistrationDTO;
import com.pec.studentportal.enums.*;
import com.pec.studentportal.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TestingService {

    private static final Logger log = LoggerFactory.getLogger(TestingService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentSubjectRegistrationRepository studentSubjectRegistrationRepository;

    @Autowired
    private MarksDistributionRepository marksDistributionRepository;

    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    public void saveDepartment(DepartmentPojo departmentPojo) {
        List<Student> students = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        Student s1 = Student.builder()
                .studentId(17103034)
                .email("vj@gmail.com")
                .firstName("venky")
                .middleName("no")
                .lastName("jain")
                .contactNumbers("123,456")
                .category(Category.GENERAL)
                .gender(Gender.MALE)
                .startYearOfEducation(2017)
                .endYearOfEducation(2021)
                .graduationLevel(GraduationLevel.UNDER_GRADUATE)
                .build();
        Student s2 = Student.builder()
                .studentId(171030341)
                .email("vj1@gmail.com")
                .firstName("venky1")
                .middleName("no1")
                .lastName("jain1")
                .contactNumbers("1231,4561")
                .category(Category.GENERAL)
                .gender(Gender.MALE)
                .startYearOfEducation(2017)
                .endYearOfEducation(2021)
                .graduationLevel(GraduationLevel.UNDER_GRADUATE)
                .build();
        students.add(s1);students.add(s2);

        Teacher t1 = Teacher.builder()
                .email("teacher1@gmail.com")
                .firstName("t1")
                .middleName("no1")
                .lastName("t2")
                .contactNumbers("1231,4561")
                .gender(Gender.MALE)
                .build();
        Teacher t2 = Teacher.builder()
                .email("teacher2@gmail.com")
                .firstName("t2")
                .middleName("no2")
                .lastName("t3")
                .contactNumbers("12341,45461")
                .gender(Gender.MALE)
                .build();
        teachers.add(t1);teachers.add(t2);

        Department department = Department.builder()
                .departmentName(departmentPojo.getDepartmentName())
                .build();

        t1.setDepartment(department);t2.setDepartment(department);
        s1.setDepartment(department);s2.setDepartment(department);
        department.setStudents(students); department.setTeachers(teachers);
        departmentRepository.save(department);
    }

    public void saveStudent(StudentPojo studentPojo) {
        List<StudentRelatives> studentRelatives = new ArrayList<>();
        StudentRelatives s1 = new StudentRelatives("P1", Gender.MALE, Relation.FATHER, Arrays.asList("123","456","789"));
        StudentRelatives s2 = new StudentRelatives("P2", Gender.FEMALE, Relation.MOTHER, Arrays.asList("1233","4456","7789"));
        studentRelatives.add(s1);studentRelatives.add(s2);
        Student student = Student.builder()
                .studentId(studentPojo.getStudentId())
                .email(studentPojo.getEmail())
                .firstName(studentPojo.getFirstName())
                .middleName(studentPojo.getMiddleName())
                .lastName(studentPojo.getLastName())
                .contactNumbers(studentPojo.getContactNumbers())
                .category(studentPojo.getCategory())
                .gender(studentPojo.getGender())
                .startYearOfEducation(studentPojo.getStartYearOfEducation())
                .endYearOfEducation(studentPojo.getEndYearOfEducation())
                .graduationLevel(studentPojo.getGraduationLevel())
                .build();
        s1.setStudent(student);
        s2.setStudent(student);
        student.setStudentRelatives(studentRelatives);
        studentRepository.save(student);
    }

    public void saveSubject(SubjectPojo subjectPojo) {
        Subject subject = Subject.builder()
                .courseCode(subjectPojo.getCourseCode())
                .courseName(subjectPojo.getCourseName())
                .build();
        subjectRepository.save(subject);
    }

    public void registerStudentForCourse(StudentSubjectRegistrationPojo studentSubjectRegistrationPojo) {
        Student student = studentRepository.findByStudentId(studentSubjectRegistrationPojo.getStudentId());
        Subject subject = subjectRepository.findByCourseCode(studentSubjectRegistrationPojo.getCourseCode());
        StudentSubjectRegistration studentSubjectRegistration = StudentSubjectRegistration.builder()
                .student(student)
                .subject(subject)
                .build();
        studentSubjectRegistrationRepository.save(studentSubjectRegistration);
    }

    public void addMarks(MarksDistributionPojo marksDistributionPojo) {
        Student student = studentRepository.findByStudentId(marksDistributionPojo.getStudentId());
        StudentSubjectRegistration studentSubjectRegistration = student.getSubjectRegistrations().stream().filter(studentSubjectRegistration1 -> studentSubjectRegistration1.getSubject().getCourseCode().equals(marksDistributionPojo.getCourseCode())).collect(Collectors.toList()).get(0);
        MarksDistribution marksDistribution = MarksDistribution.builder()
                .evaluationType(EvaluationType.valueOf(marksDistributionPojo.getEvaluationType()))
                .marksObtained(marksDistributionPojo.getMarksObtained())
                .maximumMarks(marksDistributionPojo.getMaximumMarks())
                .description(marksDistributionPojo.getDescription())
                .studentSubjectRegistration(studentSubjectRegistration)
                .build();
        marksDistributionRepository.save(marksDistribution);
    }

    public void addAttendance(AttendancePojo attendancePojo) {
        Student student = studentRepository.findByStudentId(attendancePojo.getStudentId());
        StudentSubjectRegistration studentSubjectRegistration = student.getSubjectRegistrations().stream().filter(studentSubjectRegistration1 -> studentSubjectRegistration1.getSubject().getCourseCode().equals(attendancePojo.getCourseCode())).collect(Collectors.toList()).get(0);
        AttendanceRecord attendanceRecord = AttendanceRecord.builder()
                .attendanceCount(attendancePojo.getAttendanceCount())
                .attendanceStatus(AttendanceStatus.valueOf(attendancePojo.getAttendanceStatus()))
                .date(LocalDate.now())
                .studentSubjectRegistration(studentSubjectRegistration)
                .build();
        attendanceRecordRepository.save(attendanceRecord);
    }

    public Student getStudentById(Integer id) {
        Student student = studentRepository.findByStudentId(id);
//        log.debug("dept:{}",student.getDepartment().getDepartmentName());
        return student;
    }

    public void saveUser(RegistrationDTO registrationDTO) {
        RegisteredUser user = RegisteredUser.builder().emailId(registrationDTO.getEmailId()).password(registrationDTO.getPassword()).userRole(Role.valueOf(registrationDTO.getUserRole())).build();
        registeredUserRepository.save(user);
    }

    public RegisteredUser getUserByEmail(String emailId) {
        return registeredUserRepository.findByEmailId(emailId);
    }

}
