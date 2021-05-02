package com.pec.studentportal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Assignment extends AbstractEntity<Integer> {

    private String assignmentTitle;

    private String deadlineDate;

    private String deadlineTimings;

    private String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "assignment")
    private List<AssignmentPostings> assignmentPostings;

}
