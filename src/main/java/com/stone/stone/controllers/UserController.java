package com.stone.stone.controllers;

import org.springframework.web.bind.annotation.RestController;

// import com.stone.stone.models.Game;
import com.stone.stone.models.User;
import com.stone.stone.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/createUser")
    public User addUser(@RequestBody User user) throws Exception{
        // System.out.println();
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("NORMAL");
        return this.userService.addUser(user);
    }
    @PostMapping("/createUser/{code}")
    public User addUserWithCode(@RequestBody User user,@PathVariable("code") String code) throws Exception{
        // System.out.println();
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("NORMAL");
        return this.userService.addUserWithCode(user,code);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return this.userService.getUser(username);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);
    }

    @PutMapping("/addtocart/{userId}/{gameId}")
    public void addToCart(@PathVariable("userId") Long userId,@PathVariable("gameId") Long gameId) throws Exception{
        this.userService.addToCart(userId, gameId);
    }

    @PutMapping("/purchasegame/{userId}/{gameId}")
    public void purchaseGame(@PathVariable("userId") Long userId,@PathVariable("gameId") Long gameId) throws Exception{
        this.userService.purchaseGame(userId, gameId);
    }

    @GetMapping("/exists/username/{username}")
    public boolean userExistsWithUsername(@PathVariable("username") String username){

        return this.userService.isUserExistsWithUsername(username);
    }
    @GetMapping("/exists/email/{email}")
    public boolean userExistsWithEmail(@PathVariable("email") String email){

        return this.userService.isUserExistsWithEmail(email);
    }
    
}
