package com.praveen.Repository;


import com.praveen.Entity.Adderss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Adderss,Long> {
}
