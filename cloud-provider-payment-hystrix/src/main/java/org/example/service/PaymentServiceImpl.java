package org.example.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.example.dao.PaymentDao;
import org.example.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@SuppressWarnings("all")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    private final static List<Payment> payments = Stream.of(
            new Payment(0l, "0")
    ).collect(Collectors.toList());

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getById(Long id) {
        return paymentDao.getById(id);
    }

    @Override
    public List<Payment> getByCondition(String search) {
        return paymentDao.getByCondition(search);
    }

    @Override
    @SneakyThrows
    @HystrixCommand(
            fallbackMethod = "getByConditionStatic",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "100")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"),
                    @HystrixProperty(name = "maxQueueSize", value = "-1"),
            },
            ignoreExceptions = {NoSuchMethodException.class}
    )
    public List<Payment> getByConditionTimeout(String search) {
        Thread.sleep(5000);
        return paymentDao.getByCondition(search);
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "getByConditionStatic",
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            },
            ignoreExceptions = {NoSuchMethodException.class}
    )
    public List<Payment> getByConditionCircuit(String search) {
        if (StringUtils.isNotEmpty(search)) {
            return paymentDao.getByCondition(search);
        }
        throw new RuntimeException("search failed");
    }

    public List<Payment> getByConditionStatic(String search) {
        return payments;
    }

    @Override
    public int delete(Long id) {
        return paymentDao.delete(id);
    }
}
