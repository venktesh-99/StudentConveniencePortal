package com.pec.studentportal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pec.studentportal.enums.BookReturnStatus;
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
public class BookIssueRecord extends AbstractEntity<Integer> {

    private Integer studentId;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private BookReturnStatus bookReturnStatus;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "book_id")
    private Book book;
}
