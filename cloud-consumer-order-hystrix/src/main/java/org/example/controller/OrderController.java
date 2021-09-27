package org.example.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    @GetMapping("/")
    public CommonResult getByCondition(@RequestParam(value = "search", required = false) String search) {
        return paymentService.getByCondition(search);
    }

    @GetMapping("/timeout")
    @HystrixCommand(
            fallbackMethod = "fallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "10")
            },
            ignoreExceptions = {NoSuchMethodException.class}
    )
    public CommonResult getByConditionTimeout(@RequestParam(value = "search", required = false) String search) {
        return paymentService.getByCondition(search);
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        return paymentService.delete(id);
    }

    public CommonResult fallback(String search) {
        return new CommonResult(500, "当前服务过载，请稍后再试");
    }
}
