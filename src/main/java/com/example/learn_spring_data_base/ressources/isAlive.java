package com.example.learn_spring_data_base.ressources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/monitoring")
public class isAlive {
    private static final Logger logger = LoggerFactory.getLogger(isAlive.class);

    @GetMapping(value = "/isAlive")
    public ResponseEntity<String> isAlive(){
            logger.info("is Alive");
        return new ResponseEntity<String>("is Alive", HttpStatus.OK);
    }
}
