package com.sjdemo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order_item")
public class OrderItemDO {
    @TableId
    private Long id;
    private Long userId;
    private Long itemId;
    private Long orderId;
    private LocalDateTime createdTime;

}
