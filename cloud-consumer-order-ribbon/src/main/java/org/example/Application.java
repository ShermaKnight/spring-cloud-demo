package org.example;

import org.example.annotion.IgnoreScan;
import org.example.config.LoadBalanceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "${service.payment}", configuration = LoadBalanceConfig.class)
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = IgnoreScan.class)})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
