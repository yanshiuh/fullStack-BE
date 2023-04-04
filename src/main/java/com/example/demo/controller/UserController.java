package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000/", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping
    public List<User> getUsers() throws ExecutionException, InterruptedException {
        return userService.getAllUsers();
    }

    @PostMapping
    public String createUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable(value = "id") String documentId) throws ExecutionException, InterruptedException {
        return userService.getUser(documentId);
    }

    @PutMapping
    public String updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(value = "id") String documentId) {
        return userService.deleteUser(documentId);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndPoints() {
        return ResponseEntity.ok("Rest API is working");
    }
}
