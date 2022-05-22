package com.example.librarydemo.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="photo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo implements Serializable{
    private static final long serialVersionUID = 2842598520185366295L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Lob
    private byte[] photo;

    @JsonIgnore
    @OneToMany(mappedBy = "photoId")
    private List<Book> photos;

    @JsonIgnore
    @OneToMany(mappedBy = "photoId")
    private List<EBook> ebookphotos;
}


