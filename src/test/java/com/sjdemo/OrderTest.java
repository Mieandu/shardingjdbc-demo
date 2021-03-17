package com.sjdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sjdemo.domain.OrderBO;
import com.sjdemo.domain.OrderDO;
import com.sjdemo.domain.ShadowOrder;
import com.sjdemo.mapper.OrderMapper;
import com.sjdemo.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderService orderService;

    @Test
    //@Transactional
    //@ShardingTransactionType(TransactionType.XA)
    public void createTest() {
        for(long i =10; i<=20 ; i++){
            ShadowOrder orderDO = new ShadowOrder();
            orderDO.setUserId(i);
            orderDO.setOrderId(i);
            orderDO.setRemark("remark:" + i );
            orderDO.setShadow(true);
            orderService.createOrder(orderDO);
          /*  if(i % 2 ==1){
                throw new RuntimeException();
            }*/
            System.out.println("创建订单：" + i);
        }
    }

    @Test
    public void selectAll(){
        QueryWrapper<OrderDO> wrapper = new QueryWrapper<>();
        wrapper.eq("shadow", true);
        List<OrderDO> orderDOS = orderMapper.selectList(wrapper);
        orderDOS.forEach(orderDO -> System.out.println(orderDO.toString()));
    }

    @Test
    public void selectByUserId(){
        System.out.println("根据userId查询订单");
        QueryWrapper<OrderDO> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", 11);
        List<OrderDO> orderDOS = orderMapper.selectList(wrapper);
        printResult(orderDOS);
    }

    @Test
    public void orderBy(){
        System.out.println("根据Id排序");
        QueryWrapper<OrderDO> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<OrderDO> orderDOS = orderMapper.selectList(wrapper);
        printResult(orderDOS);
    }


    @Test
    public void page(){
        System.out.println("分页查询");
        QueryWrapper<OrderDO> wrapper = new QueryWrapper<>();
        wrapper.last("limit 5,5");
        wrapper.orderByDesc("id");
        List<OrderDO> orderDOS = orderMapper.selectList(wrapper);
        printResult(orderDOS);
    }

    @Test
    public void groupBy(){
        List<OrderBO> orderBOS = orderMapper.selectOrderCount();
        orderBOS.forEach(orderDO -> System.out.println(orderDO.toString()));
    }


    public static void printResult(List<OrderDO> orderDOS){
        orderDOS.forEach(orderDO -> System.out.println(orderDO.toString()));
    }

}
