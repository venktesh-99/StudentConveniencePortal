package com.pec.studentportal.pojo;

import com.pec.studentportal.enums.EvaluationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MarksDistributionPojo {

    private Integer studentId;

    private String courseCode;

    private String evaluationType;

    private Double marksObtained;

    private Double maximumMarks;

    private String description;

}
