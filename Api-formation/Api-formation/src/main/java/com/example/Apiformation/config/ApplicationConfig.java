package com.example.Apiformation.config;


import com.example.Apiformation.repositorys.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

        @Autowired
        UserRepo userRepo ;

        @Autowired
    UserDetailsService userDetailsService ;

//          @Bean
//        public UserDetailsService userDetailsService(){
//            return username -> (UserDetails) userRepo.findByUsername(username);
//        }


        @Bean
        public AuthenticationProvider authenticationProvider(){

            DaoAuthenticationProvider authProvide = new DaoAuthenticationProvider();
            authProvide.setUserDetailsService(userDetailsService);
            authProvide.setPasswordEncoder(passwordEncoder());
            return authProvide ;

        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
            return authenticationConfiguration.getAuthenticationManager();
        }


        @Bean
        PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
        




}
