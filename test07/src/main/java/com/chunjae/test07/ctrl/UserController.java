package com.chunjae.test07.ctrl;

import com.chunjae.test07.biz.UserService;
import com.chunjae.test07.entity.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String home(Model model) {
        Human human = new Human();
        human.setName("김이름");
        human.setAge(25);
        model.addAttribute("human", human);

        List<Human> hList = new ArrayList<>();
        for(int i=1; i<=4; i++) {
            Human h = new Human();
            h.setName("이름" + i);
            h.setAge(14 * i);
            hList.add(h);
        }
        model.addAttribute("hList", hList);

        model.addAttribute("attrName", "임의필드");
        return "index";
    }

    @GetMapping(value = {"login"})
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/login");
        return modelAndView;
    }

//    @GetMapping("registration")
//    public

}
