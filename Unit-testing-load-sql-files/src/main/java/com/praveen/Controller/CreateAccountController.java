package com.praveen.Controller;


import com.praveen.Entity.CreateAccount;
import com.praveen.Exceptions.AccountNotFound;
import com.praveen.Service.CreateAccountService;
import com.praveen.util.AccountDetilResponse;
import com.praveen.util.CreateAccountResponse;
import com.praveen.util.CompositPrimaryKey_Account;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

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

    @PostMapping(value = "/findAccountDetails")
    private ResponseEntity<?> getAccountDetaillsByCompositKey(@RequestBody CompositPrimaryKey_Account compositPrimaryKeyAccount) throws AccountNotFound {
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

    @GetMapping(value = "/getAccountDetails/{accountId}")
    public ResponseEntity<?> getAccountDetailsByAccountId(@PathVariable String accountId) throws AccountNotFound {
        CreateAccount accountDetailsByAccountId = createAccountService.findAccountDetailsByAccountId(accountId);
        List<CreateAccount> listAccount=new ArrayList<>();
        listAccount.add(accountDetailsByAccountId);
        AccountDetilResponse accountDetilResponse=new AccountDetilResponse("Fetched Account details successfully", 200, new Date(), listAccount);
        return ResponseEntity.ok(accountDetilResponse);
    }
    @DeleteMapping("/deleteAccount/{accountId}")
    public ResponseEntity<?> delectAccountByAccountId(String accountId) throws AccountNotFound {
        boolean b = createAccountService.delectAccountByAccountId(accountId);
        if(b){
        Map<String,String> map=new HashMap<>();
        map.put("Respose_code","204");
        map.put("Time stamp",new Date().toString());
        map.put("Response Message","Account deleted successfully"+accountId);
        return ResponseEntity.noContent().build();
    }else{
        if(accountId.length()!=15)
            throw new AccountNotFound("Invalid account id account Id must be 15 letters "+ accountId);
        else
            throw new AccountNotFound("Account Not Found " + accountId);
    }
    }
}

