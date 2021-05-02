package com.pec.studentportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizDetailsDto {

    private String quizSubjectCode;

    private String quizSubjectName;

    private String quizTitle;

    private String quizDate;

    private String quizTimings;

    private String syllabus;

    private String quizInstructions;

}
