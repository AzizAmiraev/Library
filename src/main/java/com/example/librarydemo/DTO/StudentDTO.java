package com.example.librarydemo.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    long id;
    String firstname;
    String lastname;
    String phoneNumber;
    String group;
    String address;
    String login;
    String password;
    String role;
}
