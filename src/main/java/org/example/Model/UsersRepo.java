package org.example.Model;

import org.example.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
}
