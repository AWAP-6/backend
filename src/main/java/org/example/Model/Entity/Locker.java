package org.example.Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "lockers", schema = "lockers_schema")
public class Locker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "lock_num")
    private int lockNum;

    @Column(name = "open_code")
    private int openCode;
    @Column(name = "is_empty")
    private boolean isEmpty;
    @Column(name = "location_id")
    private int location_id;

    @Column(name = "status")
    private String status;
    public Locker(){}
    public int getLocation_id() {
        return location_id;
    }
    public int getLockNum() {
        return lockNum;
    }
    public int getOpenCode() {
        return openCode;
    }
    public boolean isEmpty() {
        return isEmpty;
    }
    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }
    public void setLockNum(int lockNum) {
        this.lockNum = lockNum;
    }
    public void setOpenCode(int openCode) {
        this.openCode = openCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Locker{" +
                "id=" + id +
                ", lockNum=" + lockNum +
                ", openCode=" + openCode +
                ", isEmpty=" + isEmpty +
                ", location_id=" + location_id +
                ", status=" + status +
                '}';
    }
}
