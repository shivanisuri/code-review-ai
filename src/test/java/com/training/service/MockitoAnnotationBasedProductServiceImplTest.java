package com.training.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.training.dal.ProductDAO;
import com.training.domain.Product;

@ExtendWith(MockitoExtension.class)
class MockitoAnnotationBasedProductServiceImplTest {

	@Mock
	ProductDAO mockDAO;
	
	//Fast Independent Repeatable Self-validating Timely
	@Test
	void addProduct_Returns_Valid_Id_When_Value_GTEQ_MinValue() {
		//AAA
		//Arrange
		ProductServiceImpl objectUnderTest = new ProductServiceImpl();
		Product argToTestMethod = new Product("test", ProductServiceImpl.MIN_VALUE, 1);
		
		Product returnedByMock = new Product("test", ProductServiceImpl.MIN_VALUE, 1);
		returnedByMock.setId(1);
		
		
		Mockito.when(mockDAO.save(argToTestMethod)).thenReturn(returnedByMock);
		objectUnderTest.setDao(mockDAO);
		//Act
		int id = objectUnderTest.addProduct(argToTestMethod);
		//Assert
		assertTrue(id > 0);
	}

}
