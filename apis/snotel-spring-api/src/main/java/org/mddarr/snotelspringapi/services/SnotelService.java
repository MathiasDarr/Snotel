package org.mddarr.snotelspringapi.services;

import org.mddarr.snotelspringapi.dto.Location;
import org.mddarr.snotelspringapi.dto.SnotelData;
import org.mddarr.snotelspringapi.repositories.LocationRepository;
import org.mddarr.snotelspringapi.repositories.SnotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SnotelService {

    private final LocationRepository locationRepository;
    private final SnotelRepository snotelRepository;

    public SnotelService(LocationRepository locationRepository, SnotelRepository snotelRepository){
        this.locationRepository = locationRepository;
        this.snotelRepository = snotelRepository;

    }
    
    public List<Location> getLocations(){
        return locationRepository.fetchALlLocations().stream().map(SnotelService::mapLocation).collect(Collectors.toList());
    }

    private static Location mapLocation(LocationRepository.LocationEntity entity) {
        return new Location(entity.getLocationID(), entity.getElevation());
    }

    private static SnotelData mapSnotelData(SnotelRepository.SnotelDataEntity entity) {
        return new SnotelData(entity.getLocation(), entity.getSnoteldate(), entity.getSnowCurrent(), entity.getSnowMedian(), entity.getSnowPctMedian(), entity.getWaterCurrent(), entity.getWaterMedian(), entity.getWaterPctMedian());
    }
}
