package com.praveen.Inventory_service;

import com.praveen.Inventory_service.model.Inventory;
import com.praveen.Inventory_service.repository.InventoryRepository;
import com.praveen.Inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan({"com.praveen.Inventory_service.controller"})
//@EnableJpaRepositories("com.praveen.Inventory_service.repository")
//@EntityScan("com.praveen.Inventory_service.model")
@RequiredArgsConstructor
public class InventoryServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	private final InventoryRepository inventoryRepository;

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args->{
			Inventory inventory1=new Inventory();
			inventory1.setSkuCode("Iphone");
			inventory1.setQuantity(100);

			Inventory inventory2=new Inventory();
			inventory2.setSkuCode("Iphone_13_red");
			inventory2.setQuantity(67);

			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}
}
