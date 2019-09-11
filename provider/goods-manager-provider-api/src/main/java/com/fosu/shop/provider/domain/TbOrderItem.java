package com.fosu.shop.provider.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_order_item")
public class TbOrderItem implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 商品id
     */
    @Column(name = "item_id")
    private Long itemId;

    /**
     * SPU_ID
     */
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 商品标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 商品单价
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 商品购买数量
     */
    @Column(name = "num")
    private Integer num;

    /**
     * 商品总金额
     */
    @Column(name = "total_fee")
    private BigDecimal totalFee;

    /**
     * 商品图片地址
     */
    @Column(name = "pic_path")
    private String picPath;

    @Column(name = "seller_id")
    private String sellerId;

    private static final long serialVersionUID = 1L;
}