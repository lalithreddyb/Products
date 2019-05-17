package com.mtc.app.controller;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mtc.app.product.Product;
import com.mtc.app.service.ProductService;

@RequestMapping(value = "/Products")
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping(produces = "application/json")
	public List<Product> getAllProducts()
	{
		return productService.getAllProducts();
	}
	
	@GetMapping(value = "/{productId}", produces = "application/json")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") int id)
	{
		try {
		Product product = productService.getProduct(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> addProduct(Product product)
	{
		Product productAdded = productService.addProduct(product);
		
		HttpHeaders pathHeader = new HttpHeaders();
		
		if (productAdded != null)
		 {
			 pathHeader.add("Location", ServletUriComponentsBuilder.fromCurrentRequest()
					 .path("/{id}")
					 .buildAndExpand(productAdded.getId())
					 .toUri().toString());			
		 }
		
		return new ResponseEntity<String>(pathHeader,HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") int id)
	{
		try 
		{
			productService.deleteProduct(id);
			return new ResponseEntity<String>("product was deleted",HttpStatus.OK);
		}catch(Exception e)
				{
					//if (e instanceof EmptyResultDataAccessException)
					return new ResponseEntity<String>("product was not found",HttpStatus.NOT_FOUND);
				}
	}
	
}
