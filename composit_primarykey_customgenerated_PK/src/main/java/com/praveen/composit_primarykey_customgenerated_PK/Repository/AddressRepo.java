package com.praveen.composit_primarykey_customgenerated_PK.Repository;


import com.praveen.composit_primarykey_customgenerated_PK.Entity.Adderss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Adderss,Long> {
}
