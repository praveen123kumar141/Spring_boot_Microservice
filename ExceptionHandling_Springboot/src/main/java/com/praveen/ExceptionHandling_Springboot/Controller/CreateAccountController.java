package com.praveen.ExceptionHandling_Springboot.Controller;


import com.praveen.ExceptionHandling_Springboot.Entity.CompositPrimaryKey_Account;
import com.praveen.ExceptionHandling_Springboot.Entity.CreateAccount;
import com.praveen.ExceptionHandling_Springboot.Exceptions.AccountNotFound;
import com.praveen.ExceptionHandling_Springboot.Service.CreateAccountService;
import com.praveen.ExceptionHandling_Springboot.util.AccountDetilResponse;
import com.praveen.ExceptionHandling_Springboot.util.CreateAccountResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/bank")
@AllArgsConstructor
@Slf4j
public class CreateAccountController {

    private static final Logger log = LoggerFactory.getLogger(CreateAccountController.class);

    private CreateAccountService createAccountService;

    @PostMapping(value = "/createAccount")
    private ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccount createAccount) throws URISyntaxException {
      log.info("enter into the create method in  controller class");
        CreateAccount account = createAccountService.createAccount(createAccount);
        CreateAccountResponse accountCreatedSuccessfully = new CreateAccountResponse("Account created successfully", 201, new Date(), account);
        return ResponseEntity.created(new URI("/api/bank/createAccount")).body(accountCreatedSuccessfully);
    }

    @GetMapping(value="/getAccountDetails/{accountId}")
    private ResponseEntity<?> getAccountdetails(@PathVariable String accountId){
        log.info("Enter in to the CreateAccountController.getAccountdetails");
        CreateAccount accountDetails = createAccountService.getAccountDetails(accountId);
        return ResponseEntity.ok(accountDetails);
    }
    @PostMapping(value = "/findAccountDetails")
    private ResponseEntity<?> getAccountDetaillsByCompositKey(@RequestBody com.praveen.ExceptionHandling_Springboot.util.CompositPrimaryKey_Account compositPrimaryKeyAccount) throws AccountNotFound {
        CreateAccount accountDetails = createAccountService.getAccountDetaillsByCompositKey(compositPrimaryKeyAccount);
        List<CreateAccount> listAccount=new ArrayList<>();
        listAccount.add(accountDetails);
        AccountDetilResponse accountDetilResponse=new AccountDetilResponse("Fetched Account details successfully", 200, new Date(), listAccount);
        return ResponseEntity.ok(accountDetilResponse);
    }
    //By using query param @RequestParam
    @PostMapping(value = "/editAccount")
    private ResponseEntity<?> editAccount(@Valid @RequestBody CreateAccount createAccount,@RequestParam String accountId) throws URISyntaxException {
        log.info("enter into the edit method in  controller class");
        CreateAccount account = createAccountService.editAccount(createAccount,accountId);
        CreateAccountResponse accountCreatedSuccessfully = new CreateAccountResponse("Account created successfully", 201, new Date(), account);
        return ResponseEntity.created(new URI("/api/bank/createAccount")).body(accountCreatedSuccessfully);
    }


}

