package org.example.Service;

import org.example.Model.Entity.Locker;
import org.example.Model.LockerRepo;
import org.example.Utils.LockerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LockersService {
    @Autowired
    private LockerRepo lockerRepo;
    public ArrayList<Locker> getAllLockers(){
        return new ArrayList<>(lockerRepo.findAll());
    }
    @GetMapping
    @Transactional
    public boolean openLocker(Integer openCode){
        Optional<Locker> lockerOpt = lockerRepo.findByOpenCode(openCode);
        if (lockerOpt.isPresent()){
            Locker locker = lockerOpt.get();
            locker.setEmpty(true);
            boolean saved = false;
            int attempts = 0;
            while (!saved && attempts < 5) {
                try {
                    lockerRepo.save(locker);
                    saved = true;
                } catch (DataIntegrityViolationException e) {
                    attempts ++;
                    if (attempts >= 6) {
                        throw e;
                    }
                }
            }
            return saved;
        }
        return false;
    }
    public String lockerStatus(Integer openCode){
        Optional<Locker> lockerOpt = lockerRepo.findByOpenCode(openCode);
        Locker locker = lockerOpt.get();
        return locker.getStatus();
    }
    @Transactional
    public boolean updateLockerStatus(Integer openCode, String status) {
        Optional<Locker> lockerOpt = lockerRepo.findByOpenCode(openCode);
        if (!lockerOpt.isPresent()) {
            return false;
        }
        Locker locker = lockerOpt.get();
        locker.setStatus(status);
        lockerRepo.save(locker);

        locker.setOpenCode(LockerUtil.generateOpenCode());
        lockerRepo.save(locker);
        return true;
    }

    public List<Locker> getEmpty(int locationId, boolean isEmpty) {
        if (isEmpty) {
            return lockerRepo.findByIsEmptyTrueAndLocationId(locationId);
        } else {
            return lockerRepo.findByIsEmptyFalseAndLocationId(locationId);
        }
    }
}
