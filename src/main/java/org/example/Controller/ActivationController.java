package org.example.Controller;

import org.example.Service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/activation")
@CrossOrigin(origins = {"https://touchscreen.onrender.com", "https://driverapp-c1h1.onrender.com", "https://consumerapp.onrender.com"})
public class ActivationController {

    private final UsersService usersService;

    public ActivationController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/act")
    public ResponseEntity<String> activateAccount(@RequestParam("token") String token) {
        boolean activationResult = usersService.activateUser(token);
        if (activationResult) {
            return ResponseEntity.ok("Account activated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Activation failed. Token may be invalid or expired.");
        }
    }


}
