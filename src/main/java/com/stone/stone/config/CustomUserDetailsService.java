package com.stone.stone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.stone.stone.models.User;
import com.stone.stone.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("username:"+username);
       User user=userRepository.findByUsername(username);
       if(user==null)
       {
        throw new UsernameNotFoundException("No such user exists");
       }
       return new CustomUser(user);
        
    }
    
}
