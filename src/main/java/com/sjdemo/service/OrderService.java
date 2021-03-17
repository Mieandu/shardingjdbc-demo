package com.sjdemo.service;

import com.sjdemo.domain.ShadowOrder;
import com.sjdemo.mapper.OrderMapper;
import com.sjdemo.mapper.ShadowOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ShadowOrderMapper shadowOrderMapper;

    //@Transactional
   // @ShardingTransactionType(TransactionType.XA)
    public void createOrder(ShadowOrder orderDO){
        shadowOrderMapper.insert(orderDO);
    }
}
