package com.stone.stone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stone.stone.models.User;



public interface UserRepository extends JpaRepository<User,Long> {
    public User  findByUsername(String username);

    public User findByEmail(String email);
    
}
