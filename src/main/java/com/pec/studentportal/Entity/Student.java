package com.pec.studentportal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pec.studentportal.enums.Category;
import com.pec.studentportal.enums.Gender;
import com.pec.studentportal.enums.GraduationLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student extends AbstractEntity<Integer> {

    @Column(nullable = false)
    private Integer studentId;

    @Column(nullable = false)
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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="department_id")
    private Department department;

    @OneToMany(targetEntity=StudentRelatives.class, mappedBy="student",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentRelatives> studentRelatives;

    @OneToMany(mappedBy = "student")
    private List<StudentSubjectRegistration> subjectRegistrations;

    @OneToMany(mappedBy = "student")
    private List<QuizPostings> quizPostings;

    public void setContactNumbers(List<String> contactNumbers) {
        this.contactNumbers = String.join(",", contactNumbers);
    }

    public List<String> getContactNumbers() {
        return Arrays.asList(this.contactNumbers.split(",", -1));
    }

}
