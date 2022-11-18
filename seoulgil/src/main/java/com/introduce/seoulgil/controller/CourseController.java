package com.introduce.seoulgil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {
    @GetMapping("/courseList")
    public String courseList(){
        return "course/courseList";
    }

    @GetMapping("/course1")
    public String course(){
        return "course/course1";
    }
}
