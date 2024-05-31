package com.praveen.Service;


import com.praveen.Entity.CompositPrimaryKey_Account;
import com.praveen.Entity.CreateAccount;
import com.praveen.Exceptions.AccountNotFound;
import com.praveen.Repository.CreateAccountRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class CreateAccountServiceImpl implements CreateAccountService{

    private static final Logger log = LoggerFactory.getLogger(CreateAccountServiceImpl.class);

    private CreateAccountRepo createAccountRepo;

    public CreateAccountServiceImpl(CreateAccountRepo createAccountRepo) {
        this.createAccountRepo = createAccountRepo;
    }


    @Override
    public boolean delectAccountByAccountId(String accountId) {
        createAccountRepo.delectAccountByAccountId(accountId);
        return true;
    }

    @Override
    public CreateAccount createAccount(CreateAccount account) {
        log.info("Enter in to the Create Account method");
        account.setOpeningDate(LocalDate.now());
        account.setAccountStatus("Active");
        account.setCustomerId(customerIdGenerator());
        createAccountRepo.findAll();
        account.setAccountId(accountId_Generator());
        CreateAccount saveAccount = createAccountRepo.save(account);
        log.info(createAccountRepo.getTheLastRecord().toString());
        return saveAccount;
    }

    @Override
    public CreateAccount findAccountDetailsByAccountId(String accountId) throws AccountNotFound {
        CreateAccount accountDetailsByAccountId = createAccountRepo.findAccountDetailsByAccountId(accountId);
        if(accountDetailsByAccountId ==null){
            if(accountId.length()!=15)
                throw new AccountNotFound("Invalid account id account Id must be 15 letters "+ accountId);
            else
                throw new AccountNotFound("Account Not Found " + accountId);
        }
        return accountDetailsByAccountId;
    }

    @Override
    public CreateAccount editAccount(CreateAccount createAccount, String accountId) {
        return null;
    }

    @Override
    public CreateAccount getAccountDetaillsByCompositKey(com.praveen.util.CompositPrimaryKey_Account compositPrimaryKey_account) throws AccountNotFound {
        CreateAccount accountDetails=createAccountRepo.findByAccountDetails(compositPrimaryKey_account.getAccountId(),compositPrimaryKey_account.getCustomerId(),compositPrimaryKey_account.getPan_id());
        if(accountDetails==null){
            if(compositPrimaryKey_account.getAccountId().length()!=15)
                throw new AccountNotFound("Invalid account id account Id must be 15 letters "+compositPrimaryKey_account.getAccountId());
            else
                throw new AccountNotFound("Account Not Found "+compositPrimaryKey_account.getAccountId());
        }
        return accountDetails;
    }

    private String customerIdGenerator(){
        long numberOfRows = createAccountRepo.findNumberOfRows();
        if(numberOfRows==0){
            return "CUS0000001";
        }else {
            log.info("enter in to the custom id generator class");
            String[] arr = {"CUS000000", "CUS00000", "CUS0000", "CUS000", "CUS00", "CUS0", "CUS"};
            numberOfRows=Long.valueOf(numberOfRows)+ 1;
            int str_len = String.valueOf(numberOfRows).length();
            String cus_id = arr[str_len - 1] + String.valueOf(numberOfRows);
            log.info("enter in to the custom id generator class" + cus_id);
            return cus_id;
        }
    }
    private String accountId_Generator(){
        CreateAccount theLastRecord = createAccountRepo.getTheLastRecord();
        if(theLastRecord==null){
            return "571540000000001";
        }else{
        String arr[]={"000000000","00000000","0000000","000000","00000","0000","000","00","0"};
          int len=  String.valueOf(Long.valueOf(theLastRecord.getAccountId().substring(5))+1).length();
            return  len==10?"57154"+String.valueOf(Long.valueOf(theLastRecord.getAccountId().substring(5))+1):"57154"+arr[len-1]+String.valueOf(Long.valueOf(theLastRecord.getAccountId().substring(5))+1);
        }
    }

}
