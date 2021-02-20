package com.app.testproject.controller;

import java.util.Date;
import java.util.Optional;

import com.app.testproject.entity.User;
import com.app.testproject.repository.UserRepository;
import com.app.testproject.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/testApi")
public class UserController {
    @Autowired
    //private UserServices userServices;
    private UserRepository userRepo;

    @GetMapping(path="/date")
    public @ResponseBody Date getDate() {
        return new Date();
    }

    @PostMapping(path="/new-user")
    public @ResponseBody User addUser (@RequestParam String name, @RequestParam String email, @RequestParam String pass) {
        User user = new User();
        String userRole2 = "USER";
        user.setName(name);
        user.setEmail(email);
        user.setPass(email);
        user.setRole(userRole2);
        userRepo.save(user);
        return user;
    }

    @GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAll() {
		return userRepo.findAll();
	}

    @GetMapping(path="/find-user")
	public @ResponseBody Optional<User> getUserById(@RequestParam Integer id) {
		return userRepo.findById(id);
	}

    @GetMapping(path="/users")
    public @ResponseBody Iterable<User> getUsers() {
        String role = "USER";
        return userRepo.findByRole(role);
    }

    @GetMapping(path="/admin")
    public @ResponseBody Iterable<User>
    getUserByRole() {
        String role = "ADMIN";
        return userRepo.findByRole(role);
    }

    @DeleteMapping(path="/delete-user")
    public @ResponseBody String delete(@RequestParam Integer id) {
        Optional<User> user = userRepo.findById(id);
        userRepo.delete(user.get());
        return "User Deleted";
    }
}
