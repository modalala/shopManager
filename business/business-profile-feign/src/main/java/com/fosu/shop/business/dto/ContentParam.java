package com.fosu.shop.business.dto;

import lombok.Data;

@Data
public class ContentParam {
    private Long categoryId;
    private String title;
    private String url;
    private String status;
    private Integer sortOrder;
}
