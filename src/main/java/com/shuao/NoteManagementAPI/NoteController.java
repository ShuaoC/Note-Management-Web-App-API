package com.shuao.NoteManagementAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/bk")
public class NoteController {

    @Autowired
    private userRepo userRepo;

    @PostMapping("/users")
    public User createUser(User user){
        user = new User(UUID.randomUUID().toString(),"" + System.currentTimeMillis());
        return userRepo.save(user);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testing(){
        return "Testing";
    }

}
