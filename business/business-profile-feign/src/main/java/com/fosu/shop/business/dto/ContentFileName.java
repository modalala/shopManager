package com.fosu.shop.business.dto;

import lombok.Data;

/**
 * 广告名字
 * @author Administrator
 */
@Data
public class ContentFileName {
    //文件原始名字
    private String originalFilename;
    //文件流
    private byte[] bytes;
    //文件大小
    private long size;
}
