package org.mddarr.snotelspringapi.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mddarr.snotelspringapi.dto.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationRepository {
    private static final String TABLE = "BasinLocations";

    private static final Logger log = LoggerFactory.getLogger(LocationRepository.class);

    private final AmazonDynamoDB amazonDynamoDB;


    public LocationRepository(AmazonDynamoDB db){
        this.amazonDynamoDB = db;
    }

//    public List<LocationRepository.LocationEntity> fetchLocations(){

    public List<LocationEntity> fetchALlLocations() {
        DynamoDBMapperConfig mapperConfig = new DynamoDBMapperConfig.Builder()
                .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(TABLE)).build();
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB, mapperConfig);
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List <LocationEntity> orders = mapper.scan(LocationEntity.class, scanExpression);
        return orders;
    }

    @DynamoDBTable(tableName=TABLE)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocationEntity {
        @DynamoDBHashKey(attributeName="LocationID")
        private String locationID;
        @DynamoDBRangeKey(attributeName = "Elevation")
        private int elevation;
    }
}