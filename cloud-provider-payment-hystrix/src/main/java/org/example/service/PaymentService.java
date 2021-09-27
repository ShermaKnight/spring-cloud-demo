package org.example.service;

import org.example.entities.Payment;

import java.util.List;

public interface PaymentService {

    int create(Payment payment);

    Payment getById(Long id);

    List<Payment> getByCondition(String search);

    List<Payment> getByConditionTimeout(String search);

    List<Payment> getByConditionCircuit(String search);

    List<Payment> getByConditionStatic(String search);

    int delete(Long id);
}
