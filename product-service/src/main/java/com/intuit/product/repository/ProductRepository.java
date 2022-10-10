package com.intuit.product.repository;

import com.intuit.product.repository.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

    Product findByCode(String code);
}
