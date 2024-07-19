package com.tacoloy.signup.controller;


import com.tacoloy.signup.model.LoginRequest;
import com.tacoloy.signup.model.User;
import com.tacoloy.signup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class usercontroller {


    @Autowired
    private UserRepository userRepository;


    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }


    @GetMapping("/signup")
    public List<User> getAllUsers() {

        return userRepository.findAll();

    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {

        if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty() || user.getConfirmPass().isEmpty()) {
            return ResponseEntity.badRequest().body("All fields must be filled");
        }


        if (userRepository.findByusername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
        if (userRepository.findByusername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        } else {
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User existingUser = userRepository.findByusername(username);

        if (existingUser != null && existingUser.getPassword().equals(password)) {
            return ResponseEntity.ok("Login Success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}



