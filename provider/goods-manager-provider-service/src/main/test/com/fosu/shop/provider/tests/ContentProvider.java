package com.fosu.shop.provider.tests;


import com.fosu.shop.provider.api.ContentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentProvider {
    @Resource
    private ContentService contentService;
    @Test
    public  void delContentTest(){

        Integer i = 5;
        List<Integer> list = new ArrayList<>();
        list.add(i);
        contentService.delContent(list);
    }
    @Test
    public void test(){}
}
