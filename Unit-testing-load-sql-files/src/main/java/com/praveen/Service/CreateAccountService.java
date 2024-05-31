package com.praveen.Service;


import com.praveen.Entity.CreateAccount;
import com.praveen.Exceptions.AccountNotFound;
import com.praveen.util.CompositPrimaryKey_Account;

public interface CreateAccountService {
    public boolean delectAccountByAccountId(String accountId);
    public CreateAccount createAccount(CreateAccount account);
    public CreateAccount findAccountDetailsByAccountId(String accountId) throws AccountNotFound;
      public CreateAccount editAccount(CreateAccount createAccount,String accountId);
    public CreateAccount getAccountDetaillsByCompositKey(CompositPrimaryKey_Account compositPrimaryKey_account) throws AccountNotFound;
}
