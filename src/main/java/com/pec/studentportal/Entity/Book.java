package com.pec.studentportal.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book extends AbstractEntity<Integer> {

    private String bookId;

    private String author;

    private String title;

    private String description;

    private Integer edition;

    @OneToMany(targetEntity = BookIssueRecord.class, mappedBy="book", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookIssueRecord> bookIssueRecords;
}