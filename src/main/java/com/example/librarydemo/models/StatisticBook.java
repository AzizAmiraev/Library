package com.example.librarydemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="statistic_book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int takenQuantity;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book bookId;
}
