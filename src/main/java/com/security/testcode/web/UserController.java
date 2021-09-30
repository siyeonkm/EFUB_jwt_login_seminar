package com.security.testcode.web;

import com.security.testcode.config.MyUserDetail;
import com.security.testcode.service.UserService;
import com.security.testcode.web.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signUp")
    public String signUpForm() {
        return "signup";
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute UserRequestDto dto) {
        String encoded = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encoded);
        service.joinUser(dto);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String userAccess(Model model, Authentication authentication) {
        //Authentication 객체를 통해 유저 정보를 가져올 수 있다.
        MyUserDetail userDetail = (MyUserDetail)authentication.getPrincipal();  //userDetail 객체를 가져옴
        model.addAttribute("info", userDetail.getUsername());      //유저 이메일
        return "user_access";
    }

    @GetMapping("/userOnly")
    public String authenticated(Model model, Authentication authentication) {
        MyUserDetail userDetail = (MyUserDetail)authentication.getPrincipal();
        String response =  "현재 로그인된 유저: " + userDetail.getUsername();
        model.addAttribute("info", response);
        return "authenticated";
    }
}
