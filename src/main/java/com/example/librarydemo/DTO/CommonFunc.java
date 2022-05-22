package com.example.librarydemo.DTO;

import com.example.librarydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.text.ParseException;

public class CommonFunc {
    UserRepository userRepository;

    public static Date getCurrentDate() throws ParseException {
        long millis=System.currentTimeMillis();
        Date date=new Date(millis);

        return date;
    }

    public static String getCurrentUsersUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }




}
