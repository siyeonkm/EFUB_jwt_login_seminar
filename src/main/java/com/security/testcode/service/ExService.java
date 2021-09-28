package com.security.testcode.service;

import com.security.testcode.config.MyUserDetail;
import com.security.testcode.domain.ExRepository;
import com.security.testcode.domain.User;
import com.security.testcode.web.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExService implements UserDetailsService {
    private final ExRepository repository;

    @Transactional
    public void joinUser(UserRequestDto user) {
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        User newUser = User.builder().nickname(user.getNickname())
                        .email(user.getEmail())
                        .password(pwEncoder.encode(user.getPassword())).build();
        repository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        return new MyUserDetail(user);
    }
}
