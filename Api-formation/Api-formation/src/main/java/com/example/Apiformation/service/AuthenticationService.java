package com.example.Apiformation.service;

import com.example.Apiformation.Model.UserEntity;
import com.example.Apiformation.Model.Role;
import com.example.Apiformation.auth.AuthenticationRequest;
import com.example.Apiformation.auth.AuthenticationResponse;
import com.example.Apiformation.auth.RegisterRequest;
import com.example.Apiformation.config.JwtService;
import com.example.Apiformation.repositorys.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserRepo userRepo ;
    @Autowired
    private JwtService jwtService ;
    @Autowired
    private PasswordEncoder passwordEncoder ;
    @Autowired
    private AuthenticationManager authenticationManager ;
    @Autowired
    private UserDetailsService userDetailsService ;

    public AuthenticationResponse register(RegisterRequest request) {

        UserEntity usr = new UserEntity();

        usr.setUsername(request.getUsername());
        usr.setPassword(passwordEncoder.encode(request.getPassword()));
        usr.setRole(Role.USER);
        userRepo.save(usr);
        var jwtToken = jwtService.generateToken(usr);
        return AuthenticationResponse.builder().token(jwtToken).build() ;

    }

   public AuthenticationResponse authenticate(AuthenticationRequest request) {

       Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
       SecurityContextHolder.getContext().setAuthentication(authentication);

       var jwtToken = jwtService.generateToken(userRepo.findByUsername(request.getUsername()));
       return AuthenticationResponse.builder().token(jwtToken).build() ;

   }



}
