package com.stone.stone.services.Impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.stone.stone.models.AdminData;
import com.stone.stone.models.Game;
import com.stone.stone.models.TransactionDetails;
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


    private static final String KEY="rzp_test_5DtK3Jfd3RKP0C";
    private static final String KEY_SECRET="0PCZmssEeMsC4QBVMLipB5ba";
    private static final String CURRENCY="INR";


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
            usertmp.getCart().remove(game);
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
    @Override
    public AdminData getAdminData() {
        AdminData data=new AdminData();
        
        data.setTotalUsers(this.userRepository.count());
        data.setTotalGames(this.gameRepository.count());
        data.setDiscountedGames(this.gameRepository.findRandomGamesWithDiscount().size());

        return data;
    }
    @Override
    public void removeFromCart(Long userId, Long gameId) throws Exception {
        User usertmp=this.userRepository.findById(userId).get();
        if(usertmp!=null)
        {
            Game game=this.gameRepository.findById(gameId).get();
            if(game==null)
            {
                throw new Exception("No such game exists!!!");
            }
            usertmp.getCart().remove(game);
            this.userRepository.save(usertmp);
        }
    }
    @Override
    public boolean isGameOwned(Long userId,Long gameId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return false; 
        }
        return user.getGamesOwned().stream().anyMatch(game->game.getGid().equals(gameId));
    }
    @Override
    public boolean isGameInCart(Long userId,Long gameId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return false; 
        }
        return user.getCart().stream().anyMatch(game -> game.getGid().equals(gameId));
    }
    @Override
    public TransactionDetails createTransaction(Double amount) {
        // amount
        // currency
        // key
        // secret_key

        JSONObject jsonObject=new JSONObject();

        System.out.println(amount);
        amount*=100;
        int value = (amount).intValue();
        System.out.println(value);
        jsonObject.put("amount", value);
        jsonObject.put("currency", CURRENCY);

        try {
            RazorpayClient razorpayClient=new RazorpayClient(KEY, KEY_SECRET);
            
            Order order= razorpayClient.orders.create(jsonObject);


            return prepareTransactionDetails(order);

            // System.out.println(order);

            


        } catch (Exception e) {
            // System.out.println(amount*100);
            System.out.println(e.getMessage());
        }

        return null;

    }


    @Override
    public TransactionDetails prepareTransactionDetails(Order order) {
        String orderId=order.get("id");
        String currency=order.get("currency");
        Integer amount=order.get("amount");

        TransactionDetails transactionDetails=new TransactionDetails(orderId,currency,amount,KEY);

        return transactionDetails;
        
    }

    
    
}
