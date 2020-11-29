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
public class BookDetailPojo {

    private String bookId;

    private String author;

    private String title;

    private String description;

    private Integer edition;

    private LocalDate issueDate;

    private LocalDate dueDate;

}
