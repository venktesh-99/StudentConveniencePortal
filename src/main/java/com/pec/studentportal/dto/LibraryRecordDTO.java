package com.pec.studentportal.dto;

import com.pec.studentportal.pojo.BookDetailPojo;
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
public class LibraryRecordDTO {

    private Integer totalBooksReturned;

    private Integer totalBooksWhoseDueDateIsNotPassed;

    private Integer totalBooksWhoseDueDateIsPassed;

    private Map<String, List<BookDetailPojo>> bookIssueMap;

}
