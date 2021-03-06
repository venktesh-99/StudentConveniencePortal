package com.pec.studentportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentDto {

    private Integer evaluationComponentId;

    private String assignmentTitle;

    private String deadlineDate;

    private String deadlineTimings;

    private String description;

}
