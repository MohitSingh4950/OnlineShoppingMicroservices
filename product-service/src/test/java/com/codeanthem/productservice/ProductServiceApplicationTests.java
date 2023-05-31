package com.codeanthem.productservice;

//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import java.math.BigDecimal;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import  org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import com.codeanthem.productservice.dto.ProductRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@Testcontainers
//@AutoConfigureMockMvc
class ProductServiceApplicationTests {

//	@Container
//	static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.26");
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@Autowired
//	private ObjectMapper objMapper;
//	
//	@DynamicPropertySource
//	static void setProperties(DynamicPropertyRegistry registry) {
//		registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
////		registry.add("spring.datasource.password", mySQLContainer::getPassword);
////		registry.add("spring.datasource.username", mySQLContainer::getUsername);
//	}
//	
//	@Test
//	void shouldCreateProduct() throws Exception{
//		ProductRequest productRequest = getProductRequest();
//		String contentValue = objMapper.writeValueAsString(productRequest);
//		
//		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(contentValue))
//				.andExpect(status().isCreated());
//	}
//
//	private ProductRequest getProductRequest() {
//		// TODO Auto-generated method stub
//		return ProductRequest.builder()
//				.name("Sumsung S21")
//				.description("50Mp Android")
//				.price(BigDecimal.valueOf(57000))
//				.build();
//	}

}
