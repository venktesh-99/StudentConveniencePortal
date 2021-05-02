package com.pec.studentportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentDetailsDto {

    private String assignmentSubjectCode;

    private String assignmentSubjectName;

    private String assignmentTitle;

    private String deadlineDate;

    private String deadlineTimings;

    private String description;
}
