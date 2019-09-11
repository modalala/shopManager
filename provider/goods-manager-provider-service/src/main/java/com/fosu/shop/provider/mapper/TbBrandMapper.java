package com.fosu.shop.provider.mapper;

import com.fosu.shop.provider.domain.TbBrand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbBrandMapper extends Mapper<TbBrand> {

    @Select("select id,name from tb_brand")
    List<TbBrand> getBrandName();
}