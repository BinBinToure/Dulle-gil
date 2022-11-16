package com.introduce.seoulgil.mainController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {
    @GetMapping("/courseList")
    public String course(){
        return "course/courseList";
    }
}
