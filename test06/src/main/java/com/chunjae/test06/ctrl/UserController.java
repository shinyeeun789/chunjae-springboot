package com.chunjae.test06.ctrl;

import com.chunjae.test06.biz.UserService;
import com.chunjae.test06.entity.Euser;
import com.chunjae.test06.exception.NoSuchDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@CrossOrigin("http://localhost:8085")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/userList")
    @ResponseBody
    public List<Euser> getUserList() throws Exception {
        return userService.getUserList();
    }

    @GetMapping("/userInfo")
    public String getUser(@RequestParam String name, Model model) throws Exception {
        Euser user = userService.getUser(name);
        model.addAttribute("user", user);
        return "user/userInfo";
    }

    @GetMapping("/login")
    public String loginForm(Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return "user/login";
        return "redirect:/";
    }

    @GetMapping("/join")
    public String joinForm(Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof AnonymousAuthenticationToken) {
            return "user/join";
        }
        return "redirect:/";
    }

    @PostMapping("/joinPro")
    public String joinPro(Euser euser, Model model) throws Exception {
        int cnt = userService.userJoin(euser);
        if(cnt == 0) {
            throw new NoSuchDataException("No Insert Process Data");
        }
        return "redirect:/login";
    }

    //중복 id 검증(Ajax)
    @PostMapping("/idCheck")
    @ResponseBody
    public boolean idCheckAjax(@RequestBody Euser test) throws Exception {
        logger.info("**************** name :"+test.getName());
        boolean result = false;
        Euser user = userService.getByName(test.getName());
        if(user!=null){
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    //중복 이메일 검증(Ajax)
    @PostMapping("/emailCheck")
    @ResponseBody
    public boolean emailCheckAjax(@RequestParam("email") String email) throws Exception {
        Euser user = userService.getByEmail(email);
        if(user!=null){
            return false;
        } else {
            return true;
        }
    }

    @GetMapping("/mypage")
    public String mypage(@RequestParam long id, Model model) throws Exception {
        Euser user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/user/mypage";
    }

    @GetMapping("/userEdit")
    public String userEditForm(@RequestParam long id, Model model) throws Exception {
        Euser user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/user/userEdit";
    }

    @PostMapping("/userEdit")
    public String userEdit(Euser user, RedirectAttributes rttr) throws Exception {
        Euser beforeUser = userService.getUser(user.getName());
        if(user.getPassword() == null || user.getPassword().equals("")) {
            user.setPassword(beforeUser.getPassword());
        }

        int result = userService.updateUser(user);
        if(result > 0) {
            rttr.addFlashAttribute("msg", "개인정보를 변경하였습니다.");
        } else {
            rttr.addFlashAttribute("msg", "개인정보 변경에 실패했습니다. 잠시 후 다시 시도해주세요");
        }
        return "redirect:mypage?id="+beforeUser.getId();
    }

}
