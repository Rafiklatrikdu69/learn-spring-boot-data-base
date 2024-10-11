package com.example.learn_spring_data_base.services;

import com.example.learn_spring_data_base.beans.Customer;
import com.example.learn_spring_data_base.repository.CustomerRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private  CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        Iterable<Customer> customers =  customerRepository.findAll();
        return IteratorUtils.toList(customers.iterator());
    }
    public Customer findCustomerById(Long id){
        Customer customer =  customerRepository.findById(id).orElse(null);
        return  customer;
    }

    public Customer addCustomer(Customer customer){
        customerRepository.save(customer);
        return customer;
    }
    public void deleteById(Long id){
        customerRepository.deleteById(id);
    }

}
