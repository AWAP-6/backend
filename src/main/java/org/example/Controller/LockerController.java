package org.example.Controller;

import org.example.Model.Entity.Locker;
import org.example.Model.LockerRepo;
import org.example.Service.EmailSenderService;
import org.example.Service.LockersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lockers")
@CrossOrigin(origins = {"https://touchscreen.onrender.com", "https://driverapp-c1h1.onrender.com", "https://consumerapp.onrender.com"})
public class LockerController {
    @Autowired
    private LockersService lockersService;
    @Autowired
    private LockerRepo lockerRepo;
    @GetMapping
    public String getAllLockers(){
        return lockersService.getAllLockers().toString();
    }
    @GetMapping("/checkOpenCode")
    public String getOpenCode(@RequestParam Integer openCode){
        if (lockersService.openLocker(openCode)) {
            return "true";
        }else {
            return "false";
        }
    }
    @GetMapping("/lockerStatus")
    public ResponseEntity<?> getStatus(@RequestParam Integer openCode) {
        Optional<Locker> lockerOpt = lockerRepo.findByOpenCode(openCode);
        if (lockerOpt.isPresent()) {
            Locker locker = lockerOpt.get();
            return ResponseEntity.ok(locker.getStatus());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Locker with the given open code not found");
        }
    }
    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestParam("openCode") Integer openCode, @RequestParam("status") String status) {
        boolean updateResult = lockersService.updateLockerStatus(openCode, status);
        if (updateResult) {
            return ResponseEntity.ok("Locker status updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update locker status.");
        }
    }
    @GetMapping("/location/{locationId}/findEmpty")
    public ResponseEntity<List<Locker>> getEmpty(@PathVariable int locationId, @RequestParam boolean isEmpty){
        List<Locker> lockers = lockersService.getEmpty(locationId, isEmpty);
        return ResponseEntity.ok(lockers);
    }

    @GetMapping("/location/{locationId}/getStatus")
    public ResponseEntity<?> getLocationStatus(@PathVariable int locationId) {
        try {
            Object locationStatus = lockersService.getLockerStatusByLocationId(locationId);
            return new ResponseEntity<>(locationStatus, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get location status " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{lockerId}/updateStatus")
    public ResponseEntity<?> updateStatus(@PathVariable int lockerId, @RequestParam("status") String newStatus) {
        boolean updateResult = lockersService.updateLockerStatus(lockerId, newStatus);
        if (updateResult) {
            return ResponseEntity.ok("Locker status updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update locker status.");
        }
    }
}
