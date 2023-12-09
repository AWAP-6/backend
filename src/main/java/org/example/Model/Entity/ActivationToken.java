package org.example.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "activationtokens", schema = "lockers_schema")
@Data
public class ActivationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "activation_token", nullable = false, unique = true)
    private UUID token;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Column(name = "is_used", nullable = false)
    private Boolean isUsed = false;

}
