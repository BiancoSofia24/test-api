package com.app.testproject.services;

import com.app.testproject.entity.User;
import com.app.testproject.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServices {
    @Autowired
    UserRepository userRepo;

    public Iterable<User> findAll() {
        return this.userRepo.findAll();
    }

    public void save(User user) {
        this.userRepo.save(user);
    }
}
