package fmi.plovdiv.TravelAgency.service;

import fmi.plovdiv.TravelAgency.dto.CreateLocation;
import fmi.plovdiv.TravelAgency.model.Location;
import fmi.plovdiv.TravelAgency.model.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository repository;

    public Location createLocation(CreateLocation location){
       if(location.getNumber().isBlank() || location.getCity().isBlank() || location.getStreet().isBlank() ||
               location.getCountry().isBlank()){
           return null;
       }

       Location newLocation = new Location();
        newLocation.setCity(location.getCity());
        newLocation.setNumber(location.getNumber());
        newLocation.setStreet(location.getStreet());
        newLocation.setCountry(location.getCountry());
        newLocation.setImageUrl(location.getImageUrl());

        return repository.save(newLocation);
    }

    public List<Location> getLocations(){
        return repository.findAll();
    }

    public Location getLocationById(Long id){
        return repository.findById(id).orElse(null);
    }

    public boolean deleteLocation(Long id){
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public Location updateLocation(Location location){
        if(repository.existsById(location.getID())) {
            if(location.getNumber().isBlank() || location.getCity().isBlank() || location.getStreet().isBlank() ||
                    location.getCountry().isBlank()){
                return null;
            }
            return repository.save(location);
        }else{
            return null;
        }
    }
}
