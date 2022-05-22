package com.example.librarydemo.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="e_book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String author;
    private String description;
    private int releaseYear;
    private String type;
    @Lob
    private byte[] data;

    public EBook(String name, String author, String description, int releaseYear, String type, byte[] data, Category category, Photo photoId) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.releaseYear = releaseYear;
        this.type = type;
        this.data = data;
        this.category = category;
        this.photoId = photoId;
    }


    @ManyToOne
    @JoinColumn
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "eBookId")
    private List<StatisticEBook> statisticEBooks;


    @ManyToOne
    @JoinColumn(name="photo_id")
    private Photo photoId;
}
