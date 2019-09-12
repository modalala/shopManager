package com.fosu.shop.provider.service;


import com.fosu.shop.provider.api.TypeService;
import com.fosu.shop.provider.domain.TbBrand;
import com.fosu.shop.provider.domain.TbItemCat;
import com.fosu.shop.provider.domain.TbTypeTemplate;
import com.fosu.shop.provider.mapper.TbTypeTemplateMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Administrator
 */
@Service(version = "1.0.0")
public class TypeServiceImpl implements TypeService {

    @Resource
    private TbTypeTemplateMapper templateMapper;
    @Override
    public PageInfo<TbTypeTemplate> getLikeType(Integer pageNum, Integer pageSize, String selectName) {
        PageHelper.startPage(pageNum, pageSize);

        PageInfo<TbTypeTemplate> pageInfo = new PageInfo<>(templateMapper.typeQueryPage(selectName));
        return pageInfo;
    }

    @Override
    public Integer postType(TbTypeTemplate tbTypeTemplate) {
        final Integer insert = templateMapper.insert(tbTypeTemplate);
        return insert;
    }

    @Override
    public Integer fixType(TbTypeTemplate tbTypeTemplate) {
        Example example = new Example(TbTypeTemplate.class);
        example.createCriteria().andEqualTo("id",tbTypeTemplate.getId());
        final Integer num = templateMapper.updateByExample(tbTypeTemplate, example);
        return num;
    }

    @Override
    public Integer delType(List<Integer> typeId) {
        AtomicReference<Integer> num = new AtomicReference<>(0);
        typeId.forEach((e)->{
            int i = templateMapper.deleteByPrimaryKey(e);
            num.getAndSet(num.get() + i);
        });
        return num.get();
    }
}
