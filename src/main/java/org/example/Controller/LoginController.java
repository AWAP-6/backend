package org.example.Controller;

import lombok.Data;
import org.example.Model.Entity.User;
import org.example.Model.LocationRepo;
import org.example.Service.LocationService;
import org.example.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private LocationService locationService;

    @Data
    public static class ApiResponse {
        private boolean success;
        private String message;
        private User user;

        public ApiResponse(boolean success, String message){
            this.success = success;
            this.message = message;
        }
    }
    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        ApiResponse apiResponse;
        if (usersService.createUser(user)) {
            apiResponse = new ApiResponse(true, "User created successfully.");
            return ResponseEntity.ok().body(apiResponse);
        } else {
            apiResponse = new ApiResponse(false, "Error creating user.");
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        ApiResponse apiResponse;
        if (usersService.authenticateUser(user.getUsername(), user.getPassword())) {
            User authenticatedUser = usersService.getUserByUsername(user.getUsername());

            apiResponse = new ApiResponse(true, "Login successfully.");
            return ResponseEntity.ok().body(apiResponse);
        } else {
            apiResponse = new ApiResponse(false, "Invalid username or password");
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @GetMapping("/hello")
    public ResponseEntity<String> securityUrl() {
        return ResponseEntity.ok("hello");
    }

}