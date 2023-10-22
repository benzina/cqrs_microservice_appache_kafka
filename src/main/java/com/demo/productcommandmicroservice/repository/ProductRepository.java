package com.demo.productcommandmicroservice.repository;

import com.demo.productcommandmicroservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
