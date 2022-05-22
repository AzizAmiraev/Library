package com.example.librarydemo.DTO;

import org.springframework.web.multipart.MultipartFile;

public interface StatisticEBookDTO {
    long getId();
    long getBookId();
    String getName();
    String getAuthor();
    int getReleaseYear();
    MultipartFile getPhoto();
    int getDownloadedQuantity();
    int getViewedQuantity();
}
