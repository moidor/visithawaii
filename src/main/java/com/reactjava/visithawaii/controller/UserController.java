package com.reactjava.visithawaii.controller;

import com.reactjava.visithawaii.model.User;
import com.reactjava.visithawaii.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> userData = userRepository.findById(id);

        return userData.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User _user = new User(user.getUsername(), user.getPassword());
            List<User> existingUsername = userRepository.findByUsername(user.getUsername());
            if (!existingUsername.contains(_user)) userRepository.save(_user);

            System.out.println("User created. Username: " + _user.getUsername() + ", Password: " + _user.getPassword());
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("L'utilisateur existe déjà en base de données.");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
