package com.example.librarydemo.repository;

import com.example.librarydemo.DTO.EBooksDTO;
import com.example.librarydemo.models.EBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EBookRepository extends CrudRepository<EBook, Long> {
    @Query(value="SELECT COUNT(id) FROM `e_book` where `data` is not null", nativeQuery = true)
    Integer getEBookQuantity();

    @Query(value="SELECT b.id, b.author, b.description, b.name, b.release_year, c.category, p.photo  FROM `e_book` as b JOIN `category` as c on c.id = b.category_id join `photo` as p on p.id = b.photo_id where `data` is not null", nativeQuery = true)
    List<EBooksDTO> getEBooks();

    @Query(value="SELECT b.id, b.author, b.description, b.name, b.release_year, c.category, p.photo  FROM `e_book` as b JOIN `category` as c on c.id = b.category_id join `photo` as p on p.id = b.photo_id where `data` is not null limit ?, 10", nativeQuery = true)
    List<EBooksDTO> getEBooksLimit(int limit);

    @Query(value="SELECT * FROM `e_book` WHERE `id` = ? and `data` is not null", nativeQuery = true)
    EBook getEBook(long id);

    @Query(value="UPDATE `e_book` SET `data`= null ,`photo_id`= null WHERE `id` = ?", nativeQuery = true)
    void deleteEBook(long id);
}
