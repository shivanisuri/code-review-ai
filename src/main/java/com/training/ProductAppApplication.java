package com.training;//base package

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.training.dal.springdata.ProductRepository;
import com.training.domain.Product;
import com.training.ui.ProductConsoleUI;

@SpringBootApplication
public class ProductAppApplication {

	public static void main(String[] args) {
		ApplicationContext springContainer = 
				SpringApplication.run(ProductAppApplication.class, args);
		ProductConsoleUI ui = springContainer.getBean(ProductConsoleUI.class);
//		ProductConsoleUI ui2 = springContainer.getBean(ProductConsoleUI.class);
		boolean disabled = false;
//		System.out.println(ui == ui2);
		
		//ui.createProductWithUI();
		if(disabled){
			testRepoForDisabled(springContainer)
		}
		testRepo(springContainer);
	}

	
	public static void testRepo(ApplicationContext springContainer) {
		ProductRepository repo = springContainer.getBean(ProductRepository.class);
		
		Product aProduct = new Product("repo", 12345, 12);
		
		Product saved = repo.save(aProduct);
		
		System.out.println("Saved using repo: "+saved.getId());
	}

	public static void testRepoForDisabled(ApplicationContext springContainer) {
		ProductRepository repo = springContainer.getBean(ProductRepository.class);
		
		Product disabledProduct = new Product("repo", 8765, 13);
		
		Product saved = repo.save(disabledProduct);
		
		System.out.println("disabledProduct in repo: "+saved.getId());
	}
}
