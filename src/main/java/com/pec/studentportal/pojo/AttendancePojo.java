package com.pec.studentportal.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendancePojo {

    private Integer studentId;

    private String courseCode;

    private Integer attendanceCount;

    private String attendanceStatus;

}
