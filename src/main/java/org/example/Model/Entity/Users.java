package org.example.Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "lockers_schema")
public class Users {
    @Column(name = "surname")
    private String surname;
    @Column(name = "password")
    private String password;
    @Column(name = "lockerlocation_id")
    private int lockerlocation_id;
    @Column(name = "name")
    private String name;
    @Id
    @Column(name = "id")
    private int id;
    public Users(){}
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getPassword() {
        return password;
    }
    public int getLockerlocation_id() {
        return lockerlocation_id;
    }
    public int get_id() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLockerlocation_id(int lockerlocation_id) {
        this.lockerlocation_id = lockerlocation_id;
    }

    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", lockerlocation_id=" + lockerlocation_id +
                ", id=" + id +
                '}';
    }
}
