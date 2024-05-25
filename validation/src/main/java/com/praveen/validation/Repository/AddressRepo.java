package com.praveen.validation.Repository;

import com.praveen.validation.Entity.Adderss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Adderss,Long> {
}
