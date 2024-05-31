package com.praveen.ExceptionHandling_Springboot.Repository;


import com.praveen.ExceptionHandling_Springboot.Entity.Adderss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Adderss,Long> {
}
