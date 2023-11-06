package org.example.Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "locations", schema = "lockers_schema")
public class Location {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public Location(){}

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
