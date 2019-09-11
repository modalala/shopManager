package com.fosu.shop.business.controller;


import com.fosu.shop.business.commons.ResponseResultMe;

import com.fosu.shop.business.dto.PageParam;
import com.fosu.shop.business.dto.BandParam;
import com.fosu.shop.provider.api.GoodsBaseService;
import com.fosu.shop.provider.domain.TbBrand;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品管理
 * @author Administrator
 */
@RestController
@RequestMapping(value = "profile")
public class ProfileController {

    @Reference(version = "1.0.0")
    private GoodsBaseService goodsBaseService;

    /**
     * 分页获取品牌信息
     *
     * @param bandPage 分页信息
     * @return
     */
    @PostMapping("getBandPageInfo")
    @ApiOperation(value = "品牌信息分页加模糊查询" )
    ResponseResultMe<PageInfo<TbBrand>> getBandInfo(@RequestBody PageParam bandPage) {

        String selectName = bandPage.getSelectName();
        Integer pageNum = bandPage.getPageNum();
        Integer pageSize = bandPage.getPageSize();
        //
        if(selectName == null || selectName == "" || selectName.isEmpty()){
            final PageInfo<TbBrand> allPbrand = goodsBaseService.getAllPageBrand(pageNum, pageSize);
            return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK, "获取品牌", allPbrand);
        }

        final PageInfo<TbBrand> likeBrand = goodsBaseService.getLikeBrand(pageNum, pageSize, selectName);
        return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK, "获取品牌", likeBrand);
    }

    @GetMapping("getBandName")
    @ApiOperation(value = "获取品牌名字" )
    ResponseResultMe<List<TbBrand>> getBandName(){

        final List<TbBrand> brandName = goodsBaseService.getBrandName();
        return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK,"获取品牌",brandName);
    }
    /**
     * 增加
     * @param bandParam
     * @return
     */
    @PostMapping("postBand")
    @ApiOperation(value = "品牌信息新增" )
    ResponseResultMe<Integer> postBand(@RequestBody BandParam bandParam) {
        TbBrand tbBrand = new TbBrand();
        tbBrand.setName(bandParam.getName());
        tbBrand.setFirstChar(bandParam.getFirstChar());

        final Integer num = goodsBaseService.postBrand(tbBrand);
        if(num != 0) {
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.OK,"插入数据成功");
        }
        else
        {
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.FAIL,"插入数据失败");
        }
    }

    /**
     * 批量删除
     * @param bandId
     * @return
     */
   @PostMapping("delBand")
   @ApiOperation(value = "品牌信息批量删除" ,notes = "品牌信息批量删除")
    ResponseResultMe<Integer> deleteBand(@RequestBody List<Integer> bandId) {
       if(bandId.size() == 0){
           return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.BREAKING,"没有参数");
       }
        Integer num = goodsBaseService.delBrand(bandId);

        if(num == bandId.size()) {
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.OK,"删除数据成功");
        }
        else
        {
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.FAIL,"删除数据失败");
        }
    }

    @PostMapping("fixBand")
    @ApiOperation(value = "品牌信息修改" ,notes = "品牌信息修改")
    ResponseResultMe<Integer> fixBand(@RequestBody TbBrand tbBrand){

        Integer num = goodsBaseService.fixBrand(tbBrand);
        if(num != 0)
        {
            return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK,"修改成功");
        }
        return new ResponseResultMe<>(ResponseResultMe.CodeStatus.FAIL,"修改失败");
    }
}
