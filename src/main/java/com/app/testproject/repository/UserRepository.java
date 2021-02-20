package com.app.testproject.repository;

import com.app.testproject.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    
    public Iterable<User> findByRole(String role);
}
    

