package org.example.Controller;

import org.example.Model.Entity.Parcel;
import org.example.Service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/parcels")
@CrossOrigin(origins = {"https://touchscreen.onrender.com", "https://driverapp-c1h1.onrender.com", "https://consumerapp.onrender.com"})public class ParcelController {

    @Autowired
    private ParcelService parcelService;

    @PostMapping("/add")
    public ResponseEntity<String> addParcel(@RequestBody Parcel parcel) {
        parcelService.addParcel(parcel);
        return ResponseEntity.ok("Parcel added successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<ArrayList<Parcel>> getParcelsBySenderEmail(@RequestParam String sender_email) {
        ArrayList<Parcel> parcels = (ArrayList<Parcel>) parcelService.getParcelsBySenderEmail(sender_email);
        return ResponseEntity.ok(parcels);
    }
}