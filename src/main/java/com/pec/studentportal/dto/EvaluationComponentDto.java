package com.pec.studentportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvaluationComponentDto {

    private Integer evaluationComponentId;

    private String evaluationType;

    private String evaluationTitle;

    private Double weightAge;

    private String evaluationDate;

}
