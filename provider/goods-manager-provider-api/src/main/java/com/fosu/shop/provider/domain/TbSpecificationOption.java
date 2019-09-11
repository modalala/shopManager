package com.fosu.shop.provider.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_specification_option")
public class TbSpecificationOption implements Serializable {
    /**
     * 规格项ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 规格项名称
     */
    @Column(name = "option_name")
    private String optionName;

    /**
     * 规格ID
     */
    @Column(name = "spec_id")
    private Long specId;

    /**
     * 排序值
     */
    @Column(name = "orders")
    private Integer orders;

    private static final long serialVersionUID = 1L;
}