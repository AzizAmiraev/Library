package com.example.librarydemo.controllers;


import com.example.librarydemo.DTO.CommonFunc;
import com.example.librarydemo.DTO.StudentDTO;
import com.example.librarydemo.exceptions.CustomException;
import com.example.librarydemo.models.User;
import com.example.librarydemo.repository.UserRepository;
import com.example.librarydemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;



    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/adminInfo") //✔
    public ResponseEntity<User> getStudentInfo(){
        return ResponseEntity.ok(userRepository.getUserByUserName(CommonFunc.getCurrentUsersUserName()));
    }


    //Выводит список всех пользователей программы вне зависимости от родей
    @GetMapping("/users") //✔
    public List<User> listOfUsers(){
        return userService.UserList();
    }

    @GetMapping("/deletedUsers")//✔
    public List<User> getDeletedUsers(){
            return userService.deletedUserList();
        }

    //Выводит информацию об одном пользователе
    @GetMapping("/user/{id}") //✔
    public User getUser(@PathVariable("id") long id){
        return userService.oneUser(id);
    }

    @GetMapping("/students") //✔
    public List<User> getStudents(){
        return userService.getStudents();
    }

    @GetMapping("/librarians") //✔
    public List<User> getLibrarians(){
        return userService.getLibrarians();
    }

    @GetMapping("/admins") //✔
    public List<User> getAdmins(){
        return userService.getAdmins();
    }

    //Удаление пользователя
    @Transactional
    @DeleteMapping("/delete/{id}")//✔
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) throws CustomException {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
        }catch(CustomException ce){
            return  new ResponseEntity<String>(ce.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Создание нового пользователя
    @Transactional
    @PostMapping //✔
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/addStudent")//✔
    public ResponseEntity addStudent(@RequestBody StudentDTO studentDTO){
        boolean success = userService.createStudent(studentDTO);

        if(success){
            return ResponseEntity.ok(HttpStatus.OK);
        }else{
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addAdmin")//✔
    public ResponseEntity addAdmin(@RequestBody StudentDTO studentDTO){
        boolean success = userService.createAdmin(studentDTO);

        if(success){
            return ResponseEntity.ok(HttpStatus.OK);
        }else{
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addLibrarian")//✔
    public ResponseEntity addLibrarian(@RequestBody StudentDTO studentDTO){
        boolean success = userService.createLibrarian(studentDTO);

        if(success){
            return ResponseEntity.ok(HttpStatus.OK);
        }else{
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }



















    //    //Добавить одного нового пользователя
//    @PostMapping("/add-student")
//    public ResponseEntity<User> addStudent(@RequestBody StudentDTO studentDTO){
//        return new ResponseEntity<User>(userService.createStudent(studentDTO), HttpStatus.CREATED);
//    }
    //    @GetMapping("/student/{id}")
//    public StudentDTO getStudent(@PathVariable("id") int id){
//        return userService.allInformationAboutOneStudent(id);
//    }
//
//
//
//    @GetMapping("/lib/{id}")
//    public LibrarianAdminDTO getLibr(@PathVariable("id") int id){
//        return userService.allInformationAboutOneLibrarian(id);
//    }



















}
