package com.fosu.shop.provider.tests;


import com.fosu.shop.provider.api.GoodsBaseService;
import com.fosu.shop.provider.domain.TbBrand;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsManagerProvider {

    @Test
    public void test(){

    }
    @Resource
    private GoodsBaseService goodsBaseService;

    @Test
    public void BrandTest(){

        List<TbBrand> allBrand = goodsBaseService.getAllBrand();
        allBrand.forEach((e)->{
            System.out.println(e);
        });
    }


    /**
     * 分页模糊查询
     */
    @Test
    public void BrandLikeTest(){
        String name = "苹";
        PageInfo<TbBrand> likeBrand = goodsBaseService.getLikeBrand(1, 3, name);
        List<TbBrand> list = likeBrand.getList();
        list.forEach(System.out::println);
    }
    @Test
    public void BrandNameTest(){
        final List<TbBrand> brandName = goodsBaseService.getBrandName();
        brandName.forEach(System.out::println);
    }


    @Test
    public void BrandDelTest(){
        Integer i = 26;
        List<Integer> list = new ArrayList<>();
        list.add(i);
        goodsBaseService.delBrand(list);
    }
}
