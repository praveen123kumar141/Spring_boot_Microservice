package com.praveen.validation.Repository;

import com.praveen.validation.Entity.CreateAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreateAccountRepo extends JpaRepository<CreateAccount,Long> {
}
