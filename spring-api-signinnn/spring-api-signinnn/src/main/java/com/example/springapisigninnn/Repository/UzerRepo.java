package com.example.springapisigninnn.Repository;

import com.example.springapisigninnn.Model.Uzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UzerRepo extends JpaRepository<Uzer,String> {

    List<Uzer> findByUsernameAndPassword(String username , String password);


}
