package com.shuao.NoteManagementAPI;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/bk")
public class NoteController {

    private final DynamoDbTable<User> userTable;
    private final DynamoDbClient dynamoClient;

    NoteController(DynamoDbTable<User> table, DynamoDbClient dynamoClient) {
        this.userTable = table;
        this.dynamoClient = dynamoClient;
    }

    @PostMapping("/users")
    public String createUser(){
        User user = new User(UUID.randomUUID().toString(),"" + System.currentTimeMillis());
        userTable.putItem(user);
        return "Done";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testing(){
        return "Testing";
    }

}
