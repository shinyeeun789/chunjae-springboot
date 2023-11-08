package com.shop.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Rootcontroller {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("msg", "메시지입니다.");
        return "hello";
    }

    @GetMapping("/hello2")
    public String hello2(String name, int age, Model model) {
        Human human = new Human(name, age);
        model.addAttribute("human", human);
        return "hello2";
    }

    @GetMapping("/hello3")
    public String hello2(Human human, Model model) {
        model.addAttribute("human", human);
        return "hello2";
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Human {
    private String name;
    private int age;
}