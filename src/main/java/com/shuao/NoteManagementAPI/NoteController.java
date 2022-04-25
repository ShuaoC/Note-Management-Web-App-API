package com.shuao.NoteManagementAPI;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class NoteController {

    private final DynamoDbTable<User> userTable;
    private final DynamoDbTable<Note> noteTable;
    private final DynamoDbClient dynamoClient;
    private AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    private ScanRequest scanRequest = new ScanRequest()
            .withTableName("User_ID_password");

    NoteController(DynamoDbTable<User> table, DynamoDbTable<Note> noteTable, DynamoDbClient dynamoClient) {
        this.userTable = table;
        this.noteTable = noteTable;
        this.dynamoClient = dynamoClient;
    }

    @PostMapping("/users")
    public String createUser(){
        User user = new User(UUID.randomUUID().toString(),"" + System.currentTimeMillis());
        userTable.putItem(user);
        return "\"userId\": \"" + user.getUserID() + "\"\n"
                + "\"createdDate\": \"" + user.getDateCreated() + "\"";
    }

    @GetMapping("/users")
    public List fetchUser(){
        ArrayList<User> list = new ArrayList<User>();
        ScanResult result = client.scan(scanRequest);

//        ArrayList<User> list = new ArrayList<User>();
//        PageIterable<User> result = userTable.scan();

        return result.getItems();
    }

    //@GetMapping("/users/{id}/qr")

    @PostMapping("/users/{id}/notes")
    public String createNote(@PathVariable String id){
        Note note = new Note(UUID.randomUUID().toString(), id, "" + System.currentTimeMillis(),"Note title","This is the body of my note");
        noteTable.putItem(note);
        return "\"noteId\": \"" + note.getNoteID() + "\"\n"
                + "\"title\": \"" + note.getTitle() + "\"\n"
                + "\"content\": \"" + note.getContent() + "\"\n"
                + "\"createDate\": \"" + note.getDateCreated() + "\"";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testing(){
        return "Testing";
    }

}
