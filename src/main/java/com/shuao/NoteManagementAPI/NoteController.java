package com.shuao.NoteManagementAPI;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin
public class NoteController {

    @PostMapping("/users")
    public User createUser(){
        User user = new User(UUID.randomUUID().toString(),"" + System.currentTimeMillis());
        return user;
    }

}
