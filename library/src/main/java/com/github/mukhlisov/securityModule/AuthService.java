package com.github.mukhlisov.securityModule;


import com.github.mukhlisov.User;
import com.github.mukhlisov.UserService;
import com.github.mukhlisov.auth.JwtRequest;
import com.github.mukhlisov.auth.JwtResponse;
import com.github.mukhlisov.securityModule.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtResponse login(JwtRequest jwtRequest) throws UsernameNotFoundException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        JwtResponse jwtResponse = new JwtResponse();
        User user = userService.findByPhoneNumber(jwtRequest.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("User with phone: %s not found".formatted(jwtRequest.getUsername())));
        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getPhoneNumber());

        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(user.getId(), user.getPhoneNumber()));
        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getPhoneNumber()));

        return jwtResponse;
    }
}
