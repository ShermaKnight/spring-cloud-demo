package org.example.controller;

import org.example.entities.Payment;
import org.example.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/order")
@SuppressWarnings("all")
public class OrderController {

    @Value("${service.payment}")
    private String paymentService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/")
    public CommonResult create(@RequestBody Payment payment) {
        String url = paymentService + "/payment/";
        return restTemplate.postForObject(url, payment, CommonResult.class);
    }

    @GetMapping("/{id}")
    public CommonResult getById(@PathVariable("id") Long id) {
        String url = paymentService + "/payment/" + id;
        return restTemplate.getForObject(url, CommonResult.class);
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        String url = paymentService + "/payment/" + id;
        ResponseEntity<CommonResult> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, CommonResult.class);
        if (Optional.ofNullable(responseEntity).isPresent()) {
            return responseEntity.getBody();
        }
        return new CommonResult(400, "failed");
    }
}
