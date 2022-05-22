package com.example.librarydemo.DTO;


import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String email;
    private String password;
}
