package com.wolfofwrightstreet.transactiontracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "USER")
public class User {
    
    @Id
    @Column(name = "email_address", length = 255, nullable = false)
    String emailAddress;
    
    @Column(name = "first_name", length = 50, nullable = false)
    String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    String lastName;
}