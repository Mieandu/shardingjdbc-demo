package com.sjdemo.service;

import com.sjdemo.domain.OrderDO;
import com.sjdemo.mapper.OrderMapper;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void createOrder(OrderDO orderDO){
        orderMapper.insert(orderDO);
    }
}
