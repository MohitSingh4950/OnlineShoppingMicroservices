package com.codeanthem.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeanthem.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
