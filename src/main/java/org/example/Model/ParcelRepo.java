package org.example.Model;

import org.example.Model.Entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ParcelRepo extends JpaRepository<Parcel, Long> {
        ArrayList<Parcel> findBySenderEmail(String senderEmail);
    }
