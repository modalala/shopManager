package com.fosu.shop.provider.mapper;

import com.fosu.shop.provider.domain.TbSpecification;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 规格
 * @author Administrator
 */
public interface TbSpecificationMapper extends Mapper<TbSpecification> {



    /**
     * 这个成功了 分页mapper
     * @return
     */
    List<TbSpecification> specsQueryPage(String selectName);
}