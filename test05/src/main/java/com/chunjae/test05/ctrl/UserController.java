package com.chunjae.test05.ctrl;

import com.chunjae.test05.biz.UserService;
import com.chunjae.test05.entity.Euser;
import com.chunjae.test05.exception.NoSuchDataException;
import com.chunjae.test05.util.EmailSocket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// GET/POST/DELETE/PUT을 1개 이상씩 작성
// GET    : a 태그로 전송할 때
// POST   : insert할 때
// DELETE : 삭제할 때
// PUT    : 업데이트할 때
@Controller
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list.do")
    public String userList(Model model) throws Exception {
        List<Euser> userList = userService.getUserList();
        if(userList.isEmpty()) {
            throw new NoSuchDataException("No Such List");
        }
        model.addAttribute("userList", userList);
        return "user/userList";
    }

    @GetMapping("detail.do")
    public String getUser(@RequestParam String name, Model model) throws Exception {
        Euser user = userService.getUser(name);
        if(user == null) {
            throw new NoSuchDataException("No Such Data");
        }
        model.addAttribute("user", user);
        return "user/detail";
    }

    // 로그인 폼 로딩
    @GetMapping("login.do")
    public String login(HttpSession session) throws Exception {
        String sname = (String) session.getAttribute("sname");
        if(sname != null) {
            return "redirect:/";
        } else {
            return "user/login";
        }
    }

    // 이메일 로그인
    @PostMapping("loginByEmail.do")
    public String loginByEmail() throws Exception {
        return "user/loginByEmail";
    }

    // 아이디 로그인
    @PostMapping("loginByName.do")
    public String loginByName() throws Exception {
        return "user/loginByName";
    }

    // 회원 탈퇴 API
    @DeleteMapping("getWithdraw.do")
    @ResponseBody
    public int getWithdraw(@RequestParam String name, RedirectAttributes rttr) throws Exception {
        int cnt = userService.getWithdraw(name);
        if(cnt == 0) {
            throw new NoSuchDataException("No Delete Process Data");
        }
        return cnt;
    }
    // 회원 탈퇴
//    @GetMapping("getWithdraw.do")
//    public String getWithdraw(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
//        HttpSession session = request.getSession();
//        String name = (String) session.getAttribute("sname");
//        userService.getWithdraw(name);
//        rttr.addFlashAttribute("msg", "탈퇴가 성공적으로 처리되었습니다.");
//        return "redirect:/";
//    }
    
    // 계정 활성화
    @GetMapping("getActivate.do")
    public String getActivate(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
        String name = request.getParameter("name");
        userService.getActivate(name);
        rttr.addFlashAttribute("msg", name+" 계정이 휴면해제 되었습니다.");
        return "redirect:/";
    }
    
    // 휴면 처리
    @GetMapping("getDormant.do")
    public String getDormant(@RequestParam String name, RedirectAttributes rttr) throws Exception {
        userService.getDormant(name);
        rttr.addFlashAttribute("msg",  name + " 계정이 휴면처리되었습니다.");
        return "redirect:/";
    }

    // 아이디 찾기
    @GetMapping("findById.do")
    public String findByIdForm(Model model) throws Exception {
        return "user/findById";
    }

    @PostMapping("findById.do")
    public String findById(HttpServletRequest request, Model model) throws Exception {
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        Euser user = userService.findById(email, tel);
        model.addAttribute("user", user);
        return "redirect:/user/findById";
    }

    // 비밀번호 찾기
    @GetMapping("findByPw.do")
    public String findByPwForm(Model model) throws Exception {
        return "user/findByPw";
    }

    @PostMapping("findByPw.do")
    public String findByPw(Euser euser, Model model) throws Exception {
        Euser user = userService.findByPw(euser.getEmail(), euser.getTel(), euser.getName());
        model.addAttribute("user", user);
        return "redirect:/user/findByPw";
    }

    public void sendMail(Euser euser) {
        EmailSocket dm = new EmailSocket();
        dm.sendMail(euser);
    }

    // 회원가입
    @GetMapping("join.do")
    public String joinForm() throws Exception {
        return "user/join";
    }

    @PostMapping("join.do")
    public String join(Euser euser, RedirectAttributes rttr) throws Exception {
        userService.join(euser);
        return "redirect:/user/login";
    }
    
    // 회원정보 수정
    @GetMapping("userEdit.do")
    public String userEditForm(HttpSession session, Model model) throws Exception {
        String name = (String) session.getAttribute("sname");
        Euser user = userService.getUser(name);
        model.addAttribute("user", user);

        return "user/edit";
    }

    // 회원정보 수정 API
    @PutMapping("userEdit.do")
    @ResponseBody
    public String userEdit(Euser euser, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
        HttpSession session = request.getSession();
        euser.setName((String) session.getAttribute("sname"));
        userService.userEdit(euser);
        return "redirect:/user/mypage";
    }

//    @PostMapping("userEdit.do")
//    public String userEditForm(Euser euser, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
//        HttpSession session = request.getSession();
//        euser.setName((String) session.getAttribute("sname"));
//        userService.userEdit(euser);
//        return "redirect:/user/mypage";
//    }

    // 회원등급 변경 API
    @PutMapping("userLevEdit.do")
    @ResponseBody
    public String userLevEdit(Euser euser, RedirectAttributes rttr) throws Exception {
        userService.userLevEdit(euser);
        rttr.addFlashAttribute("msg", "회원 등급이 변경되었습니다.");
        return "성공!";
    }

    // 회원등급 변경 (관리자)
//    @PostMapping("userLevEdit.do")
//    public String userLevEdit(Euser euser, RedirectAttributes rttr) throws Exception {
//        userService.userLevEdit(euser);
//        return "성공적으로 처리됨";
//    }


}
