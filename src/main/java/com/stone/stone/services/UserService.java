package com.stone.stone.services;

import org.springframework.stereotype.Service;

// import com.stone.stone.models.Game;
import com.stone.stone.models.User;

@Service
public interface UserService {
    public User addUser(User user) throws Exception;
    public User addUserWithCode(User user,String code) throws Exception;
    public User getUser(String username);
    public void deleteUser(Long id);
    public void addToCart(Long userId,Long gameId) throws Exception;
    public void purchaseGame(Long userId,Long gameId) throws Exception;
    public boolean isUserExistsWithUsername(String username);
    public boolean isUserExistsWithEmail(String email);
}
