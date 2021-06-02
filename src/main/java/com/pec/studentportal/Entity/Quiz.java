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
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quiz extends AbstractEntity<Integer> {

    private String quizTitle;

    private String quizDate;

    private String quizTimings;

    private String syllabus;

    private String quizInstructions;

    private Integer evaluationComponentId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "quiz")
    private List<QuizPostings> quizPostings;

}
