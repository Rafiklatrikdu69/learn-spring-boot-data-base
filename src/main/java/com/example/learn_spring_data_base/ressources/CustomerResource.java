package com.example.learn_spring_data_base.ressources;

import com.example.learn_spring_data_base.beans.Customer;
import com.example.learn_spring_data_base.services.CustomerService;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {
    private static final Logger logger = LoggerFactory.getLogger(CustomerResource.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        logger.info("List customers");
       List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        logger.info("Add customer");
        Customer result = customerService.addCustomer(customer);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        logger.info("Get customer by ID");
        Customer result = customerService.findCustomerById(id);

        if (result==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        logger.info("Update customer");
        Customer existingCustomerOpt = customerService.findCustomerById(customer.getId());

        if (existingCustomerOpt==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Customer existingCustomer = existingCustomerOpt;
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());

        customerService.addCustomer(existingCustomer);
        return new ResponseEntity<>(existingCustomer, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        logger.info("Delete customer");
        customerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
