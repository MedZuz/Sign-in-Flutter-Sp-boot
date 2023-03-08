package com.example.Apiformation.repositorys;

import com.example.Apiformation.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {


    UserDetails findByUsername(String username);
}
