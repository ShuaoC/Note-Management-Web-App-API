package com.shuao.NoteManagementAPI;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.function.Consumer;

@RestController
@CrossOrigin
public class NoteController {

    private final DynamoDbTable<User> userTable;
    private final DynamoDbTable<Note> noteTable;
    private final DynamoDbClient dynamoClient;
    private AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    private ScanRequest scanRequest = new ScanRequest()
            .withTableName("User_ID_password");
    private ScanRequest NoteScanRequest = new ScanRequest()
            .withTableName("notes");
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";

    NoteController(DynamoDbTable<User> table, DynamoDbTable<Note> noteTable, DynamoDbClient dynamoClient) {
        this.userTable = table;
        this.noteTable = noteTable;
        this.dynamoClient = dynamoClient;
    }

    @PostMapping("/users")
    public User createUser(){
        User user = new User(UUID.randomUUID().toString(),"" + System.currentTimeMillis());
        userTable.putItem(user);
        return user;
    }

    @GetMapping("/users")
    public List fetchUser(){
        ArrayList<User> list = new ArrayList<User>();
        ScanResult result = client.scan(scanRequest);

//        ArrayList<User> list = new ArrayList<User>();
//        PageIterable<User> result = userTable.scan();

        return result.getItems();
    }

    @GetMapping("/users/{id}/qr")
    public void generateQR(@PathVariable("id") String id) throws Exception {
        QRcodeGenerator.generateQRCodeImage(id, 350, 350, QR_CODE_IMAGE_PATH);
    }

//    @GetMapping("/users/{id}/qr")
//    public ResponseEntity<byte[]> generateQRCode(@PathVariable("id") String id) throws Exception {
//        return ResponseEntity.status(HttpStatus.OK).body(QRcodeGenerator.getQRCodeImage(id, 350, 350));
//    }

    @PostMapping("/users/{id}/notes")
    public Note createNote(@PathVariable String id){
        Note note = new Note(UUID.randomUUID().toString(), id, "" + System.currentTimeMillis(),"Note title","This is the body of my note");
        noteTable.putItem(note);
        return note;
    }

    @GetMapping("/users/{id}/notes")
    public List fetchNote(){
        ArrayList<Note> list = new ArrayList<Note>();
        ScanResult result = client.scan(NoteScanRequest);

        return result.getItems();
    }

    @DeleteMapping("/users/{id}/notes/{noteId}")
    public Note deleteNote(@PathVariable String id, @PathVariable String noteId){
        Note note = noteTable.getItem(Key.builder().partitionValue(id).sortValue(noteId).build());
        noteTable.deleteItem(note);

        return note;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testing(){
        return "Testing";
    }

}
