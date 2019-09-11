package com.fosu.shop.provider.service;

import com.fosu.shop.provider.api.GoodsBaseService;
import com.fosu.shop.provider.domain.TbBrand;
import com.fosu.shop.provider.mapper.TbBrandMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 商品管理生产者
 * <p>
 * Description
 * </p>
 * @author miki
 * @version v1.0.0
 * @date 2019/09/7
 * @see com.fosu.shop.provider.service
 *
 */
@Service(version = "1.0.0")
public class GoodsBaseServiceImpl implements GoodsBaseService {

    @Resource
    private TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> getAllBrand() {
        return tbBrandMapper.selectAll();
    }

    @Override
    public PageInfo<TbBrand> getAllPageBrand(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // 设置分页查询条件
        Example example = new Example(TbBrand.class);
        PageInfo<TbBrand> pageInfo = new PageInfo<>(tbBrandMapper.selectByExample(example));
        return pageInfo;
    }




    @Override
    public Integer postBrand(TbBrand tbBrand) {
        final Integer insert = tbBrandMapper.insert(tbBrand);
        return insert;
    }

    @Override
    public Integer delBrand(List<Integer> bandId) {

        AtomicReference<Integer> num = new AtomicReference(0);
        bandId.forEach((e)->{
            int i = tbBrandMapper.deleteByPrimaryKey(e);
            num.getAndSet(num.get()+i);
        });
        return num.get();
    }

    @Override
    public PageInfo<TbBrand> getLikeBrand(Integer pageNum, Integer pageSize, String selectName) {

        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(TbBrand.class);
        example.createCriteria().andLike("name","%"+selectName+"%");
        PageInfo<TbBrand> pageInfo = new PageInfo<>(tbBrandMapper.selectByExample(example));
        return pageInfo;
    }

    @Override
    public List<TbBrand> getBrandName() {
        List<TbBrand> brandName = tbBrandMapper.getBrandName();
        return brandName;
    }

    @Override
    public Integer fixBrand(TbBrand tbBrand) {
        //这里name是空的话 数据库拿回name
        if(tbBrand.getName() == "" || tbBrand.getName() == null){
            TbBrand temp = tbBrandMapper.selectByPrimaryKey(tbBrand.getId());
            tbBrand.setName(temp.getName());
        }
        Example example = new Example(TbBrand.class);
        example.createCriteria().andEqualTo("id",tbBrand.getId());
        final Integer num = tbBrandMapper.updateByExample(tbBrand, example);
        return num;
    }
}
