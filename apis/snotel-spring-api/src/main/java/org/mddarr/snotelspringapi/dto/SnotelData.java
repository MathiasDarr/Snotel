package org.mddarr.snotelspringapi.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SnotelData {
    private String location;
    private String snoteldate;
    private double snowCurrent;
    private double snowMedian;
    private double snowPctMedian;
    private double waterCurrent;
    private double waterMedian;
    private double waterPctMedian;
}
