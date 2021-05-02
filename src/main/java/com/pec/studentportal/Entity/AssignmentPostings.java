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
public class AssignmentPostings extends AbstractEntity<Integer> {

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;

}
