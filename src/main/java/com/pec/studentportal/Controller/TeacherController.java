package com.pec.studentportal.Controller;

import com.pec.studentportal.Services.TeacherService;
import com.pec.studentportal.dto.SubjectsEnrolledDTO;
import com.pec.studentportal.response.GenericApiDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(method = RequestMethod.GET, value="/fetchSubjectForATeacher/{id}")
    public GenericApiDataResponse<List<SubjectsEnrolledDTO>> fetchSubjectForATeacher(@PathVariable String id)
    {
        return teacherService.fetchSubjectForATeacher(Integer.parseInt(id));
    }
}
