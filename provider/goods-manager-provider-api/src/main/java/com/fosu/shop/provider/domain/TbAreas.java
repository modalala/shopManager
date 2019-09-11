package com.fosu.shop.provider.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_areas")
public class TbAreas implements Serializable {
    /**
     * 唯一ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 区域ID
     */
    @Column(name = "areaid")
    private String areaid;

    /**
     * 区域名称
     */
    @Column(name = "area")
    private String area;

    /**
     * 城市ID
     */
    @Column(name = "cityid")
    private String cityid;

    private static final long serialVersionUID = 1L;
}