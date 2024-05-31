package com.praveen.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Account_address")
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Adderss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adderId;

    private String doorNo;
    private String area;
    private String state;
    private long zipCode;

    public Adderss(String doorNo, String area, String state, long zipCode) {
        this.doorNo = doorNo;
        this.area = area;
        this.state = state;
        this.zipCode = zipCode;
    }
}
