package com.praveen.composit_primarykey_customgenerated_PK.Repository;


import com.praveen.composit_primarykey_customgenerated_PK.Entity.CompositPrimaryKey_Account;
import com.praveen.composit_primarykey_customgenerated_PK.Entity.CreateAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CreateAccountRepo extends JpaRepository<CreateAccount, CompositPrimaryKey_Account> {
    @Query(nativeQuery = true,value = "select count(*) from Account")
    public long findNumberOfRows();
    @Query(nativeQuery = true,value = "SELECT * FROM account order by account_id desc limit 1")
    public CreateAccount getTheLastRecord();
}
