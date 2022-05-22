package com.example.librarydemo.repository;

import com.example.librarydemo.DTO.StatisticBookDTO;
import com.example.librarydemo.models.StatisticBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatisticBookRepository extends CrudRepository<StatisticBook, Long> {

    @Query(value="SELECT s.id as id, s.taken_quantity as takenQuantity, s.book_id as bookId, b.name as name, b.author as author, b.release_year as releaseYear, p.name as photo FROM `statistic_book` as s JOIN `book` as b on s.book_id = b.id join `photo` as p on p.id = b.photo_id order by `taken_quantity` desc limit 20", nativeQuery = true)
    List<StatisticBookDTO> getTopBooks();

    @Query(value="SELECT * FROM `statistic_book` WHERE `book_id` = ?", nativeQuery = true)
    StatisticBook getStatisticBookByBookId(long id);
}
