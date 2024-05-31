package com.praveen.Service;

import com.praveen.Entity.CompositPrimaryKey_Account;
import com.praveen.Entity.CreateAccount;
import com.praveen.Entity.Adderss;
import com.praveen.Exceptions.AccountNotFound;
import com.praveen.Repository.CreateAccountRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CreateAccountServiceImplTest {

    @Mock
    private CreateAccountRepo createAccountRepo;
    @InjectMocks
    private CreateAccountServiceImpl createAccountService;
    AutoCloseable autoCloseable;
    CreateAccount createAccount;
    List<CreateAccount> li=new ArrayList<>();

    @BeforeEach
    void setUp() {
       // CreateAccountRepo mock = mock(CreateAccountRepo.class);
        createAccountService=new CreateAccountServiceImpl(createAccountRepo);
        autoCloseable= MockitoAnnotations.openMocks(this);
        createAccount = new CreateAccount("123451234512345", "cus12345", "cbspn1234", "praveen", "exp@gmail.com", null,
                10, 1000, "Active", null, null, new Adderss("7-6", "abc", "ap", 522549));

        li.add(createAccount);
        li.add(new CreateAccount("12346", "cus12346", "cbspn1235", "kumar", "exp1@gmail.com", null,
                10, 1001, "Active", null, null, new Adderss("gh7-8", "abcd", "ap", 522544)));
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }
    //https://www.geeksforgeeks.org/unit-testing-in-spring-boot-project-using-mockito-and-junit/
    @Test
    void test_createAccount() {
      //  mock(CreateAccount.class);
        mock(CreateAccountRepo.class);

        when(createAccountRepo.save(createAccount)).thenReturn(createAccount);
        Mockito.when(createAccountRepo.findNumberOfRows()).thenReturn(0L);
        List<CreateAccount> empty_list=new ArrayList<>();
        when(createAccountRepo.findAll()).thenReturn(empty_list);
        when(createAccountRepo.getTheLastRecord()).thenReturn(createAccount);
        createAccountService.createAccount(createAccount);
    }
    @Test
    void test_createAccount1() {
        //  mock(CreateAccount.class);
        mock(CreateAccountRepo.class);

        when(createAccountRepo.save(createAccount)).thenReturn(createAccount);
        Mockito.when(createAccountRepo.findNumberOfRows()).thenReturn(5L);
        when(createAccountRepo.findAll()).thenReturn(li);
        when(createAccountRepo.getTheLastRecord()).thenReturn(createAccount);
        CreateAccount account = createAccountService.createAccount(createAccount);
        assertThat(account.getAccountStatus()).isEqualTo("Active");
    }

    @Test
    void editAccount() {
    }

    @Test
    void test_getAccountDetaillsByCompositKey() throws AccountNotFound {

        com.praveen.util.CompositPrimaryKey_Account compositPrimaryKeyAccount= new com.praveen.util.CompositPrimaryKey_Account( "cus12345","cbspn1234","123451234512345" );
        when(createAccountRepo.findByAccountDetails("123451234512345","cus12345","cbspn1234")).thenReturn(createAccount);
        createAccountService.getAccountDetaillsByCompositKey(compositPrimaryKeyAccount);
    }
}