package com.fosu.shop.business;

import com.fosu.shop.provider.api.GoodsBaseService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessProfileTest {

    @Reference(version = "1.0.0")
    private GoodsBaseService goodsBaseService;
    @Test
    public void BandTest(){
        goodsBaseService.getAllBrand();
    }

}
