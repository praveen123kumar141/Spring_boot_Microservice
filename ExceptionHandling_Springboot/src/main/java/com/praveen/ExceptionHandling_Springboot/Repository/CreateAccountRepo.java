package com.praveen.ExceptionHandling_Springboot.Repository;

import com.praveen.ExceptionHandling_Springboot.Entity.CompositPrimaryKey_Account;
import com.praveen.ExceptionHandling_Springboot.Entity.CreateAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CreateAccountRepo extends JpaRepository<CreateAccount, CompositPrimaryKey_Account> {
    @Query(nativeQuery = true,value = "select count(*) from Account")
    public long findNumberOfRows();
    @Query(nativeQuery = true,value = "SELECT * FROM account order by account_id desc limit 1")
    public CreateAccount getTheLastRecord();
    @Query(nativeQuery = true,value ="SELECT * FROM account where account_id=? OR customer_id=? OR  pan_no=?")
    public CreateAccount findByAccountDetails(String accountId,String customerId,String pan_no);
}
