package org.example.Service;

import org.example.Model.Entity.Users;
import org.example.Model.Entity.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsersService {
    @Autowired
    private UsersRepo usersRepo;
    public ArrayList<Users> getAllUsers(){
        return new ArrayList<Users>(usersRepo.findAll());
    }
}
