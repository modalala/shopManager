package com.fosu.shop.business.dto;

import lombok.Data;

@Data
public class PageParam {
    /**
     * {pageNum}/{pageSize}/{selectName}
     */
    Integer pageNum;
    Integer pageSize;
    String selectName;
}
