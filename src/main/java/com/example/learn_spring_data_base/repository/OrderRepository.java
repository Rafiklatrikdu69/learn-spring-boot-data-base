package com.example.learn_spring_data_base.repository;

import com.example.learn_spring_data_base.beans.MyOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<MyOrder,Long> {

}
