package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        return userRepository.getAllUsers();
    }

    public String createUser(User user) throws ExecutionException, InterruptedException {
        return userRepository.createUser(user);
    }

    public User getUser(String documentId) throws ExecutionException, InterruptedException {
        return userRepository.getUser(documentId);
    }

    public String updateUser(User user) {
        return userRepository.updateUser(user);
    }

    public String deleteUser(String documentId) {
        return userRepository.deleteUser(documentId);
    }
}
