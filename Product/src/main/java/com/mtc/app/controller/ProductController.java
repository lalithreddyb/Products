package com.mtc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		Product product = productService.getProduct(id);
		
		if(product == null)
		{
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
}
