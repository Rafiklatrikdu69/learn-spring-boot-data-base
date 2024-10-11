package com.example.learn_spring_data_base.ressources;

import com.example.learn_spring_data_base.beans.Product;
import com.example.learn_spring_data_base.repository.ProductRepository;
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
@RequestMapping(value = "/products")
public class ProductResource {
    private static final Logger logger =
            LoggerFactory.getLogger(ProductResource.class);
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        logger.info(" list products ");
        Iterable<Product> products = productRepository.findAll();
        return new
                ResponseEntity<List<Product>>(IteratorUtils.toList(products.iterator()),
                HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        logger.info(" add product ");
        Product result = productRepository.save(product);
        return new ResponseEntity<Product>(result, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        logger.info(" get product ");
        Product result = productRepository.findById(id).orElse(null);

        return new ResponseEntity<Product>(result, HttpStatus.OK);

    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        logger.info(" update product ");
        Product result = productRepository.findById(product.getId()).orElse(null);

        if (result != null) {
            result.setName(product.getName());
            result.setPrice(result.getPrice());
            productRepository.save(result);
        }
        return new ResponseEntity<Product>(result, HttpStatus.OK);

    }
}
