package org.example.Model;

import org.example.Model.Entity.Locker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LockerRepo extends JpaRepository<Locker, Integer> {

}
