package com.shuao.NoteManagementAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@SpringBootApplication
public class NoteManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteManagementApiApplication.class, args);
	}

	@Bean
	public DynamoDbClient formClient(){
		return DynamoDbClient.builder()
				.credentialsProvider(DefaultCredentialsProvider.builder().build())
				.region(Region.US_EAST_1)
				.build();
	}

	@Bean
	public DynamoDbEnhancedClient formEnhancedClient(DynamoDbClient client){
		return DynamoDbEnhancedClient.builder()
				.dynamoDbClient(client)
				.build();
	}

	@Bean
	public DynamoDbTable<User> formDynamoTable(DynamoDbEnhancedClient enhancedClient){
		return enhancedClient.table("User_ID_password",TableSchema.fromBean(User.class));
	}

}
