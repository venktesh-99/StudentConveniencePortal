package com.pec.studentportal.dto;

import com.pec.studentportal.pojo.AttendanceDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceRecordDTO {

    private Integer totalLectures;

    private Integer totalLecturesAttended;

    private List<AttendanceDetail> attendanceDetails;

}
