package com.pec.studentportal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class TeacherSubjectRegistration extends AbstractEntity<Integer>{

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "teacherSubjectRegistration")
    private List<EvaluationComponent> evaluationComponents;

}
