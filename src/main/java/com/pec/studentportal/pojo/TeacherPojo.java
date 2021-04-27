package com.pec.studentportal.pojo;

import com.pec.studentportal.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherPojo {

    private String email;

    private Integer teacherId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String contactNumbers;

    private Gender gender;
}
