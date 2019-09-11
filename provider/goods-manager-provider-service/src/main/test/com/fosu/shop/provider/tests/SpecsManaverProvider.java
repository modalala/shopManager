package com.fosu.shop.provider.tests;

import com.fosu.shop.provider.api.SpecsService;
import com.fosu.shop.provider.domain.TbSpecification;
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
public class SpecsManaverProvider {

    @Resource
    private SpecsService specsService;


    @Test
    public void test(){}

    @Test
    public void SpecsLikeTest(){

        String name = "网";
        PageInfo<TbSpecification> likeSpecs = specsService.getLikeSpecs(1, 3, name);

        List<TbSpecification> list = likeSpecs.getList();
        list.forEach((e)->{
            System.out.println(e);
        });

    }
    @Test
    public void deleteSpecs(){

        List<Integer> id = new ArrayList<>();
        id.add(34);
        id.add(35);
        Integer integer = specsService.delSpecs(id);
        System.out.println(integer);
    }
}
