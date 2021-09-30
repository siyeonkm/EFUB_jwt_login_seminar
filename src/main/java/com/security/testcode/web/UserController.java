package com.security.testcode.web;

import com.security.testcode.config.JwtTokenProvider;
import com.security.testcode.config.MyUserDetail;
import com.security.testcode.domain.User;
import com.security.testcode.service.UserService;
import com.security.testcode.web.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signUp")
    public Long signUp(@RequestBody UserRequestDto dto) {
        String encoded = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encoded);
        return service.joinUser(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequestDto dto) {
        User member = service.findUser(dto);
        if(member == null) throw new IllegalArgumentException("가입되지않은 이메일입니다");
        if(!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getEmail(), member.getRole());
    }

    @GetMapping("/")
    public String userAccess() {
        return "권한확인됨";
    }

    @GetMapping("/userOnly")
    public String authenticated() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetail userDetail = (MyUserDetail)principal;
        String response =  "현재 로그인된 유저: " + userDetail.getUsername();
        return response;
    }
}
