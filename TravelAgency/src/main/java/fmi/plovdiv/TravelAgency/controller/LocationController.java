package fmi.plovdiv.TravelAgency.controller;

import fmi.plovdiv.TravelAgency.dto.CreateLocation;
import fmi.plovdiv.TravelAgency.model.Location;
import fmi.plovdiv.TravelAgency.model.LocationRepository;
import fmi.plovdiv.TravelAgency.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService service;

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody CreateLocation location){
        Location newLocation =  service.createLocation(location);

        if(newLocation == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else{
            return ResponseEntity.ok(newLocation);
        }
    }

    @GetMapping
    public ResponseEntity<List<Location>> getLocation(){
        List<Location> listLocation = service.getLocations();

        return ResponseEntity.ok(listLocation);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long locationId){
        Location location = service.getLocationById(locationId);

        if(location == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else{
            return ResponseEntity.ok(location);
        }
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<Boolean> deleteLocation(@PathVariable Long locationId){
        Boolean isDeleted = service.deleteLocation(locationId);

        return ResponseEntity.ok(isDeleted);
    }

    @PutMapping
    public ResponseEntity<Location> updateLocation(@RequestBody Location location){
        Location newLocation = service.updateLocation(location);

        if(newLocation == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else{
            return ResponseEntity.ok(newLocation);
        }

    }
}
