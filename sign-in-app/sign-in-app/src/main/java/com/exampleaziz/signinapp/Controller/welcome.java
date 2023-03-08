package com.exampleaziz.signinapp.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/welcome")
public class welcome {

    @GetMapping
    public ResponseEntity<String> welcome(){
        return  ResponseEntity.ok("hello");
    }

}
