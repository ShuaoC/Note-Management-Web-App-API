package com.shuao.NoteManagementAPI;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/bk")
public class NoteController {

//    private final DynamoDbTable<User> table;
//    NoteController(DynamoDbTable<User> table) {
//        this.table = table;
//    }


    @Autowired
    private userRepo userRepo;

//    @PostMapping("/users")
//    public String createUser(){
//        User user = new User(UUID.randomUUID().toString(),"" + System.currentTimeMillis());
//        this.table.putItem(user);
//        return "Success";
//    }

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
