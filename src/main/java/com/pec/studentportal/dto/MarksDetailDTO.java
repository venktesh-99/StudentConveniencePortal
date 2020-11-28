package com.pec.studentportal.dto;

import com.pec.studentportal.pojo.MarksDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarksDetailDTO {

    private Integer finalMarksObtained;

    private Integer finalGradeObtained;

    private Map<String, List<MarksDetail>> marksDistribution;

}
