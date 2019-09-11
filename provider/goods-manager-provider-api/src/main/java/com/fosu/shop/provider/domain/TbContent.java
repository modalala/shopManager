package com.fosu.shop.provider.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_content")
public class TbContent implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 内容类目ID
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 内容标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 链接
     */
    @Column(name = "url")
    private String url;

    /**
     * 图片绝对路径
     */
    @Column(name = "pic")
    private String pic;

    /**
     * 状态
     */
    @Column(name = "`status`")
    private String status;

    /**
     * 排序
     */
    @Column(name = "sort_order")
    private Integer sortOrder;

    private static final long serialVersionUID = 1L;
}