package com.pec.studentportal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvaluationComponent extends AbstractEntity<Integer> {

    private String evaluationType;

    private String evaluationTitle;

    private Double weightAge;

    private String evaluationDate;

    private Boolean isPosted;

    private Boolean isMarksUploaded;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "teacherSubjectRegistration_id")
    private TeacherSubjectRegistration teacherSubjectRegistration;

}
