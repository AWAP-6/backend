package org.example.Model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "parcels", schema = "lockers_schema")
@Data
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parcel_id")
    private Long parcelId;
    @Column(name = "sender_name")
    private String senderName;
    @Column(name = "sender_email")
    private String senderEmail;
    @Column(name = "sender_address")
    private String senderAddress;
    @Column(name = "recipient_name")
    private String recipientName;
    @Column(name = "recipient_email")
    private String recipientEmail;
    @Column(name = "recipient_address")
    private String recipientAddress;
    @Column(name = "from_location")
    private Integer fromLocation;
    @Column(name = "to_location")
    private Integer toLocation;

    public Parcel() {
    }
}
