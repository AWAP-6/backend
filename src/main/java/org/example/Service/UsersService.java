package org.example.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Model.ActivationTokenRepo;
import org.example.Model.Entity.ActivationToken;
import org.example.Model.Entity.User;
import org.example.Model.RoleRepo;
import org.example.Model.UsersRepo;
import org.example.Model.enums.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UsersService {
    private final AuthenticationManager authenticationManager;
    private final ActivationTokenRepo activationTokenRepo;
    private final UsersRepo usersRepo;
    private final RoleRepo roleRepo;
    private final ActivationTokenRepo tokenRepo;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;
    public boolean createUser(User user) {
        String activationTokenString = UUID.randomUUID().toString();
        UUID activationTokenUUID = UUID.fromString(activationTokenString);
        LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(15);
        String activationUrl = "http://localhost:3000/activate?token=" + activationTokenString;
        String email = user.getEmail();
        User existingUser = usersRepo.findByEmail(user.getEmail());
        if (existingUser != null) {
            return false;
        }

        user.setActive(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.USER_ROLE);
        User savedUser = usersRepo.save(user);

        ActivationToken activationToken = new ActivationToken();
        activationToken.setUser(savedUser);
        activationToken.setToken(activationTokenUUID);
        activationToken.setExpirationDate(expirationDate);
        activationToken.setIsUsed(false);
        tokenRepo.save(activationToken);

        emailSenderService.sendMail(email, "Email confirmation", "Please click the following link to activate your account: " + activationUrl);
        return true;
    }
    public boolean activateUser(String token) {
        UUID tokenUUID;
        try {
            tokenUUID = UUID.fromString(token);
        } catch (IllegalArgumentException e) {
            return false;
        }
        Optional<ActivationToken> tokenOpt = tokenRepo.findByToken(tokenUUID);
        if (!tokenOpt.isPresent() || tokenOpt.get().getIsUsed() ||
                tokenOpt.get().getExpirationDate().isBefore(LocalDateTime.now())) {
            return false;
        }
        ActivationToken activationToken = tokenOpt.get();
        User user = activationToken.getUser();
        if (user != null) {
            user.setActive(true);
            usersRepo.save(user);
            activationToken.setIsUsed(true);
            tokenRepo.save(activationToken);
            return true;
        }
        return false;
    }
    public boolean authenticateUser(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user = {}", username);
            return false;
        }
    }
    @Transactional
    public boolean deleteUser(Long userId) {
        if (usersRepo.findById(userId).isEmpty()) return false;
//        System.out.println(usersRepo.findById(userId).get());
        activationTokenRepo.deleteByUser( usersRepo.findById(userId).get());
        usersRepo.deleteById(userId);
        return true;
    }


    public void showUser(Long userId){
        System.out.println(usersRepo.findById(userId).get());
    }
}
