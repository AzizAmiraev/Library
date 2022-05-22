package com.example.librarydemo.DTO;

import java.sql.Date;


public interface TakenBooks {
    long getId();
    long getBookId();
    String getBookName();
    String getBookAuthor();
    Date getStartDate();
    String getLibrarianName();

}
