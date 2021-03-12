package com.sjdemo.service;

import com.sjdemo.domain.OrderDO;
import com.sjdemo.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    public void createOrder(OrderDO orderDO){
        orderMapper.insert(orderDO);
    }
}
