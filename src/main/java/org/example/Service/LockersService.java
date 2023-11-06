package org.example.Service;

import org.example.Model.Entity.Locker;
import org.example.Model.LockerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LockersService {
    @Autowired
    private LockerRepo lockerRepo;
    public ArrayList<Locker> getAllLockers(){
        return new ArrayList<>(lockerRepo.findAll());
    }

}
