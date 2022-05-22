package com.example.librarydemo.DTO;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;

public interface EBooksDTO {
    long getId();
    String getAuthor();
    String getDescription();
    String getName();
    int getRelease_year();
    String getCategory();
    @Lob
    byte[] getPhoto();

}
