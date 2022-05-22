package com.example.librarydemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name="taken")
@AllArgsConstructor
@NoArgsConstructor
public class Taken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JoinColumn(name="student_id")
    private User studentId;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name="librarian_id")
    private User librarianId;
}
