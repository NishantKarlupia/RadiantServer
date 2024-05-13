package com.stone.stone.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.stone.models.Game;
import com.stone.stone.models.User;
import com.stone.stone.repository.GameRepository;
import com.stone.stone.repository.UserRepository;
import com.stone.stone.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public User addUser(User user) throws Exception {
        User usertmp=this.userRepository.findByUsername(user.getUsername());
        if(usertmp!=null)
        {
            throw new Exception("User already exists!!!");
        }
        user.setRole("NORMAL");

        usertmp=this.userRepository.save(user);

        return usertmp;
    }
    @Override
    public User addUserWithCode(User user, String code) throws Exception {

        User usertmp=this.userRepository.findByUsername(user.getUsername());
        if(usertmp!=null)
        {
            throw new Exception("User already exists!!!");
        }


        if(code!=null){
            User userwithcode=this.userRepository.findByUsername(code);
            if(userwithcode!=null)
            {
                userwithcode.setXPoints(userwithcode.getXPoints()+30L);
                this.userRepository.save(userwithcode);
            }
        }
        user.setRole("NORMAL");

        usertmp=this.userRepository.save(user);

        return usertmp;
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @SuppressWarnings("null")
    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    @SuppressWarnings("null")
    @Override
    public void addToCart(Long userId,Long gameId) throws Exception {
        
        User usertmp=this.userRepository.findById(userId).get();
        if(usertmp!=null)
        {
            Game game=this.gameRepository.findById(gameId).get();
            if(game==null)
            {
                throw new Exception("No such game exists!!!");
            }
            usertmp.getCart().add(game);
            this.userRepository.save(usertmp);
        }
    }

    @SuppressWarnings("null")
    @Override
    public void purchaseGame(Long userId, Long gameId) throws Exception {
        User usertmp=this.userRepository.findById(userId).get();
        if(usertmp!=null)
        {
            Game game=this.gameRepository.findById(gameId).get();
            if(game==null)
            {
                throw new Exception("No such game exists!!!");
            }
            game.setTotalDownloads(game.getTotalDownloads()+1);
            this.gameRepository.save(game);
            usertmp.getGamesOwned().add(game);
            this.userRepository.save(usertmp);
        }
    }
    
    @Override
    public boolean isUserExistsWithUsername(String username) {
        User user=this.userRepository.findByUsername(username);
        System.out.println("username: "+username);
        if(user!=null)return true;
        return false;
    }
    @Override
    public boolean isUserExistsWithEmail(String email) {
        User user=this.userRepository.findByEmail(email);
        if(user!=null)return true;
        return false;
    }

    
    
}
