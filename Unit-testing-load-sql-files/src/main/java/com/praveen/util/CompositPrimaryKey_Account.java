package com.praveen.util;

public class CompositPrimaryKey_Account {
    private String customerId;
    private String pan_id;
    private String accountId;

    public CompositPrimaryKey_Account(String customerId, String pan_id, String accountId) {
        this.customerId = customerId;
        this.pan_id = pan_id;
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
