package org.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entities.Payment;

import java.util.List;

@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment getById(@Param("id") Long id);

    List<Payment> getByCondition(@Param("search") String search);

    int delete(@Param("id") Long id);
}
