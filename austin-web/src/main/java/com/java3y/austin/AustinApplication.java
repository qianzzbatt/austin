package com.java3y.austin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class AustinApplication {

    public static void main(String[] args) {
        SpringApplication.run(AustinApplication.class, args);
    }


}
