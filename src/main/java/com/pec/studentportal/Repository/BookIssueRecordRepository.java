package com.pec.studentportal.Repository;

import com.pec.studentportal.Entity.BookIssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookIssueRecordRepository extends JpaRepository<BookIssueRecord, Integer> {

    List<BookIssueRecord> findByStudentId(Integer studentId);

}
