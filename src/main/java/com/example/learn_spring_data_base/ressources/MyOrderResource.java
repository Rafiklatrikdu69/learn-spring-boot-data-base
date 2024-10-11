package com.example.learn_spring_data_base.ressources;

import com.example.learn_spring_data_base.beans.MyOrder;
import com.example.learn_spring_data_base.repository.OrderRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class MyOrderResource {
    private static final Logger logger = LoggerFactory.getLogger(MyOrderResource.class);

    @Autowired
    private OrderRepository myOrderRepository;

    @GetMapping
    public ResponseEntity<List<MyOrder>> getOrders() {
        logger.info("List orders");
        Iterable<MyOrder> orders = myOrderRepository.findAll();
        return new ResponseEntity<>(IteratorUtils.toList(orders.iterator()), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MyOrder> addOrder(@RequestBody MyOrder myOrder) {
        logger.info("Add order");
        MyOrder result = myOrderRepository.save(myOrder);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<MyOrder> getOrderById(@PathVariable("id") Long id) {
        logger.info("Get order by ID");
        MyOrder result = myOrderRepository.findById(id).orElse(null);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MyOrder> updateOrder(@RequestBody MyOrder myOrder) {
        logger.info("Update order");
        MyOrder existingOrder = myOrderRepository.findById(myOrder.getId()).orElse(null);
        if (existingOrder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingOrder.setCustomer(myOrder.getCustomer());
        existingOrder.setProducts(myOrder.getProducts());
        myOrderRepository.save(existingOrder);
        return new ResponseEntity<>(existingOrder, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        logger.info("Delete order");
        myOrderRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
