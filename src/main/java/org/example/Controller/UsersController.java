package org.example.Controller;

import org.example.Model.UsersRepo;
import org.example.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam Long id){
        if (usersService.deleteUser(id)) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
