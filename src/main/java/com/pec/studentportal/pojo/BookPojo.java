package com.pec.studentportal.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookPojo {

    private String bookId;

    private String author;

    private String title;

    private String description;

    private Integer edition;

}
