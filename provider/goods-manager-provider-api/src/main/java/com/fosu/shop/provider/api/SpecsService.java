package com.fosu.shop.provider.api;

import com.fosu.shop.provider.domain.TbBrand;
import com.fosu.shop.provider.domain.TbSpecification;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 规格管理
 * @author Administrator
 */
public interface SpecsService {

    /**
     * 分页模糊查询
     * @param pageNum
     * @param pageSize
     * @param selectName
     * @return
     */
    PageInfo<TbSpecification> getLikeSpecs(Integer pageNum, Integer pageSize, String selectName);


    /**
     * 获取规格名字
     * @return
     */
    List<TbSpecification> getSpecsName();

    /**
     * 插入规格信息
     * @param tbSpecification 规格信息
     * @return
     */
    Integer postSpecs(TbSpecification tbSpecification);

    /**
     *增加规格级联
     */
    Integer postLinkSpecs(TbSpecification tbSpecification);
    /**
     * 删除规格
     * @param specsId  规格id
     * @return
     */
    Integer delSpecs(List<Integer> specsId);

    /**
     * 修改规格
     */
    Integer fixSpecs(TbSpecification tbSpecification);
}
