package com.fosu.shop.business.controller;

import com.fosu.shop.business.commons.ResponseResultMe;
import com.fosu.shop.business.dto.PageParam;
import com.fosu.shop.provider.api.TypeService;
import com.fosu.shop.provider.domain.TbBrand;
import com.fosu.shop.provider.domain.TbTypeTemplate;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分类管理
 * @author Administrator
 */
@RestController
@RequestMapping(value = "type")
public class TypeController {

    @Reference(version = "1.0.0")
    private TypeService typeService;

    /**
     * 分页获取品牌信息
     *
     * @param bandPage 分页信息
     * @return
     */
    @PostMapping("getBandPageInfo")
    @ApiOperation(value = "模板模糊分页" )
    ResponseResultMe<PageInfo<TbTypeTemplate>> getBandInfo(@RequestBody PageParam bandPage) {

        String selectName = bandPage.getSelectName();
        Integer pageNum = bandPage.getPageNum();
        Integer pageSize = bandPage.getPageSize();
        //
        final PageInfo<TbTypeTemplate> pageInfo = typeService.getLikeType(pageNum, pageSize, selectName);

        return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK, "获取品牌", pageInfo);
    }

    @PostMapping("fixType")
    @ApiOperation(value = "修改模板" )
    ResponseResultMe<Integer> fixType(@RequestBody TbTypeTemplate tbTypeTemplate){
        Integer num = typeService.fixType(tbTypeTemplate);
        if(num != 0)
        {
            return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK,"修改成功");
        }
        return new ResponseResultMe<>(ResponseResultMe.CodeStatus.FAIL,"修改失败");
    }
    /**
     * 插入模板
     * @param tbTypeTemplate
     * @return
     */
    @PostMapping("postType")
    @ApiOperation(value = "插入模板" )
    ResponseResultMe<Integer> postType(TbTypeTemplate tbTypeTemplate){

        final Integer num = typeService.postType(tbTypeTemplate);
        if(num != 0) {
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.OK,"插入数据成功");
        }
        else
        {
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.FAIL,"插入数据失败");
        }
    }
    /**
     * 删除模板
     * @param typeId
     * @return
     */
    @PostMapping("delType")
    @ApiOperation(value = "删除模板" )
    ResponseResultMe<Integer> delType(List<Integer> typeId){
        if(typeId.size() == 0){
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.BREAKING,"没有参数");
        }
        Integer num = typeService.delType(typeId);

        if(num == typeId.size()) {
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.OK,"删除数据成功");
        }
        else
        {
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.FAIL,"删除数据失败");
        }
    }
}
