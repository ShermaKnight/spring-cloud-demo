package org.example.controller;

import org.example.entities.Payment;
import org.example.model.CommonResult;
import org.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    public CommonResult create(@RequestBody Payment payment) {
        return paymentService.create(payment);
    }

    @GetMapping("/{id}")
    public CommonResult getById(@PathVariable("id") Long id) {
        return paymentService.getById(id);
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        return paymentService.delete(id);
    }
}
