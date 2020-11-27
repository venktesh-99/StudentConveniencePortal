package com.pec.studentportal.pojo;

import com.pec.studentportal.enums.Category;
import com.pec.studentportal.enums.Gender;
import com.pec.studentportal.enums.GraduationLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentPojo {

    private Integer studentId;

    private String email;

    private String firstName;

    private String middleName;

    private String lastName;

    private String contactNumbers;

    private Category category;

    private Gender gender;

    private Integer startYearOfEducation;

    private Integer endYearOfEducation;

    private GraduationLevel graduationLevel;

}
