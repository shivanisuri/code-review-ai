package com.training.web;


import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.training.ProductAppApplication;
import com.training.dal.ProductDAO;
import com.training.domain.Product;

@SpringBootTest(
		classes = ProductAppApplication.class,
		webEnvironment = WebEnvironment.MOCK
		)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-qa.properties")
public class ProductControllerIntegrationTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ProductDAO dao;
	
	@Test
	public void testGetProductById() throws Exception {
		
		//Arrange
		Product testProduct = new Product("test1", 10000, 5);
		Product saved = dao.save(testProduct);
		int id = saved.getId();
		//Act and Assert
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/products/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id)));
		
	}


}