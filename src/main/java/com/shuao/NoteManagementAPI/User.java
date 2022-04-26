package com.shuao.NoteManagementAPI;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class User {

    private String userId;
    private String dateCreated;

    public User(){}

    public User(String id, String date) {
        this.userId = id;
        this.dateCreated = date;
    }

    @DynamoDbPartitionKey
    public String getUserID() {
        return this.userId;
    }

    public void setUserID(String id) {
        this.userId = id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateC) {
        this.dateCreated = dateC;
    }

}
