package com.stud.web.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int age;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String name;
}
