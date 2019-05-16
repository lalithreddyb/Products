package com.mtc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtc.app.product.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

}
