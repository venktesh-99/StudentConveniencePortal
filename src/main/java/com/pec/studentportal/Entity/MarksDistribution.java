package com.pec.studentportal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pec.studentportal.enums.EvaluationType;
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
public class MarksDistribution extends AbstractEntity<Integer> {

    private EvaluationType evaluationType;

    private Double marksObtained;

    private Double maximumMarks;

    private String description;

    private Integer evaluationComponentId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "studentSubjectRegistration_id")
    private StudentSubjectRegistration studentSubjectRegistration;

}
