package com.praveen.validation.Service;

import com.praveen.validation.Entity.CreateAccount;
import com.praveen.validation.Repository.CreateAccountRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

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
        CreateAccount saveAccount = createAccountRepo.save(account);
        return saveAccount;
    }
}
