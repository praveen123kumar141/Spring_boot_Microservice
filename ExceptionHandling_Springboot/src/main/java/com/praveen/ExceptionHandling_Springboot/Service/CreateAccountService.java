package com.praveen.ExceptionHandling_Springboot.Service;


import com.praveen.ExceptionHandling_Springboot.Entity.CompositPrimaryKey_Account;
import com.praveen.ExceptionHandling_Springboot.Entity.CreateAccount;
import com.praveen.ExceptionHandling_Springboot.Exceptions.AccountNotFound;

public interface CreateAccountService {

    public CreateAccount createAccount(com.praveen.ExceptionHandling_Springboot.Entity.CreateAccount account);

    public CreateAccount getAccountDetails(String accountId);
    public CreateAccount editAccount(CreateAccount createAccount,String accountId);
    public CreateAccount getAccountDetaillsByCompositKey(com.praveen.ExceptionHandling_Springboot.util.CompositPrimaryKey_Account compositPrimaryKey_account) throws AccountNotFound;
}
