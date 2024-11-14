package com.example.project1.controller;

import com.example.project1.dto.UserDto;
import com.example.project1.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController
{
    private final TaskService taskService;
    @GetMapping
    public String GetSignUp()
    {
        return "Login/signup";
    }

    @PostMapping
    public String PostSignUp(@Validated UserDto dto, @RequestParam String passwordCheck, Model model, RedirectAttributes redirectAttributes)
    {
        String msg = taskService.SignUp(dto, passwordCheck);

        if (msg.equals("1"))
        {
            model.addAttribute("msg", "중복된 아이디 입니다.");
            return "Login/signup";
        }
        else if (msg.equals("2"))
        {
            model.addAttribute("msg2", "비밀번호를 확인 해주세요.");
            return "Login/signup";
        }
        else
        {
            redirectAttributes.addFlashAttribute("msg", "회원가입이 완료 되었습니다.");
            return "redirect:/login";
        }
    }
}
