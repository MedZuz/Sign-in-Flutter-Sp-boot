package com.example.Apiformation.config;

import com.example.Apiformation.Model.UserEntity;
import com.example.Apiformation.repositorys.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService  implements org.springframework.security.core.userdetails.UserDetailsService {


        @Autowired
        private UserRepo repo ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity usr = new UserEntity() ;

        var uz = repo.findByUsername(username);

        return uz;
    }
}
