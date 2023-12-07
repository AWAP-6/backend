package org.example.Model;

import org.example.Model.Entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelRepo extends JpaRepository<Parcel, Long> {
}
