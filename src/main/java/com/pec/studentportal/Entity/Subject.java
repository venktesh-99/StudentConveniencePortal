package com.pec.studentportal.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject extends AbstractEntity<Integer> {

    private String courseCode;

    private String courseName;

    @OneToMany(mappedBy = "subject")
    private List<StudentSubjectRegistration> studentRegistrations;

}
