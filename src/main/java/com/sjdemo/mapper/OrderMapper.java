package com.sjdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjdemo.domain.OrderBO;
import com.sjdemo.domain.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper extends BaseMapper<OrderDO> {

    @Select("select user_id,count(order_id) as orderCount from t_order group by user_id order by orderCount")
    List<OrderBO> selectOrderCount();
}

