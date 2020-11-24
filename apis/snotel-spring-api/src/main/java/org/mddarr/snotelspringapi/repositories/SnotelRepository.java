package org.mddarr.snotelspringapi.repositories;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SnotelRepository {
    private static final String TABLE = "Snotel";

    private final AmazonDynamoDB amazonDynamoDB;
    public SnotelRepository(AmazonDynamoDB db){
        this.amazonDynamoDB = db;
    }

    public List<SnotelDataEntity> getSnotelLocationBetweenDates(String locationID, String sdate, String edate) {

        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":locationID", new AttributeValue().withS(locationID));
        eav.put(":sdate", new AttributeValue().withS(sdate));
        eav.put(":edate", new AttributeValue().withS(edate));

        DynamoDBQueryExpression<SnotelDataEntity> queryExpression = new DynamoDBQueryExpression<SnotelDataEntity>()
                .withKeyConditionExpression("LocationID = :locationID and SnotelDate BETWEEN :sdate and :edate").withExpressionAttributeValues(eav);
        List<SnotelDataEntity> snotelData = mapper.query(SnotelDataEntity.class, queryExpression);
        return snotelData;
    }

    @DynamoDBTable(tableName=TABLE)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SnotelDataEntity {
        @DynamoDBHashKey(attributeName="LocationID")
        private String location;
        @DynamoDBRangeKey(attributeName = "SnotelDate")
        private String snoteldate;
        @DynamoDBAttribute(attributeName = "SnowCurrent")
        private double snowCurrent;
        @DynamoDBAttribute(attributeName = "SnowMedian")
        private double snowMedian;
        @DynamoDBAttribute(attributeName = "SnowPctMedian")
        private double snowPctMedian;
        @DynamoDBAttribute(attributeName = "WaterCurrent")
        private double waterCurrent;
        @DynamoDBAttribute(attributeName = "WaterCurrentAverage")
        private double waterMedian;
        @DynamoDBAttribute(attributeName = "WaterPctAverage")
        private double waterPctMedian;
    }
}
