package com.codeanthem.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.codeanthem.orderservice.dto.InventoryResponse;
import com.codeanthem.orderservice.dto.OrderLineItemDto;
import com.codeanthem.orderservice.dto.OrderRequest;
import com.codeanthem.orderservice.event.OrderPlacedEvent;
import com.codeanthem.orderservice.model.OrderLineItems;
import com.codeanthem.orderservice.model.Orders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {
 
	
	private final OrderRepository orderRepository;
	private final WebClient.Builder webClientBuilder;
	private final Tracer tracer;
	private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;
	
	public String placeOrder(OrderRequest orderRequest) {
		Orders order = new Orders();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItems().stream()
		.map(this ::mapToDto)
		.toList();
		
		order.setOrderLineItems(orderLineItems);
		
		//collect all the skucodes
		List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode)
		.toList();
		log.info("Calling inventory Service");
		
		Span inventoryServiceLookup = tracer.nextSpan().name("Inventory Service lookup");
		try(Tracer.SpanInScope spanInScope =tracer.withSpan(inventoryServiceLookup.start())) {
//			tracer.withSpan(inventoryServiceLookup.start());
			
			//call inventory service and place order if product is in stock
			InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
					.uri("http://inventory-service/api/inventory",
							uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes)
							.build())
					.retrieve()
					.bodyToMono(InventoryResponse[].class)
					.block();
					
					boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
					
					if(allProductsInStock) {
						orderRepository.save(order);
						kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
						return "Order Placed Succesfully";
					}else {
						throw new IllegalArgumentException("Product is not in stock");
					}
		}finally {
			inventoryServiceLookup.end();
		}
		
		
		
		
//		orderRepository.save(order);
	}
	
	private OrderLineItems mapToDto(OrderLineItemDto orderLineItemDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		
		orderLineItems.setPrice(orderLineItemDto.getPrice());
		orderLineItems.setSkuCode(orderLineItemDto.getSkuCode());
		orderLineItems.setQuantity(orderLineItemDto.getQuantity());
		
		return orderLineItems;
	}
}
