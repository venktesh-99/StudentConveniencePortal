package com.pec.studentportal.Services.impl;

import com.pec.studentportal.Entity.Book;
import com.pec.studentportal.Entity.BookIssueRecord;
import com.pec.studentportal.Repository.BookIssueRecordRepository;
import com.pec.studentportal.Services.LibraryService;
import com.pec.studentportal.dto.LibraryRecordDTO;
import com.pec.studentportal.enums.BookReturnStatus;
import com.pec.studentportal.pojo.BookDetailPojo;
import com.pec.studentportal.response.GenericApiDataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LibraryServiceImpl implements LibraryService {

    private static final Logger log = LoggerFactory.getLogger(LibraryService.class);

    private static final String RETURNED = "returned";

    private static final String DUE_DATE_PASSED = "due_date_passed";

    private static final String DUE_DATE_NOT_PASSED = "due_date_not_passed";

    @Autowired
    private BookIssueRecordRepository bookIssueRecordRepository;

    public GenericApiDataResponse<LibraryRecordDTO> getLibraryDataForAStudent(Integer studentId) {
        try {
            List<BookIssueRecord> bookIssueRecords = bookIssueRecordRepository.findByStudentId(studentId);
            Map<String, List<BookDetailPojo>> bookIssueMap = new HashMap<>();
            bookIssueMap.put(RETURNED, new ArrayList<>());
            bookIssueMap.put(DUE_DATE_PASSED, new ArrayList<>());
            bookIssueMap.put(DUE_DATE_NOT_PASSED, new ArrayList<>());
            bookIssueRecords.forEach(bookIssueRecord -> {
                String status = getStatus(bookIssueRecord);
                Book book = bookIssueRecord.getBook();
                BookDetailPojo bookDetailPojo = BookDetailPojo.builder()
                        .bookId(book.getBookId())
                        .author(book.getAuthor())
                        .description(book.getDescription())
                        .title(book.getTitle())
                        .edition(book.getEdition())
                        .issueDate(bookIssueRecord.getIssueDate())
                        .dueDate(bookIssueRecord.getDueDate())
                        .build();
                bookIssueMap.get(status).add(bookDetailPojo);
            });
            LibraryRecordDTO libraryRecordDTO = LibraryRecordDTO.builder()
                    .totalBooksReturned(bookIssueMap.get(RETURNED).size())
                    .totalBooksWhoseDueDateIsNotPassed(bookIssueMap.get(DUE_DATE_NOT_PASSED).size())
                    .totalBooksWhoseDueDateIsPassed(bookIssueMap.get(DUE_DATE_PASSED).size())
                    .bookIssueMap(bookIssueMap)
                    .build();
            return new GenericApiDataResponse<>(true, "Success", libraryRecordDTO);
        } catch (Exception e) {
            log.error("fetch_library_data_error:studentId:{} with error:{}",studentId,e);
            return new GenericApiDataResponse<>(false,"Some error occurred.",null);
        }
    }

    private String getStatus(BookIssueRecord bookIssueRecord) {
        if(BookReturnStatus.RETURNED.equals(bookIssueRecord.getBookReturnStatus())) {
            return RETURNED;
        }
        if(LocalDate.now().isAfter(bookIssueRecord.getDueDate())) {
            return DUE_DATE_PASSED;
        }
        return DUE_DATE_NOT_PASSED;
    }
}
