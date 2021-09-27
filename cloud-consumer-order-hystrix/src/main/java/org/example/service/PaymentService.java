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

    @GetMapping("/payment/")
    CommonResult getByCondition(@RequestParam(value = "search", required = false) String search);

    @GetMapping("/payment/timeout")
    CommonResult getByConditionTimeout(@RequestParam(value = "search", required = false) String search);

    @DeleteMapping("/payment/{id}")
    CommonResult delete(@PathVariable("id") Long id);
}
