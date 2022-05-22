package com.example.librarydemo.repository;

import com.example.librarydemo.models.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Query(value="SELECT * from book where in_library = 1 and deleted = 0", nativeQuery = true)
    List<Book> getInLibraryBooks();

    @Query(value="SELECT * FROM `book` WHERE lower(`name`) = lower(?) and deleted = 0", nativeQuery = true)
    Book getBook(String name);

    @Query(value="SELECT COUNT(id) FROM `book` where deleted = 0", nativeQuery = true)
    Integer getBookQuantity();

    @Modifying
    @Query(value="UPDATE `book` SET `deleted` = 1 , `photo_id` = null where `id` = ?", nativeQuery = true)
    void deleteBookById(long id);

    @Override
    @Query(value="SELECT * from book where deleted = 0", nativeQuery = true)
    Iterable<Book> findAll();

    @Override
    @Query(value="SELECT * from book where deleted = 0 and id = ?", nativeQuery = true)
    Optional<Book> findById(Long aLong);

    @Query(value="SELECT * FROM `book` WHERE `deleted` = 1", nativeQuery = true)
    List<Book> getDeletedBooks();

    @Query(value="SELECT if(COUNT(*)>0, true, false ) FROM `book` WHERE `in_library` = 0 and `id` = ?", nativeQuery = true)
    int checkIfBookWasTaken(long bookId);
}
