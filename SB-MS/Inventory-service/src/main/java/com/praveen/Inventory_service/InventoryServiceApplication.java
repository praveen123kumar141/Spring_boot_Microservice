package com.praveen.Inventory_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com/praveen/Inventory_service/controller","com/praveen/Inventory_service/service"})
public class InventoryServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
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
