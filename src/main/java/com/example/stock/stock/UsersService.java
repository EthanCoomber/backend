package com.example.stock.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository userRepository;

    @Autowired
    public UsersService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addNewUser(Users newUser) {
        Optional<Users> UserOptional = userRepository.findUserByUsername(newUser.getUsername());
        if(UserOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        userRepository.save(newUser);
    }

    public boolean userExist(Users newUser) {
        Optional<Users> UserOptional = userRepository.findUserByUsername(newUser.getUsername());
        if(UserOptional.isPresent()){
            return true;
        } else {
            return false;
        }
    }

    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Transactional
    public void updateTicker(Long userId, String ticker, String action) {
        Users currUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "user with id " + userId + "does not exist"
                ));

        if(action.equals("add")){
            currUser.addTicker(ticker);
        } else {
            currUser.deleteTicker(ticker);
        }
    }
}
