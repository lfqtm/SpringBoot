package com.stud.web.controller;

import com.stud.web.pojo.Person;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WebController {

    @GetMapping("/ha")
    public String ha() {
        return "哈哈";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser() {
        return "get-张三";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String saveUser() {
        return "post-张三";
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String putUser() {
        return "PUT-张三";
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public String deleteUser() {
        return "DELETE-张三";
    }

    @GetMapping("/cars/{path}")
    public Map<String, Object> carsSell(@MatrixVariable("low") Integer low,
                                        @MatrixVariable("brand") List<String> brand,
                                        @PathVariable String path) {
        Map<String, Object> res = new HashMap<>();
        res.put("low",low);
        res.put("brand",brand);
        res.put("path",path);
        return res;
    }

    @GetMapping("/boss/{bossId}/{empId}")
    public Map<String, Object> getBossAndEmp(
            @MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge,
            @MatrixVariable(value = "age", pathVar = "empId") Integer empAge) {
        Map<String, Object> res = new HashMap<>();
        res.put("bossAge", bossAge);
        res.put("empAge", empAge);
        return res;
    }

    @ResponseBody
    @GetMapping("/person")
    public Person getPerson() {
        return new Person(18, new Date(), "张三");
    }


}
