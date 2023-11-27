package org.example.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Model.Entity.User;
import org.example.Model.RoleRepo;
import org.example.Model.UsersRepo;
import org.example.Model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersService {
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (usersRepo.findByEmail(email) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.USER_ROLE);
        log.info("Saving new User with email: {}", email);
        usersRepo.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (usersRepo.findById(userId).isEmpty()) return false;
        roleRepo.deleteById(userId);
        usersRepo.deleteById(userId);
        return true;
    }
}
