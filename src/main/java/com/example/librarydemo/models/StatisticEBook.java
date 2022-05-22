package com.example.librarydemo.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="statistic_e_book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticEBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int downloadedQuantity;
    private int viewedQuantity;

    @ManyToOne
    @JoinColumn(name="e_book_id")
    private EBook eBookId;
}
