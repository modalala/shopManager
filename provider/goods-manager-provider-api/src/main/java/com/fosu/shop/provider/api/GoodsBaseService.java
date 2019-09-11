package com.fosu.shop.provider.api;

import com.fosu.shop.provider.domain.TbBrand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 品牌管理
 * @author Administrator
 */
public interface GoodsBaseService {

    /**
     * 获取所有品牌
     * @return
     */
    List<TbBrand> getAllBrand();

    /**
     * 分页获得品牌
     * @param pageNum 页码
     * @param pageSize 个数
     * @return
     */
    PageInfo<TbBrand> getAllPageBrand(Integer pageNum,Integer pageSize);


    /**
     * 分页模糊查询
     * @param pageNum
     * @param pageSize
     * @param selectName
     * @return
     */
    PageInfo<TbBrand> getLikeBrand(Integer pageNum, Integer pageSize,String selectName);

    /**
     * 获取品牌名字
     * @return
     */
    List<TbBrand> getBrandName();

    /**
     * 插入品牌信息
     * @param tbBrand
     * @return
     */
    Integer postBrand(TbBrand tbBrand);

    /**
     * 删除品牌
     * @param bandId
     * @return
     */
    Integer delBrand(List<Integer> bandId);

    /**
     * 修改品牌
     */
    Integer fixBrand(TbBrand tbBrand);
}
