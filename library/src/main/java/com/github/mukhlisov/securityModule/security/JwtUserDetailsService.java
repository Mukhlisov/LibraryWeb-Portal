package com.github.mukhlisov.securityModule.security;

import com.github.mukhlisov.User;
import com.github.mukhlisov.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userService.findByPhoneNumber(phone).orElseThrow(()->
            new UsernameNotFoundException("User with phone: %s not found".formatted(phone)));
        return JwtEntityFactory.create(user);
    }
}
