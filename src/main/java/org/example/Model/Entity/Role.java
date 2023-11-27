package org.example.Model.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Table(name = "user_roles", schema = "lockers_schema")
@Entity
public class Role {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "role_name")
    private String roleName;

}
