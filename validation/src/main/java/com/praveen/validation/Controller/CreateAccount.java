package com.praveen.validation.Controller;

import com.praveen.validation.Service.CreateAccountService;
import com.praveen.validation.util.CreateAccountResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

@RestController
@RequestMapping("/api/bank")
@AllArgsConstructor
@Slf4j
public class CreateAccount {

    private static final Logger log = LoggerFactory.getLogger(CreateAccount.class);
    private CreateAccountService createAccountService;

    @PostMapping(value = "/createAccount")
    private ResponseEntity<?> createAccount(@Valid @RequestBody com.praveen.validation.Entity.CreateAccount createAccount) throws URISyntaxException {
      log.info("enter into the create method in  controller class");
        com.praveen.validation.Entity.CreateAccount account = createAccountService.createAccount(createAccount);
        CreateAccountResponse accountCreatedSuccessfully = new CreateAccountResponse("Account created successfully", 201, new Date(), account);
        return ResponseEntity.created(new URI("/api/bank/createAccount")).body(accountCreatedSuccessfully);
    }
}
