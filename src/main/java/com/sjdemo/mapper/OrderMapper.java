package com.sjdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjdemo.domain.OrderBO;
import com.sjdemo.domain.OrderDO;
import com.sjdemo.domain.OrderDetailBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper extends BaseMapper<OrderDO> {

    @Select("select user_id,count(order_id) as orderCount from t_order group by user_id order by orderCount")
    List<OrderBO> selectOrderCount();

    @Select("select o.order_id,o.remark,o.user_id,o.created_time,oi.item_id from t_order o left join t_order_item oi on oi.order_id = o.order_id where o.order_id = #{order_id}")
    List<OrderDetailBO> selectOrderDetail(Long orderId);
}

