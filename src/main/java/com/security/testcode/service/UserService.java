package com.security.testcode.service;

import com.security.testcode.config.MyUserDetail;
import com.security.testcode.domain.UserRepository;
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
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    @Transactional
    public void joinUser(UserRequestDto user) {
        User newUser = User.builder()
                .email(user.getEmail())
                .password(user.getPassword()).build();
        repository.save(newUser);
    }

    @Override
    public MyUserDetail loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        return new MyUserDetail(user);
    }
}
