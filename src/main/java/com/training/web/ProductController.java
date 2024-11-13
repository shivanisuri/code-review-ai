package com.training.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.domain.Product;
import com.training.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return service.findAll();
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") int id) {
		Product p = service.findById(id);
		if(p == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(404, "record not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(p, HttpStatus.OK);
	}
	
  	@PostMapping("/products")
	public ResponseEntity<?> addProduct(@RequestBody Product toBeAdded){
		
		try {
			int id = service.addProduct(toBeAdded);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/products/"+id));
			return new ResponseEntity<Product>(toBeAdded, headers, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
}