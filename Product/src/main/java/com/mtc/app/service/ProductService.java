package com.mtc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mtc.app.product.Product;
import com.mtc.app.repository.IProductRepository;

@Service
public class ProductService {

	@Autowired
	private IProductRepository productrepository;	
	
	public List<Product> getAllProducts()
	{
		return productrepository.findAll();
	}
	
	public Product getProduct(int id)
	{
		return productrepository.getOne(id);
	}
	
	public Product addProduct(Product product)
	{
		return productrepository.saveAndFlush(product);
	}
	
	public void deleteProduct(int id)
	{
		productrepository.deleteById(id);
	}
	
}
