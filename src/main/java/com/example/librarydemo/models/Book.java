package com.example.librarydemo.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private long id;
    private String name;
    private String author;
    private boolean inLibrary;
    private String description;
    private int releaseYear;
    private boolean deleted;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Taken> takenBooks;

    @JsonIgnore
    @OneToMany(mappedBy = "bookId")
    private List<StatisticBook> statisticBooks;

    @ManyToOne
    @JoinColumn
    private Category category;

    @ManyToOne
    @JoinColumn(name="photo_id")
    private Photo photoId;
}

