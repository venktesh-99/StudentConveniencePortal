package com.pec.studentportal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentSubjectRegistration extends AbstractEntity<Integer> {

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private Integer finalMarksObtained;

    private Integer finalGradeObtained;

    private Integer totalLectures;

    private Integer totalLecturesAttended;

    @OneToMany(targetEntity = AttendanceRecord.class, mappedBy = "studentSubjectRegistration", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AttendanceRecord> attendanceRecords;

    @OneToMany(targetEntity = MarksDistribution.class, mappedBy = "studentSubjectRegistration", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MarksDistribution> marksDistributionList;

}
