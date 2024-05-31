package com.praveen.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="Account")
@Setter
@Getter
@Data
@NoArgsConstructor
@IdClass(CompositPrimaryKey_Account.class)
public class CreateAccount {
    //primary key
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String accountId;
    @Id
    private String customerId;
    @Id
    private String pan_id;

    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "Only alphabets are allowed with the first letter as capital")
    @NotNull(message = "Name is not null")
    @Size(min = 8, message = "Name must be at lease 8 letters")
    @Size(max = 30, message = "Name must be in less then 30 letters")
    private String accountName;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+.com")
    @NotEmpty(message = "Email cannot be empty")
    private String emailId;

    private LocalDate dob;

    private int age;

    @Min(value = 1000, message = "opening balance must should be more then 1000")
    private double openingBal;

    private String accountStatus;

    private LocalDate openingDate;

    private LocalDate lastUpdated;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address_id", referencedColumnName = "adderId")
    private Adderss adderss;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPan_id() {
        return pan_id;
    }

    public void setPan_id(String pan_id) {
        this.pan_id = pan_id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getOpeningBal() {
        return openingBal;
    }

    public void setOpeningBal(double openingBal) {
        this.openingBal = openingBal;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Adderss getAdderss() {
        return adderss;
    }

    public void setAdderss(Adderss adderss) {
        this.adderss = adderss;
    }

    public CreateAccount(String accountId, String customerId, String pan_id, String accountName, String emailId, LocalDate dob, int age, double openingBal, String accountStatus, LocalDate openingDate, LocalDate lastUpdated, Adderss adderss) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.pan_id = pan_id;
        this.accountName = accountName;
        this.emailId = emailId;
        this.dob = dob;
        this.age = age;
        this.openingBal = openingBal;
        this.accountStatus = accountStatus;
        this.openingDate = openingDate;
        this.lastUpdated = lastUpdated;
        this.adderss = adderss;
    }
}
