package org.example.service;

import org.example.entities.Payment;
import org.example.model.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("${service.payment}")
public interface PaymentService {

    @PostMapping("/payment/")
    CommonResult create(@RequestBody Payment payment);

    @GetMapping("/payment/{id}")
    CommonResult getById(@PathVariable("id") Long id);

    @DeleteMapping("/payment/{id}")
    CommonResult delete(@PathVariable("id") Long id);
}
