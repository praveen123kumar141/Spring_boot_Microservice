package com.praveen.Repository;

import com.praveen.Entity.CompositPrimaryKey_Account;
import com.praveen.Entity.CreateAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface CreateAccountRepo extends JpaRepository<CreateAccount, CompositPrimaryKey_Account> {
    @Query(nativeQuery = true,value = "select count(*) from Account")
    public long findNumberOfRows();

    @Query(nativeQuery = true,value = "SELECT * FROM account order by account_id desc limit 1")
    public CreateAccount getTheLastRecord();

    @Query(nativeQuery = true,value ="SELECT * FROM account where account_id=? OR customer_id=? OR  pan_no=?")
    public CreateAccount findByAccountDetails(String accountId,String customerId,String pan_no);

    @Query(nativeQuery = true,value = " select * from account where account_id=?")
    public CreateAccount findAccountDetailsByAccountId(String accountId);

    @Query(nativeQuery = true,value = "delete from account where account_id=?")
    public void delectAccountByAccountId(String accountId);
}
