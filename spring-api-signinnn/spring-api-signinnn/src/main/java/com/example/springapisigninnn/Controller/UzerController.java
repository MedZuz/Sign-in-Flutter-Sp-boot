package com.example.springapisigninnn.Controller;

import com.example.springapisigninnn.Model.Uzer;
import com.example.springapisigninnn.Service.UzerSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class UzerController {

    @Autowired
    UzerSerive uzerSerive ;

//    @GetMapping("/all")
//    public ResponseEntity<?> getusers(){
//        return uzerSerive.getallusers();
//    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Uzer uzer) {

        return uzerSerive.login(uzer);

    }

}
