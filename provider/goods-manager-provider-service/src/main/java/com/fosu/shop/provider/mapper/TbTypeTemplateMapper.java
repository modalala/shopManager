package com.fosu.shop.provider.mapper;

import com.fosu.shop.provider.domain.TbSpecification;
import com.fosu.shop.provider.domain.TbTypeTemplate;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbTypeTemplateMapper extends Mapper<TbTypeTemplate> {

    /**
     * 这个成功了 分页mapper
     * @return
     */
    List<TbTypeTemplate> typeQueryPage(String selectName);
}