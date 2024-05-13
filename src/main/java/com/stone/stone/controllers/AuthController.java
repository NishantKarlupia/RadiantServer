package com.stone.stone.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stone.stone.config.CustomUser;
import com.stone.stone.config.CustomUserDetailsService;
import com.stone.stone.config.JwtUtil;
import com.stone.stone.models.JWT.JWTRequest;
import com.stone.stone.models.JWT.JWTResponse;

@RestController
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private CustomUserDetailsService userDetailsServiceImpl;

    @Autowired JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/generate-token")
    public ResponseEntity<?>generateToken(@RequestBody JWTRequest jwtRequest) throws Exception{
        try {
            System.out.println(jwtRequest.getUsername());
            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        } catch (Exception e) {
            throw new Exception("username or password is incorrect");
        }
        UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        String token=this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    private void authenticate(String username,String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER DISABLED");
        }
        catch(BadCredentialsException e){
            throw new Exception("Bad Credentials");
        }
    }

    @GetMapping("/current-user")
    public CustomUser getCurrentUser(Principal principal){
    
        System.out.println("current: "+principal.getName());
        return (CustomUser) this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
    }

    
    
}
