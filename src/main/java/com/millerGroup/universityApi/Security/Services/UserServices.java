package com.millerGroup.universityApi.Security.Services;

import com.millerGroup.universityApi.Model.User;
import com.millerGroup.universityApi.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServices {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;


    public UserServices(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(String username, String email, String password){
        if(userRepository.findByEmail(email) != null){
            throw new RuntimeException("Email already exists");
        }
        if(userRepository.findByUsername(username) != null){
            throw new RuntimeException("Sorry! Username exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }
}
