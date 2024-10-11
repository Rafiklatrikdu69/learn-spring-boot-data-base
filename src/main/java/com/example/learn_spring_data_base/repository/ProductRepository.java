package com.example.learn_spring_data_base.repository;

import com.example.learn_spring_data_base.beans.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
}
