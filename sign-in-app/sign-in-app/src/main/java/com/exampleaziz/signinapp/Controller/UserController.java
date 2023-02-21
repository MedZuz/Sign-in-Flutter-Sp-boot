package com.exampleaziz.signinapp.Controller;
import com.exampleaziz.signinapp.ConfigSec.TokenGenerator;
import com.exampleaziz.signinapp.Service.userService;
import com.exampleaziz.signinapp.Model.User;
import com.exampleaziz.signinapp.dto.authresponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class UserController {

    @Autowired
    private userService service ;

    @Autowired
    private TokenGenerator tokenGenerator ;


    @PostMapping("/login")
    public ResponseEntity<authresponseDTO> Login(@RequestBody User user) {
        return (ResponseEntity<authresponseDTO>) service.login(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody User user) {
        return service.register(user);
    }
}