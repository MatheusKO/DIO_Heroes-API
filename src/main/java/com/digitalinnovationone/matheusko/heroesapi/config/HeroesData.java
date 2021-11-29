package com.digitalinnovationone.matheusko.heroesapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import static com.digitalinnovationone.matheusko.heroesapi.constants.HeroesConstant.ENDPOINT_DYNAMO;
import static com.digitalinnovationone.matheusko.heroesapi.constants.HeroesConstant.REGION_DYNAMO;

public class HeroesData {

    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Heroes");

        Item hero = new Item()
                .withPrimaryKey("id", "1")
                .withString("name", "Captain America")
                .withString("universe", "Marvel")
                .withNumber("movies", 7);

        Item hero1 = new Item()
                .withPrimaryKey("id", "2")
                .withString("name", "Batman")
                .withString("universe", "DC")
                .withNumber("movies", 14);

        Item hero2 = new Item()
                .withPrimaryKey("id", "3")
                .withString("name", "Doctor Strange")
                .withString("universe", "Marvel")
                .withNumber("movies", 5);

        try {
            System.out.println("Inserting heroes into Heroes...");
            PutItemOutcome outcome = table.putItem(hero);
            PutItemOutcome outcome1 = table.putItem(hero1);
            PutItemOutcome outcome2 = table.putItem(hero2);
            System.out.println("Data inserted successfully");
        } catch(Exception e) {
            System.err.println("Error inserting data: " + e.getMessage());
        }

    }
}
