package org.example.Controller;

import org.example.Model.Entity.Parcel;
import org.example.Service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parcels")
@CrossOrigin(origins = "http://localhost:3000")
public class ParcelController {

    @Autowired
    private ParcelService parcelService;

    @PostMapping("/add")
    public ResponseEntity<String> addParcel(@RequestBody Parcel parcel) {
        parcelService.addParcel(parcel);
        return ResponseEntity.ok("Parcel added successfully");
    }
}
