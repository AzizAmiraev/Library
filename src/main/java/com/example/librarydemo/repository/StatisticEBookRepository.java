package com.example.librarydemo.repository;

import com.example.librarydemo.DTO.StatisticEBookDTO;
import com.example.librarydemo.models.StatisticEBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatisticEBookRepository extends CrudRepository<StatisticEBook, Long> {
    @Query(value="SELECT s.id as id, s.e_book_id as bookId, b.name as name, b.author as author, b.release_year as releaseYear, p.photo as photo , s.downloaded_quantity as downloadedQuantity, s.viewed_quantity as viewedQuantity FROM `statistic_e_book` as s JOIN `e_book` as b on s.e_book_id = b.id JOIN `photo` as p on p.id = b.photo_id WHERE 1 order by `downloaded_quantity` desc limit 20  ", nativeQuery = true)
    List<StatisticEBookDTO> getTopBooks();

    @Query(value="SELECT s.id as id, s.e_book_id as bookId, b.name as name, b.author as author, b.release_year as releaseYear, p.photo as photo , s.downloaded_quantity as downloadedQuantity, s.viewed_quantity as viewedQuantity FROM `statistic_e_book` as s JOIN `e_book` as b on s.e_book_id = b.id JOIN `photo` as p on p.id = b.photo_id WHERE 1 order by `viewed_quantity` desc limit 20 ", nativeQuery = true)
    List<StatisticEBookDTO> getTopViewedBooks();

    @Query(value="select if(COUNT(*)>0,true,false) from `statistic_e_book` WHERE `e_book_id` = ?", nativeQuery = true)
    int existsInStatistic(long bookId);

}
