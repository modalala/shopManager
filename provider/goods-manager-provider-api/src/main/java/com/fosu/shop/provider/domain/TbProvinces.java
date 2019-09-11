package com.fosu.shop.provider.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_provinces")
public class TbProvinces implements Serializable {
    /**
     * 唯一ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 省份ID
     */
    @Column(name = "provinceid")
    private String provinceid;

    /**
     * 省份名称
     */
    @Column(name = "province")
    private String province;

    private static final long serialVersionUID = 1L;
}