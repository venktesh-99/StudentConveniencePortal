package com.pec.studentportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectsEnrolledDTO {

    private Integer courseId;

    private String courseCode;

    private String courseName;

}
