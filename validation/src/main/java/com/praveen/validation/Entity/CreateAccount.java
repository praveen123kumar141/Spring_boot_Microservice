package com.praveen.validation.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name="Account")
@Setter
@Getter
@AllArgsConstructor
@Data
public class CreateAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    private String customerId;

    private String panNo;

    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "Only alphabets are allowed with the first letter as capital")
    @NotNull(message = "Name is not null")
    @Size(min = 8,message = "Name must be at lease 8 letters")
    @Size(max=30,message = "Name must be in less then 30 letters")
    private String accountName;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+.com")
    @NotEmpty(message = "Email cannot be empty")
    private String emailId;

    private LocalDate dob;

    private int age;

    @Min(value = 1000,message = "opening balance must should be more then 1000")
    private double openingBal;

    private String accountStatus;

    private LocalDate openingDate;

    private LocalDate lastUpdated;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address_id",referencedColumnName = "adderId")
    private Adderss adderss;

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setOpeningBal(double openingBal) {
        this.openingBal = openingBal;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setAdderss(Adderss adderss) {
        this.adderss = adderss;
    }

    public long getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getEmailId() {
        return emailId;
    }

    public double getOpeningBal() {
        return openingBal;
    }

    public String isAccountStatus() {
        return accountStatus;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public Adderss getAdderss() {
        return adderss;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
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

    public String getAccountStatus() {
        return accountStatus;
    }
}
