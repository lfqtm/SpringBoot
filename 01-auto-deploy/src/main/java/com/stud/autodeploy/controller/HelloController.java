package com.stud.autodeploy.controller;

import com.stud.autodeploy.bean.Car;
import com.stud.autodeploy.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class HelloController {

    @Autowired
    private Car car;

    @Autowired
    private User user;

    @GetMapping("/myCar")
    public Car myCar() {
        log.info("car===" + car);
        return car;
    }

    @GetMapping("/myUser")
    public User myUser() {
        return user;
    }
}
