package com.example.librarydemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    long id;
    String firstname;
    String lastname;
    String groupName;
    String phoneNumber;
    String address;
    String email;
    String password;
    boolean enabled;
    String status;
    String role;
}
