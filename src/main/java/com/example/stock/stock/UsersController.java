package com.example.stock.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {
    private final UsersService usersService;
    private final UsersRepository usersRepository;

    @Autowired
    public UsersController(UsersService userService,
                           UsersRepository usersRepository) {
        this.usersService = userService;
        this.usersRepository = usersRepository;
    }

    @GetMapping(path = "/get")
    public List<Users> getStudents(){
        return usersService.getUsers();
    }

    @PostMapping(path = "/post")
    public void addUser(@RequestBody Users newUser){
        boolean exists = usersService.userExist(newUser);
        if(!exists) {
            usersService.addNewUser(newUser);
        }
    }

    @DeleteMapping(path = "/delete/{username}")
    public void deleteUser(@PathVariable String username){
        Optional<Users> temp = usersService.getUser(username);
        usersRepository.delete(temp.get());
//        usersRepository.deleteById(temp.get().getId());
    }

    @GetMapping(path = "/get/{username}")
    public Optional<Users> getUser(@PathVariable String username){
        return usersService.getUser(username);
    }

    @PostMapping(path = "/{userId}/insert")
    public void insertTicker(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String ticker){
        usersService.updateTicker(userId, ticker, "add");
    }

    @DeleteMapping(path = "/{userId}/delete")
    public void deleteTicker(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String ticker){
        usersService.updateTicker(userId, ticker, "delete");
    }
}
