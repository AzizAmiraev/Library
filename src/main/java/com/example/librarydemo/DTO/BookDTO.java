package com.example.librarydemo.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO implements Serializable {
     long id;
     String name;
     String author;
     String description;
     int releaseYear;
     String category;
     String photoName;
     MultipartFile photo;


}
