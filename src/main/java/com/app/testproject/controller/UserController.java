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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/testApi")
public class UserController {
    @Autowired
    //private UserServices userServices;
    private UserRepository userRepo;

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

    @GetMapping(path="/find/{id}")
	public @ResponseBody Optional<User> getUserById(@PathVariable String id) {
		return userRepo.findById(Integer.valueOf(id));
	}

    @GetMapping(path="/{role}")
    public @ResponseBody Iterable<User> getUsers(@RequestParam String role) {
        return userRepo.findByRole(role);
    }

    @DeleteMapping(path="/delete-user")
    public @ResponseBody String delete(@RequestParam Integer id) {
        Optional<User> user = userRepo.findById(id);
        userRepo.delete(user.get());
        return "User Deleted";
    }

    @PostMapping(path="/update-user/{id}")
    public @ResponseBody String updateUser(@PathVariable String id, @RequestParam String name, @RequestParam String email, @RequestParam String pass) {
        Optional<User> user = userRepo.findById(Integer.valueOf(id));
        User userUpdated = user.get();
        userUpdated.setName(name);
        userUpdated.setEmail(email);
        userUpdated.setPass(pass);
        userRepo.save(userUpdated);
        return "User Updated";
    }
}
