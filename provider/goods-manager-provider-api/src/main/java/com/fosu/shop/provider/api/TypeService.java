package com.fosu.shop.provider.api;

import com.fosu.shop.provider.domain.TbTypeTemplate;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分类管理
 * @author Administrator
 */
public interface TypeService {

    /**
     * 模板模糊分页
     * @param pageNum
     * @param pageSize
     * @param selectName
     * @return
     */
    PageInfo<TbTypeTemplate> getLikeType(Integer pageNum, Integer pageSize,String selectName);

    /**
     * 插入模板
     * @param tbTypeTemplate
     * @return
     */
    Integer postType(TbTypeTemplate tbTypeTemplate);

    /**
     * 修改模板
     * @param tbTypeTemplate
     * @return
     */
    Integer fixType(TbTypeTemplate tbTypeTemplate);
    /**
     * 删除模板
     * @param typeId
     * @return
     */
    Integer delType(List<Integer> typeId);
}
