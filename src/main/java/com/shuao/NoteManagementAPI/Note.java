package com.shuao.NoteManagementAPI;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Note {

    private String noteId;
    private String title;
    private String content;
    private String dateCreated;

    public Note(){}

    public Note(String id, String date, String title, String content) {
        this.noteId = id;
        this.dateCreated = date;
        this.title = title;
        this.content = content;
    }

    @DynamoDbPartitionKey
    public String getUserID() {
        return this.noteId;
    }

    public void setUserID(String id) {
        this.noteId = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateC) {
        this.dateCreated = dateC;
    }

}
