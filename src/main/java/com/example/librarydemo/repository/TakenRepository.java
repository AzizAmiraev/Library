package com.example.librarydemo.repository;

import com.example.librarydemo.DTO.TakenBooks;
import com.example.librarydemo.DTO.TakenBooksForLibrarian;
import com.example.librarydemo.DTO.TakenBooksHistory;
import com.example.librarydemo.DTO.TakenHistoryDTO;
import com.example.librarydemo.models.Taken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TakenRepository extends CrudRepository<Taken, Long> {

    @Query(value = "select t.id as id, t.book_id as bookId, b.name as bookName, b.author as bookAuthor, t.start_date as startDate, concat_ws(' ', u.lastname, u.firstname) as librarianName from taken t join book b on t.book_id = b.id join usr as u on t.librarian_id = u.id where t.student_id = ? and t.end_date IS NULL" , nativeQuery = true)
    List<TakenBooks> getTakenBooks(long student_id);

    @Query(value = "select t.id as id, t.book_id as bookId, b.name as bookName, b.author as bookAuthor, t.start_date as startDate, t.end_date as endDate, concat_ws(' ', u.lastname, u.firstname) as librarianName from taken t join book b on t.book_id = b.id join usr as u on t.librarian_id = u.id where t.student_id = ? and t.end_date IS NOT NULL" , nativeQuery = true)
    List<TakenBooksHistory> getTakenBooksHistory(long student_id);

    @Query(value = "select t.id as id, t.book_id as bookId, t.student_id as studentId, concat_ws(' ', s.lastname, s.firstname) as studentName, concat_ws(' ', l.lastname, l.firstname) as librarianName, b.name as bookName, b.author as bookAuthor, t.start_date as startDate from taken t join book b on t.book_id = b.id join usr as s on t.student_id = s.id join usr as l on t.librarian_id = l.id where t.end_date IS NULL" , nativeQuery = true)
    List<TakenBooksForLibrarian> getTakenBooksForLibrarian();

    @Query(value="SELECT * FROM `taken` WHERE `student_id` = ?1 and `book_id` = ?2 and `end_date` is null", nativeQuery = true)
    Taken getTakenBook(long studentId, long bookId);

    @Query(value = "SELECT t.id as id, t.book_id as bookId, b.name as bookName, b.author as bookAuthor, t.librarian_id as librarianId, concat_ws(' ', l.lastname, l.firstname) as librarianName, t.student_id as studentId, concat_ws(' ', s.lastname, s.firstname) as StudentName, t.start_date as startDate, t.end_date as endDate FROM `taken` as t JOIN `book` as b on t.book_id = b.id JOIN `usr` as s on t.student_id = s.id JOIN `usr` as l on t.librarian_id = l.id WHERE t.end_date is NOT null", nativeQuery = true)
    public List<TakenHistoryDTO> getTakenHistory();


}
