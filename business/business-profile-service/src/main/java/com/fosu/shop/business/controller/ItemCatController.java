package com.fosu.shop.business.controller;

import com.fosu.shop.business.commons.ResponseResultMe;
import com.fosu.shop.business.dto.PageParam;
import com.fosu.shop.provider.api.ItemCatService;
import com.fosu.shop.provider.domain.TbItemCat;
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
@RequestMapping(value = "itemCat")
public class ItemCatController {

    @Reference(version = "1.0.0")
    private ItemCatService itemCatService;

    /**
     * 分类顶级有分页
     * @return
     */
    @PostMapping("getPageItemCat")
    @ApiOperation(value = "分类信息分页" )
    ResponseResultMe<PageInfo<TbItemCat>> getPageItemCat(@RequestBody PageParam pageParam){

        final PageInfo<TbItemCat> pageItemCat = itemCatService.getPageItemCat(pageParam.getPageNum(), pageParam.getPageSize());
        return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK,"查询分类分页成功",pageItemCat);
    }


    /**
     * 分类查询下级
     * @param parentId 用这个查
     * @return
     */
    @PostMapping("getLowerItemCat")
    @ApiOperation(value = "分类查询下级" )
    ResponseResultMe<List<TbItemCat>> getLowerItemCat(@RequestBody Integer parentId){

        final List<TbItemCat> lowerItemCat = itemCatService.getLowerItemCat(parentId);
        return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK,"查询分类分页成功",lowerItemCat);
    }
    /**
     * 新增分类
     * @param tbItemCat
     * @return
     */
    @PostMapping("postItemCat")
    @ApiOperation(value = "新增分类" )
    ResponseResultMe<Integer> postItemCat(@RequestBody TbItemCat tbItemCat){

        final Integer num = itemCatService.postItemCat(tbItemCat);
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
     * @param id
     * @return
     */
    @PostMapping("delItemCat")
    @ApiOperation(value = "批量删除" )
    ResponseResultMe<Integer> delItemCat (@RequestBody List<Integer> id){
        if(id.size() == 0){
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.BREAKING,"没有参数");
        }
        Integer num = itemCatService.delItemCat(id);

        if(num == id.size()) {
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.OK,"删除数据成功");
        }
        else
        {
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.FAIL,"删除数据失败");
        }
    }

    /**
     * 修改
     * @param tbItemCat
     * @return
     */
    @PostMapping("fixItemCat")
    @ApiOperation(value = "分类修改" )
    ResponseResultMe<Integer> fixItemCat(TbItemCat tbItemCat){
        Integer num = itemCatService.fixItemCat(tbItemCat);
        if(num != 0)
        {
            return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK,"修改成功");
        }
        return new ResponseResultMe<>(ResponseResultMe.CodeStatus.FAIL,"修改失败");
    }
}


