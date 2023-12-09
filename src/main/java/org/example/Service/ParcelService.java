package org.example.Service;

import org.example.Model.ParcelRepo;
import org.example.Model.Entity.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParcelService {

    @Autowired
    private ParcelRepo parcelRepo;

    public void addParcel(Parcel parcel) {
        parcelRepo.save(parcel);
    }
}
