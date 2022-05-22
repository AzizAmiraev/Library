package com.example.librarydemo.services;

import com.example.librarydemo.DTO.*;
import com.example.librarydemo.exceptions.CustomException;
import com.example.librarydemo.models.Book;
import com.example.librarydemo.models.StatisticBook;
import com.example.librarydemo.models.Taken;
import com.example.librarydemo.models.User;
import com.example.librarydemo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TakenService {

    @Autowired
    TakenRepository takenRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatisticBookRepository statisticBookRepository;

    @Autowired
    StatisticEBookRepository statisticEBookRepository;

    @Autowired
    PhotoRepository photoRepository;

    public List<TakenBooks> getTakenList(long student_id){
        List<TakenBooks> takenList = (List<TakenBooks>) takenRepository.getTakenBooks(student_id);
        return takenList;
    }

    public List<TakenBooksHistory> getTakenBooksHistory(long student_id){
        List<TakenBooksHistory> booksHistories = (List<TakenBooksHistory>) takenRepository.getTakenBooksHistory(student_id);
        return booksHistories;
    }

    public  List<TakenHistoryDTO> getTakenHistory(){
        return takenRepository.getTakenHistory();
    }

    public List<TakenBooksForLibrarian> getTakenBooksForLibrarian(){
        return takenRepository.getTakenBooksForLibrarian();
    }

    public int giveBook(TakenDTO book) throws ParseException, CustomException {

        long bookId;
        long studentId;

        try {
            studentId = Integer.parseInt(book.getStudentInfo());
        } catch (NumberFormatException nfe) {
            throw new CustomException("Entered not an id");
        }

        try {
            bookId = Integer.parseInt(book.getBookInfo());
        } catch (NumberFormatException nfe) {
            throw new CustomException("Entered not an id");
        }



        if(bookRepository.findById(bookId).get() == null){
            return 404;
        }
        if(userRepository.findById(studentId).get() == null){
            return 404;
        }



        Book b = bookRepository.findById(bookId).get();
        User u = userRepository.findById(studentId).get();

        if(!(b.isInLibrary())){
            return 404;
        }
        b.setInLibrary(false);


        bookRepository.save(b);

        book.setStartDate(CommonFunc.getCurrentDate());

        Taken taken = new Taken();
        taken.setStartDate(book.getStartDate());
        taken.setLibrarianId(userRepository.getUserByUserName(CommonFunc.getCurrentUsersUserName()));
        taken.setStudentId(u);
        taken.setBook(b);
        takenRepository.save(taken);



        if(statisticBookRepository.getStatisticBookByBookId(bookId) == null){
            StatisticBook statisticBook = new StatisticBook();
            statisticBook.setBookId(b);
            statisticBook.setTakenQuantity(1);
            statisticBookRepository.save(statisticBook);

        }else{
            StatisticBook statisticBook = statisticBookRepository.getStatisticBookByBookId(bookId);
            statisticBook.setTakenQuantity(statisticBook.getTakenQuantity() + 1);
            statisticBookRepository.save(statisticBook);
        }



        return 200;
    }

    public int takeBook(TakenDTO book) throws ParseException, CustomException {

        long bookId;
        long studentId;


        try {
            studentId = Integer.parseInt(book.getStudentInfo());
        } catch (NumberFormatException nfe) {
            throw new CustomException("Entered not an id");
        }

        try {
            bookId = Integer.parseInt(book.getBookInfo());
        } catch (NumberFormatException nfe) {
            throw new CustomException("Entered not an id");
        }


        if(!(bookRepository.findById(bookId).isPresent())){
            return 404;
        }
        if(!(userRepository.findById(studentId).isPresent())){
            return 404;
        }



        Book b = bookRepository.findById(bookId).get();

        if((b.isInLibrary())){
            return 404;
        }

        b.setInLibrary(true);


        bookRepository.save(b);


        Taken taken = takenRepository.getTakenBook(studentId, bookId);
        System.out.println("fcvgbhjk");
        System.out.println(CommonFunc.getCurrentDate());
        System.out.println(taken.getStartDate());
        taken.setEndDate(CommonFunc.getCurrentDate());

        takenRepository.save(taken);

        return 200;
    }

    public List<StatisticBookCLassDTO> getBookStatistic(){

        List<StatisticBookDTO> books =  statisticBookRepository.getTopBooks();
        List<StatisticBookCLassDTO> bookDTO = new ArrayList<StatisticBookCLassDTO>();

        for(StatisticBookDTO b : books){
            StatisticBookCLassDTO bDTO = new StatisticBookCLassDTO(b.getId(),b.getTakenQuantity(),b.getBookId(), b.getName(),b.getAuthor(), b.getReleaseYear(), photoRepository.getPhotoByName(b.getPhoto()));
            bookDTO.add(bDTO);
        }

        return bookDTO;
    }

    public List<StatisticEBookDTO> getEBookStatistic(){
        return statisticEBookRepository.getTopBooks();
    }

    public List<StatisticEBookDTO> getViewedEBookStatistic(){
        return statisticEBookRepository.getTopViewedBooks();
    }
}
