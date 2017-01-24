package com.ibm.microservices.interconnect17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MenuApplicationUI {

    public static void main(String[] args) {
        SpringApplication.run(MenuApplicationUI.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
      return new RestTemplate();
    }

}
