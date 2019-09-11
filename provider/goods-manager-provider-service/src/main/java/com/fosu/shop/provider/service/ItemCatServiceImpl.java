package com.fosu.shop.provider.service;

import com.fosu.shop.provider.api.ItemCatService;

import com.fosu.shop.provider.domain.TbItemCat;
import com.fosu.shop.provider.mapper.TbItemCatMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 分类管理
 * @author Administrator
 */
@Service(version = "1.0.0")
public class ItemCatServiceImpl implements ItemCatService {

    @Resource
    private TbItemCatMapper tbItemCatMapper;
    @Override
    public PageInfo<TbItemCat> getPageItemCat(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // 设置分页查询条件
        Example example = new Example(TbItemCat.class);
        PageInfo<TbItemCat> pageInfo = new PageInfo<>(tbItemCatMapper.selectByExample(example));

        return pageInfo;
    }

    @Override
    public List<TbItemCat> getLowerItemCat(Integer parentId) {
        Example example = new Example(TbItemCat.class);
        example.createCriteria().andEqualTo("parentId",parentId);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(example);
        return tbItemCats;
    }

    @Override
    public Integer postItemCat(TbItemCat tbItemCat) {
        final Integer insert = tbItemCatMapper.insert(tbItemCat);
        return insert;
    }

    @Override
    public Integer delItemCat(List<Integer> id) {
        AtomicReference<Integer> num = new AtomicReference<>(0);
        id.forEach((e)->{
            int i = tbItemCatMapper.deleteByPrimaryKey(e);
            num.getAndSet(num.get() + i);
        });
        return num.get();
    }

    @Override
    public Integer fixItemCat(TbItemCat tbItemCat) {
        Example example = new Example(TbItemCat.class);
        example.createCriteria().andEqualTo("id",tbItemCat.getId());
        final Integer num = tbItemCatMapper.updateByExample(tbItemCat, example);
        return num;
    }
}
