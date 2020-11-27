package com.pec.studentportal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pec.studentportal.enums.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceRecord extends AbstractEntity<Integer> {

    private LocalDate date;

    private Integer attendanceCount;

    private AttendanceStatus attendanceStatus;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "studentSubjectRegistration_id")
    private StudentSubjectRegistration studentSubjectRegistration;
}
