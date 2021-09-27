package org.example.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.example.annotion.IgnoreScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@IgnoreScan
public class LoadBalanceConfig {

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
