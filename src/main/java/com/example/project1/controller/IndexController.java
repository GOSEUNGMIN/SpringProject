package com.example.project1.controller;

import com.example.project1.dto.UserDto;
import com.example.project1.service.TaskService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {

    private final TaskService taskService; // service에서 처리하여 받아오기 위해 선언
    private final HttpSession session;

    @GetMapping
    public String index(Model model)
    {
        if (session != null)
        {
            model.addAttribute("login", "login");
        }
        return "index";
    }
    @GetMapping("login")
    public String getlogin()
    {
        return "Login/login";
    }

    @PostMapping("login")
    public String postlogin(@Validated UserDto dto, Model model)
    {
        Boolean A = taskService.login(dto);
        if (A == true)
        {
            return "redirect:/";
        }
        else
        {
            return "Login/login";
        }
    }

    @PostMapping("logout")
    public String logOut()
    {
        session.invalidate(); // 세션 정보를 삭제, 초기화 로그아웃 기능
        return "redirect:/";
    }
}
