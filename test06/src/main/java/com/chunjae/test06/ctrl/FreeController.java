package com.chunjae.test06.ctrl;

import com.chunjae.test06.biz.FreeService;
import com.chunjae.test06.biz.UserService;
import com.chunjae.test06.entity.Euser;
import com.chunjae.test06.entity.Free;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/free/*")
public class FreeController {
    @Autowired
    private FreeService freeService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String freeList(Model model) throws Exception {
        List<Free> freeList = freeService.freeList();
        model.addAttribute("freeList", freeList);
        return "/free/freeList";
    }

    @GetMapping("insert")
    public String freeInsertForm(Model model) throws Exception {
        return "/free/freeInsert";
    }

    @PostMapping("insert")
    public String freeInsert(Free free, HttpServletRequest request, Principal principal, RedirectAttributes rttr) throws Exception {
        Euser user = userService.getUserById(Long.valueOf(principal.getName()));
        free.setName(user.getName());
        int result = freeService.insertFree(free);
        if(result > 0) {
            rttr.addFlashAttribute("msg", "게시판에 글이 등록되었습니다.");
            return "redirect:list";
        } else {
            rttr.addFlashAttribute("msg", "글 등록에 실패했습니다. 잠시 후 다시 시도해주세요.");
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
    }

    @GetMapping("detail")
    public String freeDetail(@RequestParam int fno, @RequestParam Long id, HttpServletRequest request, HttpSession session, Model model) throws Exception {
        Cookie[] cookieFromRequest = request.getCookies();
        String cookieValue = null;
        for(int i=0; i< cookieFromRequest.length; i++) {
            cookieValue = cookieFromRequest[0].getValue();
        }
        // 쿠키 세션 입력
        if(session.getAttribute(fno + ":freeCookie") == null) {
            session.setAttribute(fno + ":freeCookie", fno + ":" + cookieValue);
        } else {
            session.setAttribute(fno + ":freeCookie ex", session.getAttribute(fno + ":freeCookie"));
            if(!session.getAttribute(fno + ":freeCookie").equals(fno + ":" + cookieValue)) {
                session.setAttribute(fno + ":freeCookie", fno + ":" + cookieValue);
            }
        }
        // 쿠키와 세션이 없는 경우 조회수 카운트
        if(!session.getAttribute(fno + ":freeCookie").equals(session.getAttribute(fno + ":freeCookie ex"))) {
            freeService.updateViews(fno);
        }

        Euser user = userService.getUserById(id);
        Free free = freeService.getFree(fno);
        model.addAttribute("detail", free);
        model.addAttribute("isAuthor", user.getName().equals(free.getName()));
        return "/free/freeDetail";
    }

    @GetMapping("edit")
    public String freeEditForm(@RequestParam int fno, Model model) throws Exception {
        Free free = freeService.getFree(fno);
        model.addAttribute("detail", free);
        return "/free/freeEdit";
    }

    @PostMapping("edit")
    public String freeEdit(Free free, RedirectAttributes rttr) throws Exception {
        int result = freeService.updateFree(free);
        if(result > 0) {
            rttr.addFlashAttribute("msg", "게시글을 수정하였습니다.");
        } else {
            rttr.addFlashAttribute("msg", "게시글 수정에 실패했습니다. 잠시 후 다시 시도해주세요.");
        }
        return "redirect:detail?fno=" + free.getFno();
    }

    @GetMapping("delete")
    public String freeDelete(@RequestParam int fno, RedirectAttributes rttr) throws Exception {
        int result = freeService.deleteFree(fno);
        if (result > 0) {
            rttr.addFlashAttribute("msg", "게시글을 삭제하였습니다.");
        } else {
            rttr.addFlashAttribute("msg", "게시글 삭제에 실패했습니다. 잠시 후 다시 시도해주세요");
        }
        return "redirect:list";
    }

}
