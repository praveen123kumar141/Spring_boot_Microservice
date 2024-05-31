package com.praveen.composit_primarykey_customgenerated_PK.Entity;
import java.io.Serializable;
import java.util.Objects;

public class CompositPrimaryKey_Account implements Serializable {

    private String customerId;
    private String pan_id;
    private String accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositPrimaryKey_Account that = (CompositPrimaryKey_Account) o;
        return accountId == that.accountId && Objects.equals(customerId, that.customerId) && Objects.equals(pan_id, that.pan_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, pan_id, accountId);
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
