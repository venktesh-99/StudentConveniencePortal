package com.pec.studentportal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pec.studentportal.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher extends AbstractEntity<Integer> {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Integer teacherId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String contactNumbers;

    private Gender gender;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="department_id")
    private Department department;

    @OneToMany(mappedBy = "teacher")
    private List<TeacherSubjectRegistration> subjectRegistrations;

}
