package org.example.Controller;

import org.example.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
@CrossOrigin(origins = {"https://touchscreen.onrender.com", "https://driverapp-c1h1.onrender.com", "https://consumerapp.onrender.com"})
public class LocationController {
    @Autowired
    private LocationService locationService;
    @GetMapping
    public String getAllLocations(){
        return locationService.getAllLocations().toString();
    }
}
