package com.example.librarydemo.DTO;

import java.sql.Date;


public interface TakenBooksForLibrarian {
    long getId();
    long getBookId();
    String getBookName();
    String getBookAuthor();
    Date getStartDate();
    long getStudentId();
    String getStudentName();
    String getLibrarianName();

}
