package com.praveen.composit_primarykey_customgenerated_PK.Service;

import com.praveen.composit_primarykey_customgenerated_PK.Entity.CreateAccount;
import com.praveen.composit_primarykey_customgenerated_PK.Repository.CreateAccountRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
@Slf4j
public class CreateAccountServiceImpl implements CreateAccountService{

    private static final Logger log = LoggerFactory.getLogger(CreateAccountServiceImpl.class);

    private CreateAccountRepo createAccountRepo;

    @Override
    public CreateAccount createAccount(CreateAccount account) {
        log.info("Enter in to the Create Account method");
        account.setOpeningDate(LocalDate.now());
        account.setAccountStatus("Active");
        account.setCustomerId(customerIdGenerator());
        account.setAccountId(accountId_Generator());
        CreateAccount saveAccount = createAccountRepo.save(account);
        log.info(createAccountRepo.getTheLastRecord().toString());
        return saveAccount;
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
            return "57154"+arr[len-1]+String.valueOf(Long.valueOf(theLastRecord.getAccountId().substring(5))+1);
        }
    }
}
