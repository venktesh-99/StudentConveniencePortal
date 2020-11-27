package com.pec.studentportal.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarksDetail {



    private Integer marksObtained;

    private Integer maximumMarks;

    private String description;
}
