package com.fosu.shop.provider.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_brand")
public class TbBrand implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 品牌名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 品牌首字母
     */
    @Column(name = "first_char")
    private String firstChar;

    private static final long serialVersionUID = 1L;
}