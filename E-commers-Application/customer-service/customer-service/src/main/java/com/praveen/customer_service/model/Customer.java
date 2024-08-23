package com.praveen.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address_id", referencedColumnName = "adderId")
    private Address address;
}
