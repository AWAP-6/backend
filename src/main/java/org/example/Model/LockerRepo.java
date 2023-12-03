package org.example.Model;

import org.example.Model.Entity.Locker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LockerRepo extends JpaRepository<Locker, Integer> {
    Optional<Locker> findByOpenCode(Integer openCode);
    List<Locker> findByIsEmptyTrueAndLocationId(int locationId);
    List<Locker> findByIsEmptyFalseAndLocationId(int locationId);

    Optional<Locker> findStatusByOpenCode(Integer openCode);
}
