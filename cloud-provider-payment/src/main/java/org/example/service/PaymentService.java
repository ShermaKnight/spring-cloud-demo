package org.example.service;

import org.apache.ibatis.annotations.Param;
import org.example.entities.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment getById(Long id);

    int delete(Long id);
}
