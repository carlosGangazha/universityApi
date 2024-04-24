package com.millerGroup.universityApi.Controller;

import com.millerGroup.universityApi.Model.User;
import com.millerGroup.universityApi.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository){
        this.userRepository  = userRepository;
    }

    @PostMapping("/login")
    @CrossOrigin()
    public ResponseEntity<String> Login(@RequestBody User userRequest) {
        String email = userRequest.getEmail();
        String password = userRequest.getPassword();

        User user = userRepository.findByEmail(email);


        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        return ResponseEntity.ok("Login successful");
    }
}
