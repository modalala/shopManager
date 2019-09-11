package com.fosu.shop.provider.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_pay_log")
public class TbPayLog implements Serializable {
    /**
     * 支付订单号
     */
    @Id
    @Column(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 支付完成时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 支付金额（分）
     */
    @Column(name = "total_fee")
    private Long totalFee;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 交易号码
     */
    @Column(name = "transaction_id")
    private String transactionId;

    /**
     * 交易状态
     */
    @Column(name = "trade_state")
    private String tradeState;

    /**
     * 订单编号列表
     */
    @Column(name = "order_list")
    private String orderList;

    /**
     * 支付类型
     */
    @Column(name = "pay_type")
    private String payType;

    private static final long serialVersionUID = 1L;
}