package com.fosu.shop.provider.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_item")
public class TbItem implements Serializable {
    /**
     * 商品id，同时也是商品编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 商品标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 商品卖点
     */
    @Column(name = "sell_point")
    private String sellPoint;

    /**
     * 商品价格，单位为：元
     */
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock_count")
    private Integer stockCount;

    /**
     * 库存数量
     */
    @Column(name = "num")
    private Integer num;

    /**
     * 商品条形码
     */
    @Column(name = "barcode")
    private String barcode;

    /**
     * 商品图片
     */
    @Column(name = "image")
    private String image;

    /**
     * 所属类目，叶子类目
     */
    @Column(name = "categoryId")
    private Long categoryid;

    /**
     * 商品状态，1-正常，2-下架，3-删除
     */
    @Column(name = "`status`")
    private String status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "item_sn")
    private String itemSn;

    @Column(name = "cost_pirce")
    private BigDecimal costPirce;

    @Column(name = "market_price")
    private BigDecimal marketPrice;

    @Column(name = "is_default")
    private String isDefault;

    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "seller_id")
    private String sellerId;

    @Column(name = "cart_thumbnail")
    private String cartThumbnail;

    @Column(name = "category")
    private String category;

    @Column(name = "brand")
    private String brand;

    @Column(name = "spec")
    private String spec;

    @Column(name = "seller")
    private String seller;

    private static final long serialVersionUID = 1L;
}