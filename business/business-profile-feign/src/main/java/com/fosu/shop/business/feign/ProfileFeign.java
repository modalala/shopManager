package com.fosu.shop.business.feign;

import com.fosu.shop.business.dto.BandParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 商品管理
 * @author Administrator
 */
@FeignClient(value = "business-profile", path = "profile")
public interface ProfileFeign {


    /**
     * 分页获取品牌信息
     * @param pageNum 页码
     * @param pageSize 个数
     * @return
     */
    @GetMapping("getBandPageInfo/{pageNum}/{pageSize}")
    String getBandInfo(@PathVariable String pageNum, @PathVariable String pageSize);

    /**
     * 获取品牌名字
     * @return
     */
    @GetMapping("getBandName")
    String getBandName();

    /**
     * 新增品牌
     * @param bandParam
     * @return
     */
    @PostMapping("postBand")
    String postBand(@RequestBody BandParam bandParam);


    /**
     * 删除品牌
     * @param bandId
     * @return
     */
    @PostMapping("delBand")
    String deleteBand(@RequestBody List<Integer> bandId) ;
}
