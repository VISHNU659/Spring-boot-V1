package com.example.demo;

import com.example.demo.DTO.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo1ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void customerExistenceValidationTest() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId();
    }

}
