package com.praveen.validation.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Account_address")
@AllArgsConstructor
@Setter
@Getter
public class Adderss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adderId;

    private String doorNo;
    private String area;
    private String state;
    private long zipCode;
}
