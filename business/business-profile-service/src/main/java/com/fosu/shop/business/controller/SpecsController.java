package com.fosu.shop.business.controller;

import com.fosu.shop.business.commons.ResponseResultMe;
import com.fosu.shop.business.dto.PageParam;
import com.fosu.shop.provider.api.SpecsService;
import com.fosu.shop.provider.domain.TbSpecification;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  规格管理
 * @author Administrator
 */
@RestController
@RequestMapping(value = "Specs")
public class SpecsController {

    @Reference(version = "1.0.0")
    private SpecsService specsService;

    /**
     * 分页获取品牌信息
     *
     * @param specsPage 分页信息
     * @return
     */
    @PostMapping("getSpecsPageInfo")
    @ApiOperation(value = "规格信息分页加模糊查询" )
    ResponseResultMe<PageInfo<TbSpecification>> getSpecsInfo(@RequestBody PageParam specsPage)
    {
        final Integer pageNum = specsPage.getPageNum();
        final Integer pageSize = specsPage.getPageSize();
        final String selectName = specsPage.getSelectName();

        //搜索为空时 已经在mybatis中解决
        PageInfo<TbSpecification> likeSpecs = specsService.getLikeSpecs(pageNum, pageSize, selectName);
        return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK, "获取品牌", likeSpecs);
    }

    /**
     * 获取规格名字
     * @return
     */
    @GetMapping("getSpecsName")
    @ApiOperation(value = "获取规格名字" )
    ResponseResultMe<List<TbSpecification>> getSpecsName(){
        List<TbSpecification> specsName = specsService.getSpecsName();
        return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK,"获取规格",specsName);
    }

    /**
     * 规格信息新增
     * @param tbSpecification
     * @return
     */
    @PostMapping("postSpecs")
    @ApiOperation(value = "规格信息新增" )
    ResponseResultMe<Integer> postSpecs(@RequestBody TbSpecification tbSpecification){

        Integer integer = specsService.postLinkSpecs(tbSpecification);
        if(integer != 0)
        {
            return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK,"插入数据成功",integer);
        }
        else{
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.FAIL,"插入数据失败");
        }
    }

    @PostMapping("delSpecs")
    @ApiOperation(value = "规格信息批量删除" ,notes = "规格信息批量删除")
    ResponseResultMe<Integer> deleteSpecs(@RequestBody List<Integer> specsId){
        if(specsId.size() == 0)
        {
            return new ResponseResultMe<>(ResponseResultMe.CodeStatus.BREAKING,"没有参数");
        }
        Integer integer = specsService.delSpecs(specsId);

        if(integer == specsId.size()){
            return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK,"删除成功",integer);
        }
        else{
            return new ResponseResultMe<>(ResponseResultMe.CodeStatus.FAIL,"删除失败",integer);
        }
    }

    @PostMapping("fixSpecs")
    @ApiOperation(value = "规格信息修改" ,notes = "规格信息修改")
    ResponseResultMe<Integer> fixBand(@RequestBody TbSpecification specification ){
        Integer num = specsService.fixSpecs(specification);
        if(num != 0)
        {
            return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK,"修改成功");
        }
        return new ResponseResultMe<>(ResponseResultMe.CodeStatus.FAIL,"修改失败");
    }
}
