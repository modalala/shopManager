package com.fosu.shop.provider.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_cities")
public class TbCities implements Serializable {
    /**
     * 唯一ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 城市ID
     */
    @Column(name = "cityid")
    private String cityid;

    /**
     * 城市名称
     */
    @Column(name = "city")
    private String city;

    /**
     * 省份ID
     */
    @Column(name = "provinceid")
    private String provinceid;

    private static final long serialVersionUID = 1L;
}