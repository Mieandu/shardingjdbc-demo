package com.sjdemo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderDetailBO {
    private Long userId;
    private Long orderId;
    private String remark;
    private String itemId;
    private LocalDateTime createdTime;
}
