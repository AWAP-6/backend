package org.example.Service;

import org.example.Model.Entity.Location;
import org.example.Model.Entity.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LocationService {
    @Autowired
    private LocationRepo locationRepo;

    public ArrayList<Location> getAllLocations(){
        return new ArrayList<>(locationRepo.findAll());
    }
}
