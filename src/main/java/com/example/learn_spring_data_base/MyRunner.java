package com.example.learn_spring_data_base;

import com.example.learn_spring_data_base.beans.Customer;
import com.example.learn_spring_data_base.beans.MyOrder;
import com.example.learn_spring_data_base.beans.Product;
import com.example.learn_spring_data_base.repository.CustomerRepository;
import com.example.learn_spring_data_base.repository.OrderRepository;
import com.example.learn_spring_data_base.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        logger.info("StartApplication...");
        Customer custom1 = customerRepository.save(new Customer("Michel", "Dupont"));
        Customer custom2 = customerRepository.save(new Customer("Marcel", "Martin"));
        Customer custom3 = customerRepository.save(new Customer("John", "toto"));
        logger.info("\nfindAll()");
        customerRepository.findAll().forEach(x -> logger.info(x.toString()));
        logger.info("\nfindById(1L)");
        customerRepository.findById(1l).ifPresent(x -> logger.info(x.toString()));
        logger.info("\nfindByName('Martin')");
        customerRepository.findByLastName("Martin").forEach(x -> logger.info(x.toString()));
        Product product1 = productRepository.save(new Product("Intel Processor", 234));
        Product product2 = productRepository.save(new Product("AMD Processor", 165));
        Product product3 = productRepository.save(new Product("SSD", 95));
        logger.info("\nfindAll()");
        productRepository.findAll().forEach(x -> logger.info(x.toString()));
        logger.info("\nfindById(1L)");

        productRepository.findById(1l).ifPresent(x -> logger.info(x.toString()));
        logger.info("\nfindByName('Intel Processor')");
        customerRepository.findByLastName("Intel Processor").forEach(x ->
                logger.info(x.toString()));
        MyOrder myOrder1 = new MyOrder(custom1);
        MyOrder myOrder2 = new MyOrder(custom2);
        MyOrder myOrder3 = new MyOrder(custom3);
        MyOrder myOrder4 = new MyOrder(custom3);
        myOrder1.getProducts().add(product1);
        myOrder2.getProducts().add(product2);
        myOrder3.getProducts().add(product3);
        myOrder4.getProducts().add(product1);
        myOrder4.getProducts().add(product3);
        orderRepository.save(myOrder1);
        orderRepository.save(myOrder2);
        orderRepository.save(myOrder3);
        orderRepository.save(myOrder4);
        logger.info("\nfindAll()");
        orderRepository.findAll().forEach(x -> logger.info(x.toString()));
        logger.info("\nfindById(1L)");
        orderRepository.findById(1l).ifPresent(x -> logger.info(x.toString()));
    }
}