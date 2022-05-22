package com.example.librarydemo.enums;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    STUDENT,
    LIBRARIAN,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
