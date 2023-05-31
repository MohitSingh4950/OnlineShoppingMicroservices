package com.codeanthem.inventoryservice.controller;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeanthem.inventoryservice.dto.InventoryResponse;
import com.codeanthem.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

	
	private final InventoryRepository inventoryRepository;
	
	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		log.info("Wait Started");
		try {
		Thread.sleep(10000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		log.info("Wait Started");
		return inventoryRepository.findBySkuCodeIn(skuCode)
				.stream().map(inventory -> 
					InventoryResponse.builder().skuCode(inventory.getSkuCode())
					.isInStock(inventory.getQuantity() >0)
					.build()
				).toList();
	}
}
