package com.shuao.NoteManagementAPI;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
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
    private AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    private ScanRequest scanRequest = new ScanRequest()
            .withTableName("Reply");

    NoteController(DynamoDbTable<User> table, DynamoDbClient dynamoClient) {
        this.userTable = table;
        this.dynamoClient = dynamoClient;
    }

    @PostMapping("/users")
    public String createUser(){
        User user = new User(UUID.randomUUID().toString(),"" + System.currentTimeMillis());
        userTable.putItem(user);
        return "Added UserID: " + user.getUserID() + "\nDate created: " + user.getDateCreated();
    }

    @GetMapping("/users")
    public String fetchUser(){
        return "";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testing(){
        return "Testing";
    }

}
