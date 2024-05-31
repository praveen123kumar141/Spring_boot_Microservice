package com.praveen.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.praveen.Entity.Adderss;
import com.praveen.Entity.CreateAccount;
import com.praveen.Exceptions.AccountNotFound;
import com.praveen.Service.CreateAccountServiceImpl;
import com.praveen.util.CompositPrimaryKey_Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@WebMvcTest(CreateAccountController.class)
class CreateAccountControllerTest {

    @MockBean
    CreateAccountServiceImpl createAccountService;

   @Autowired
    private MockMvc mockMvc;

   CreateAccount createAccount;

    @Test
    public void test_createAccount_success() throws Exception {
        CreateAccount mock = mock(CreateAccount.class);
        createAccount = new CreateAccount("123451234512345", "cus12345", "cbspn1234", "Praveenkumar", "exp@gmail.com", null,
                10, 1000, "Active", null, null, new Adderss("7-6", "abc", "ap", 522549));

        when(createAccountService.createAccount(nullable(CreateAccount.class))).thenReturn(createAccount);

        this.mockMvc.perform(post("/api/bank/createAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createAccount)))
                .andDo(print())
                .andExpect(status().isCreated());
    }
    @Test
    public void test_createAccount_bad_request() throws Exception {
        CreateAccount mock = mock(CreateAccount.class);
        createAccount = new CreateAccount("123451234512345", "cus12345", "cbspn1234", "Praveenkumar", "exp@gmail", null,
                10, 1000, "Active", null, null, new Adderss("7-6", "abc", "ap", 522549));

        when(createAccountService.createAccount(nullable(CreateAccount.class))).thenReturn(createAccount);

        this.mockMvc.perform(post("/api/bank/createAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createAccount)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAccountDetaillsByCompositKey() throws Exception {

        createAccount = new CreateAccount("123451234512345", "cus12345", "cbspn1234", "Praveenkumar", "exp@gmail.com", null,
                10, 1000, "Active", null, null, new Adderss("7-6", "abc", "ap", 522549));

        when(createAccountService.getAccountDetaillsByCompositKey(mock(CompositPrimaryKey_Account.class))).thenReturn(createAccount);

        CompositPrimaryKey_Account compositPrimaryKeyAccount=new CompositPrimaryKey_Account("cus12345","cbspn1234","123451234512345");
        this.mockMvc.perform(post("/api/bank/findAccountDetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(compositPrimaryKeyAccount)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAccountDetailsByAccountId() throws Exception {
        createAccount = new CreateAccount("123451234512345", "cus12345", "cbspn1234", "Praveenkumar", "exp@gmail.com", null,
                10, 1000, "Active", null, null, new Adderss("7-6", "abc", "ap", 522549));

        when(createAccountService.findAccountDetailsByAccountId(any(String.class))).thenReturn(createAccount);

        this.mockMvc.perform(get("/api/bank/getAccountDetails/12345"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test_delectAccountByAccountId(){
        //https://www.youtube.com/watch?v=HqiEM5HQsZs&t=3565s
    }

}