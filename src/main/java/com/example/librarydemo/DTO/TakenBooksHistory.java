package com.example.librarydemo.DTO;

import java.sql.Date;

public interface TakenBooksHistory {

    long getId();
    long getBookId();
    String getBookName();
    String getBookAuthor();
    Date getStartDate();
    Date getEndDate();
    String getLibrarianName();
}
