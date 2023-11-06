package org.example.Controller;

import org.example.Service.LockersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lockers")
public class LockerController {
    @Autowired
    private LockersService lockersService;
    @GetMapping
    public String getAllLockers(){
        return lockersService.getAllLockers().toString();
    }
}
