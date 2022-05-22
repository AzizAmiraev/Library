package com.example.librarydemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianAdminDTO {
    long id;
    String firstname;
    String lastname;
    String phoneNumber;
    String login;
    String password;
    String role;




    public String getLastname() {
        return lastname;
    }
}
