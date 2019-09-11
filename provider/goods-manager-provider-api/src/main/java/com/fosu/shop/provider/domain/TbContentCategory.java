package com.fosu.shop.provider.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_content_category")
public class TbContentCategory implements Serializable {
    /**
     * 类目ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 分类名称
     */
    @Column(name = "`name`")
    private String name;

    private static final long serialVersionUID = 1L;
}