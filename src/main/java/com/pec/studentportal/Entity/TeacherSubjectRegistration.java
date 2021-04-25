package com.pec.studentportal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherSubjectRegistration extends AbstractEntity<Integer>{

    @ManyToMany
    @JsonIgnore
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
