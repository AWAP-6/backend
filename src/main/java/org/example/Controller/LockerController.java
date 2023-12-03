package org.example.Controller;

import org.example.Model.Entity.Locker;
import org.example.Service.EmailSenderService;
import org.example.Service.LockersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lockers")
@CrossOrigin(origins = "http://localhost:3000")
public class LockerController {
    @Autowired
    private LockersService lockersService;
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
    public String getStatus(@RequestParam Integer openCode) {
        return lockersService.lockerStatus(openCode);
    }

    @GetMapping("/location/{locationId}/findEmpty")
    public ResponseEntity<List<Locker>> getEmpty(@PathVariable int locationId, @RequestParam boolean isEmpty){
        List<Locker> lockers = lockersService.getEmpty(locationId, isEmpty);
        return ResponseEntity.ok(lockers);
    }
}
