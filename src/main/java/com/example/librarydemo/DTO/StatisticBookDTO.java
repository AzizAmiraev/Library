package com.example.librarydemo.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public interface StatisticBookDTO  {
    long getId();
    int getTakenQuantity();
    long getBookId();
    String getName();
    String getAuthor();
    int getReleaseYear();
    String getPhoto();
}
