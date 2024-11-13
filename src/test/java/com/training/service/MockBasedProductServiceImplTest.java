/**
 * 
 */
package com.training.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.training.dal.ProductDAO;
import com.training.domain.Product;

/**
 * @author preeprab
 *
 */
class MockBasedProductServiceImplTest {

	@Test
	void addProduct_Returns_Valid_Id_When_Value_GTEQ_MinValue() {
		//AAA
				//Arrange
				ProductServiceImpl objectUnderTest = new ProductServiceImpl();
				Product argToTestMethod = new Product("test", ProductServiceImpl.MIN_VALUE, 1);
				
				Product returnedByMock = new Product("test", ProductServiceImpl.MIN_VALUE, 1);
				returnedByMock.setId(1);
				
				ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
				Mockito.when(mockDAO.save(argToTestMethod)).thenReturn(returnedByMock);
				objectUnderTest.setDao(mockDAO);
				//Act
				int id = objectUnderTest.addProduct(argToTestMethod);
				//Assert
				assertTrue(id > 0);
	}

}
