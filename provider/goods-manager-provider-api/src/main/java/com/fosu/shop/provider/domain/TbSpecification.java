package com.fosu.shop.provider.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_specification")
public class TbSpecification implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY ,generator = "JDBC")
    private Long id;

    /**
     * 名称
     */
    @Column(name = "spec_name")
    private String specName;

    /**
     * 多表连接
     */
    private List<TbSpecificationOption> tbSpecificationOptions;

    private static final long serialVersionUID = 1L;
}