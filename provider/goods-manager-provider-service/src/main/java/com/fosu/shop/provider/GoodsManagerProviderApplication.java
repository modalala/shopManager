package com.fosu.shop.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 商品管理生产者
 * <p>
 * Description
 * </p>
 * @author miki
 * @version v1.0.0
 * @date 2019/09/7
 * @see com.fosu.shop
 *
 */
@SpringBootApplication
@MapperScan(basePackages = "com.fosu.shop.provider.mapper")
public class GoodsManagerProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsManagerProviderApplication.class,args);
    }
}
