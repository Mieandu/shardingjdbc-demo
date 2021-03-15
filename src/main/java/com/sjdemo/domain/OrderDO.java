package com.sjdemo.domain;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order")
public class OrderDO {

    @TableId
    private Long id;
    private Long userId;
    private Long orderId;
    private String remark;
    private Boolean shadow;

    public static void main(String[] args) {
        OrderDO orderDO = new OrderDO();
        orderDO.setId(1L);
    }

}
