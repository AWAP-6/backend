package org.example.Model;

import org.example.Model.Entity.ActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActivationTokenRepo extends JpaRepository<ActivationToken, Long> {
    Optional<ActivationToken> findByToken(UUID token);
}
