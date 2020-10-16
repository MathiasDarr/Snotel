package org.mddarr.snotelspringapi.repositories;


import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SnotelRepository {
    private static final String TABLE = "Snotel";

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
