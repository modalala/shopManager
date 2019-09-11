package com.fosu.shop.provider.api;

import com.fosu.shop.provider.domain.TbBrand;
import com.fosu.shop.provider.domain.TbItemCat;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分类管理
 * @author Administrator
 */
public interface ItemCatService {

    /**
     * 查询顶级有分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<TbItemCat> getPageItemCat(Integer pageNum, Integer pageSize);

    /**
     * 查询下级
     * @param parentId 用这个查
     * @return
     */
    List<TbItemCat> getLowerItemCat(Integer parentId);

    /**
     * 新增分类
     * @param tbItemCat
     * @return
     */
    Integer postItemCat(TbItemCat tbItemCat);

    /**
     * 批量删除
     * @param id
     * @return
     */
    Integer delItemCat(List<Integer> id);

    /**
     * 修改
     * @param tbItemCat
     * @return
     */
    Integer fixItemCat(TbItemCat tbItemCat);
}
