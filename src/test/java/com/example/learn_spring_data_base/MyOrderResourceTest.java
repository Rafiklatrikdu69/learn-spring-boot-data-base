package com.example.learn_spring_data_base;

import com.example.learn_spring_data_base.beans.MyOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MyOrderResourceTest {
    private static final String URLBASE = "http://localhost:8080/orders";

    @Test
    @Order(1)
    public void getLenghtMyOrder(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<MyOrder>> response =  restTemplate.exchange(URLBASE, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<MyOrder>>() {
                });
        List<MyOrder> myOrders = response.getBody();
        assertTrue((myOrders.size()) >= 2);
    }
}
