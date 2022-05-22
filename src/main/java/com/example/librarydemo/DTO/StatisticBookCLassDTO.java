package com.example.librarydemo.DTO;

import com.example.librarydemo.models.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticBookCLassDTO implements Serializable {
    long id;
    int takenQuantity;
    long bookId;
    String name;
    String author;
    int releaseYear;
    Photo photo;
}
