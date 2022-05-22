package com.example.librarydemo.controllers;

import com.example.librarydemo.DTO.*;
import com.example.librarydemo.models.EBook;
import com.example.librarydemo.models.User;
import com.example.librarydemo.repository.UserRepository;
import com.example.librarydemo.services.EBookService;
import com.example.librarydemo.services.TakenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private TakenService takenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EBookService eBookService;

    @GetMapping("/studentInfo") //✔
    public ResponseEntity<User> getStudentInfo(){
        return ResponseEntity.ok(userRepository.getUserByUserName(CommonFunc.getCurrentUsersUserName()));
    }

    @GetMapping("/takenBooks")  //✔
    public ResponseEntity<List<TakenBooks>> getTakenBooks(){

        return ResponseEntity.ok(takenService.getTakenList(userRepository.getUserByUserName(CommonFunc.getCurrentUsersUserName()).getId()));
    }

    @GetMapping("/takenBooksHistory")  //✔
    public ResponseEntity<List<TakenBooksHistory>> getTakenBooksHistory(){

        return ResponseEntity.ok(takenService.getTakenBooksHistory(userRepository.getUserByUserName(CommonFunc.getCurrentUsersUserName()).getId()));
    }

    @GetMapping("/topBooks") //✔
    public ResponseEntity<List<StatisticBookCLassDTO>> getTopBooks(){
        return ResponseEntity.ok(takenService.getBookStatistic());
    }

    @GetMapping("/topEBooks") //
    public ResponseEntity<List<StatisticEBookDTO>> getTopEBooks(){
        return ResponseEntity.ok(takenService.getEBookStatistic());
    }


    @GetMapping("/eBooks") //✔
    public ResponseEntity<List<EBooksDTO> > getEBooks(){
        return ResponseEntity.ok(eBookService.getEBooks());
    }

    @GetMapping("/eBooks/limit/{limit}") //✔
    public  ResponseEntity<List<EBooksDTO>> getEBookLimit(@PathVariable("limit") int limit){
        return ResponseEntity.ok(eBookService.getEBookLimit(limit));
    }

    @GetMapping("/eBook/{bookId}") //✔
    public ResponseEntity<EBook> getEBookById(@PathVariable("bookId") long id){
        return ResponseEntity.ok(eBookService.getEBookById(id));
    }

    @GetMapping("/eBook/{bookId}/download")//✔
    public ResponseEntity makeDownloadStatistic(@PathVariable("bookId") long bookId){
        eBookService.makeDownloadStatistic(bookId);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/eBook/{bookId}/view") //✔
    public ResponseEntity makeViewStatistic(@PathVariable("bookId") long bookId){
        eBookService.makeViewedStatistic(bookId);
        return  ResponseEntity.ok(HttpStatus.OK);
    }




}
