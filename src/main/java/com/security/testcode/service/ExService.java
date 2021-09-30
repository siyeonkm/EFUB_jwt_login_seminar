package com.security.testcode.service;

import com.security.testcode.config.JwtTokenProvider;
import com.security.testcode.config.MyUserDetail;
import com.security.testcode.config.SecurityConfig;
import com.security.testcode.domain.ExRepository;
import com.security.testcode.domain.User;
import com.security.testcode.web.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ExService implements UserDetailsService {
    private final ExRepository repository;

    @Transactional
    public Long joinUser(UserRequestDto user) {
        User newUser = User.builder()
                        .email(user.getEmail())
                        .password(user.getPassword()).build();
        repository.save(newUser);
        return newUser.getUserNum();
    }

    @Transactional
    public User findUser(UserRequestDto dto) {
        User member = repository.findByEmail(dto.getEmail());
        return member;
    }

    @Override
    public MyUserDetail loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        return new MyUserDetail(user);
    }
}
