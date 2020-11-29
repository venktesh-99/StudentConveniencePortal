package com.pec.studentportal.Repository;

import com.pec.studentportal.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    public Book findByBookId(String bookId);

}
