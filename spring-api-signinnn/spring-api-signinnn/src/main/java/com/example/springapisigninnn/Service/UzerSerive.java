package com.example.springapisigninnn.Service;


import com.example.springapisigninnn.Model.Uzer;
import com.example.springapisigninnn.Repository.UzerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UzerSerive {

    @Autowired
    UzerRepo uzerRepo ;
    public ResponseEntity<?> getallusers() {

        List<Uzer> res = uzerRepo.findAll();
        if (res.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("there is no book available for now");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<?> login(Uzer uzer) {

        List<Uzer> res = uzerRepo.findByUsernameAndPassword(uzer.getUsername() ,uzer.getPassword());

        if (res.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no data");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }



}
