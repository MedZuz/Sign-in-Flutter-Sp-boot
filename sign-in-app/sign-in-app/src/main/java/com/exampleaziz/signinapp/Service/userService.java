package com.exampleaziz.signinapp.Service;

import com.exampleaziz.signinapp.ConfigSec.TokenGenerator;
import com.exampleaziz.signinapp.Repository.RoleRepository;
import com.exampleaziz.signinapp.Repository.UserRepository;
import com.exampleaziz.signinapp.ConfigSec.WebSecurityConfiguration;
import com.exampleaziz.signinapp.Model.Role;
import com.exampleaziz.signinapp.Model.User;
import com.exampleaziz.signinapp.dto.authresponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class userService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder ;
    @Autowired
    AuthenticationManager authenticationManager ;

    @Autowired
    private TokenGenerator tokenGenerator ;

    @Autowired
    WebSecurityConfiguration webSecurityConfiguration ;


    public userService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, WebSecurityConfiguration webSecurityConfiguration) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.webSecurityConfiguration = webSecurityConfiguration;
    }




    public ResponseEntity<?> login(User user){
        if (userRepository.findByUsername(user.getUsername()).isPresent()){

        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenGenerator.generaToken(authentication);
        return new ResponseEntity<>(new authresponseDTO(token), HttpStatus.OK);
    }else {
            return new ResponseEntity<>(" No uz with this credential " , HttpStatus.UNAUTHORIZED);

        }}


    public ResponseEntity<String> register(User user) {

        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return new ResponseEntity<>("Username is taken",HttpStatus.BAD_REQUEST);

        }
        User usr = new User();

        usr.setUsername(user.getUsername());
        usr.setPassword(passwordEncoder.encode(user.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        usr.setRoles(Collections.singletonList(roles));

        userRepository.save(usr);

        return new ResponseEntity<>("success register" ,HttpStatus.OK);

    }


}
