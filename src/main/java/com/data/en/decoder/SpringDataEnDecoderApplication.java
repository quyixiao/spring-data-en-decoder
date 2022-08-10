package com.data.en.decoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.data.en.decoder"})
@EnableDiscoveryClient
@EnableCaching
@ServletComponentScan
public class SpringDataEnDecoderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataEnDecoderApplication.class, args);
    }

}
