package com.pec.studentportal.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookIssueRecordPojo {

    private Integer studentId;

    private String bookId;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private String bookReturnStatus;

}
