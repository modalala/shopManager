package com.fosu.shop.provider.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_item_cat")
public class TbItemCat implements Serializable {
    /**
     * 类目ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 父类目ID=0时，代表的是一级的类目
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 类目名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 类型id
     */
    @Column(name = "type_id")
    private Long typeId;

    private static final long serialVersionUID = 1L;
}