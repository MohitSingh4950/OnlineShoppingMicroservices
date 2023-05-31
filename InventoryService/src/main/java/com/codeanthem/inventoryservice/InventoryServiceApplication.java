package com.codeanthem.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.codeanthem.inventoryservice.model.Inventory;
import com.codeanthem.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		
		return args ->{
			Inventory inventory = new Inventory();
			inventory.setSkuCode("Sumsung_S21");
			inventory.setQuantity(12);
			
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("oppo F7");
			inventory1.setQuantity(20);
			
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}
}
