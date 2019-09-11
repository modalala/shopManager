package com.fosu.shop.provider.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_seckill_order")
public class TbSeckillOrder implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 秒杀商品ID
     */
    @Column(name = "seckill_id")
    private Long seckillId;

    /**
     * 支付金额
     */
    @Column(name = "money")
    private BigDecimal money;

    /**
     * 用户
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 商家
     */
    @Column(name = "seller_id")
    private String sellerId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 状态
     */
    @Column(name = "`status`")
    private String status;

    /**
     * 收货人地址
     */
    @Column(name = "receiver_address")
    private String receiverAddress;

    /**
     * 收货人电话
     */
    @Column(name = "receiver_mobile")
    private String receiverMobile;

    /**
     * 收货人
     */
    @Column(name = "receiver")
    private String receiver;

    /**
     * 交易流水
     */
    @Column(name = "transaction_id")
    private String transactionId;

    private static final long serialVersionUID = 1L;
}