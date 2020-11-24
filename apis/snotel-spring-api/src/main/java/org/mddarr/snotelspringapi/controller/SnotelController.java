package org.mddarr.snotelspringapi.controller;

import org.mddarr.snotelspringapi.dto.Location;
import org.mddarr.snotelspringapi.dto.SnotelData;
import org.mddarr.snotelspringapi.services.SnotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SnotelController {

    @Autowired
    private SnotelService snotelService;

    @RequestMapping("snotel")
    public List<SnotelData> getSnotelLocationBetweenDates(@RequestParam("location") String locationID, @RequestParam("sdate") String sdate, @RequestParam("edate") String edate) {
        List<SnotelData> snotelData = snotelService.getSnotelDataBetweenDates(locationID, sdate, edate);
        return snotelData;
    }

    @RequestMapping("locations")
    public List<Location> getLocations(){
        return snotelService.getLocations();
    }

}
