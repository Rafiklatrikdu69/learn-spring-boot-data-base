package com.example.learn_spring_data_base;

import com.example.learn_spring_data_base.beans.Customer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerResourceTest {
    private final String URLBASE ="http://localhost:8080/customers";

    @Test
    @Order(1)
    public void customersLength(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Customer>> response =  restTemplate.exchange(URLBASE, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Customer>>() {
                });
        List<Customer> customers = response.getBody();
        assertTrue((customers.size()) >= 2);
    }

    @Test
    @Order(2)
    public void getCustomerById() {
        RestTemplate restTemplate = new RestTemplate();
        int customerId = 1;
        String urlCustomerId = String.valueOf(customerId);
        ResponseEntity<Customer> response =
                restTemplate.exchange(URLBASE.concat("/" + urlCustomerId),
                        HttpMethod.GET, null, new
                                ParameterizedTypeReference<Customer>() {
                                });
        Customer customer = response.getBody();
        assertNotNull(customer);
    }

    @Test
    @Order(4)
    public void updateCustomer() {
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("customer 1");
        customer.setLastName("lastname customer 1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(customer,
                headers);
        ResponseEntity<Customer> response = restTemplate.exchange(URLBASE,
                HttpMethod.PUT, requestEntity, Customer.class);
        Customer result = response.getBody();
        assertNotNull(result);
        assertEquals(result.getId(), customer.getId());
        assertEquals(result.getLastName(), customer.getLastName());
    }
}
