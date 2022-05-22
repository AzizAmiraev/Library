package com.example.librarydemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TakenDTO {
    private Date startDate;
    private String studentInfo;
    private String bookInfo;

}
