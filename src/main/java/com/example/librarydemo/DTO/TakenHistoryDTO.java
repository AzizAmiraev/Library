package com.example.librarydemo.DTO;

public interface TakenHistoryDTO {
    long getId();
    long getBookId();
    long getLibrarianId();
    long getStudentId();
    String getStartDate();
    String getEndDate();
    String getStudentName();
    String getBookName();
    String getBookAuthor();
    String getLibrarianName();
}
