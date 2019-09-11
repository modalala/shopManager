package com.fosu.shop.provider.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_goods_desc")
public class TbGoodsDesc implements Serializable {
    /**
     * SPU_ID
     */
    @Id
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 描述
     */
    @Column(name = "introduction")
    private String introduction;

    /**
     * 规格结果集，所有规格，包含isSelected
     */
    @Column(name = "specification_items")
    private String specificationItems;

    /**
     * 自定义属性（参数结果）
     */
    @Column(name = "custom_attribute_items")
    private String customAttributeItems;

    @Column(name = "item_images")
    private String itemImages;

    /**
     * 包装列表
     */
    @Column(name = "package_list")
    private String packageList;

    /**
     * 售后服务
     */
    @Column(name = "sale_service")
    private String saleService;

    private static final long serialVersionUID = 1L;
}