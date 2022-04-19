package com.shuao.NoteManagementAPI;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin
public class NoteController {

    @PostMapping("/users")
    public String createUser(){
        String userID = UUID.randomUUID().toString();
        String createdDate = "" + System.currentTimeMillis();
        return "UserID: "+ userID + " and the password is: " + createdDate;
    }

}
