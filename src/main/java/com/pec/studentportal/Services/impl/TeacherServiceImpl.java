package com.pec.studentportal.Services.impl;

import com.pec.studentportal.Entity.Teacher;
import com.pec.studentportal.Entity.TeacherSubjectRegisteration;
import com.pec.studentportal.Repository.TeacherRepository;
import com.pec.studentportal.Services.TeacherService;
import com.pec.studentportal.dto.SubjectsEnrolledDTO;
import com.pec.studentportal.response.GenericApiDataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class TeacherServiceImpl implements TeacherService {

    private static final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    private TeacherRepository teacherRepository;

    public GenericApiDataResponse<List<SubjectsEnrolledDTO>> fetchSubjectForATeacher(Integer teacherId)
    {
        try {
            Teacher teacher = teacherRepository.findByTeacherId(teacherId);
            List<TeacherSubjectRegisteration> teacherSubjectRegisterations = teacher.getSubjectRegisterations();
            List<SubjectsEnrolledDTO> subjectsEnrolledDTOS = new ArrayList<>();
            teacherSubjectRegisterations.forEach(teacherSubjectRegisteration -> {
                SubjectsEnrolledDTO subjectsEnrolledDTO = SubjectsEnrolledDTO.builder().courseCode(teacherSubjectRegisteration.getSubject().getCourseCode()).courseName(teacherSubjectRegisteration.getSubject().getCourseName()).build();
                subjectsEnrolledDTOS.add(subjectsEnrolledDTO);
            });
            return new GenericApiDataResponse<>(true, "Success", subjectsEnrolledDTOS);
        } catch (Exception e) {

            log.error("fetch_subject_enrollments_error:teacherId:{} with error:{}",teacherId,e);
            return new GenericApiDataResponse<>(false,"Some error occurred.",null);
        }
    }
}
