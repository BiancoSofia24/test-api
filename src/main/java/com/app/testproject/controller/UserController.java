package com.app.testproject.controller;

import java.util.Optional;

import com.app.testproject.entity.User;
import com.app.testproject.repository.UserRepository;
// import com.app.testproject.services.UserServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

//@Controller
@RestController
@RequestMapping(path = "/testApi")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepo;
    // private UserServices userServices;

    @PostMapping(path = "/add")
    public @ResponseBody User addUser(@RequestParam String name, @RequestParam String email, @RequestParam String pass) {
        log.info("Create a new user");
        User user = new User();
        String userRole2 = "USER";
        user.setName(name);
        user.setEmail(email);
        user.setPass(email);
        user.setRole(userRole2);
        userRepo.save(user);
        return user;
    }

    @Operation(summary="Find all the users")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success|OK"),
        @ApiResponse(responseCode = "404", description = "Not Found|Fail")
    })
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAll() {
        log.info("Find all users");
        return userRepo.findAll();
    }

    @Operation(summary="Find one user by its ID")
    @GetMapping(path = "/find/{id}")
    public @ResponseBody Optional<User> getUserById(@Parameter(description="User ID") @PathVariable String id) {
        log.info("Find the user with the ID: " + id);
        return userRepo.findById(Integer.valueOf(id));
    }

    @Operation(summary="Find one user by its role")
    @GetMapping(path = "/{role}")
    public @ResponseBody Iterable<User> getUsers(@Parameter(description="User Role") @RequestParam String role) {
        log.info("Find a user with the Role: " + role);
        return userRepo.findByRole(role);
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody String delete(@RequestParam Integer id) {
        log.info("Delete a user");
        Optional<User> user = userRepo.findById(id);
        userRepo.delete(user.get());
        return "User Deleted";
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody String updateUser(@PathVariable String id, @RequestParam String name, @RequestParam String email, @RequestParam String pass) {
        log.info("Update a user");
        Optional<User> user = userRepo.findById(Integer.valueOf(id));
        User userUpdated = user.get();
        userUpdated.setName(name);
        userUpdated.setEmail(email);
        userUpdated.setPass(pass);
        userRepo.save(userUpdated);
        return "User Updated";
    }
}
