package com.shop.controller;

import com.shop.entity.Sample;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ex")
public class ExamController {

    @GetMapping("/sample1")
    public String getSample1(Model model) {
        List<String> nameList = Arrays.asList("kim", "lee", "park", "choi");
        model.addAttribute("names", nameList);

        Sample sample = new Sample(1000L, "졸려", 123);
        model.addAttribute("sample", sample);

        return "exam/sample1";
    }

    @GetMapping("/sample2")
    public String getSample2(Model model) {
        return "exam/sample2";
    }

    @GetMapping("/sample3")
    public String getSample3(Model model) {
        return "exam/sample3";
    }

}
