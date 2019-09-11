package com.fosu.shop.provider.service;

import com.fosu.shop.provider.api.SpecsService;
import com.fosu.shop.provider.domain.TbBrand;
import com.fosu.shop.provider.domain.TbSpecification;
import com.fosu.shop.provider.domain.TbSpecificationOption;
import com.fosu.shop.provider.mapper.TbSpecificationMapper;
import com.fosu.shop.provider.mapper.TbSpecificationOptionMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 规格管理
 * @author Administrator
 */
@Service(version = "1.0.0")
public class SpecsServiceImpl implements SpecsService {

    @Resource
    private TbSpecificationMapper tbSpecificationMapper;

    @Resource
    private TbSpecificationOptionMapper optionMapper;


    @Override
    public PageInfo<TbSpecification> getLikeSpecs(Integer pageNum, Integer pageSize, String selectName) {
        PageHelper.startPage(pageNum,pageSize);
        //Example example = new Example(TbSpecification.class);
        //example.createCriteria().andLike("specName","%"+selectName+"%");

        PageInfo<TbSpecification> pageInfo = new PageInfo<>(tbSpecificationMapper.specsQueryPage(selectName));

        return pageInfo;
    }



    @Override
    public List<TbSpecification> getSpecsName() {

        return tbSpecificationMapper.selectAll();
    }

    @Override
    public Integer postSpecs(TbSpecification tbSpecification) {
        final int insert = tbSpecificationMapper.insert(tbSpecification);

        return insert;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer postLinkSpecs(TbSpecification tbSpecification) {

        //插入返回id
        final int insert = tbSpecificationMapper.insert(tbSpecification);
        Long specId = tbSpecification.getId();
        final List<TbSpecificationOption> optionList = tbSpecification.getTbSpecificationOptions();
        optionList.forEach((e)->{
            //把插入的主键id放进去
            e.setSpecId(specId);
            optionMapper.insert(e);
        });

        return 1;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer delSpecs(List<Integer> specsId) {

        AtomicReference<Integer> num = new AtomicReference<>(0);
        specsId.forEach((e)->{
            //把级联的删除了
            Example example = new Example(TbSpecificationOption.class);
            example.createCriteria().andEqualTo("specId","e");
            optionMapper.deleteByExample(example);
            //删除总的
            int i = tbSpecificationMapper.deleteByPrimaryKey(e);
            num.getAndSet(num.get() + i);

        });
        return num.get();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer fixSpecs(TbSpecification tbSpecification) {

        //这里name是空的话 数据库拿回name
        if(tbSpecification.getSpecName() == "" || tbSpecification.getSpecName() == null){
            TbSpecification temp = tbSpecificationMapper.selectByPrimaryKey(tbSpecification.getId());
            tbSpecification.setSpecName(temp.getSpecName());
        }
        final Long tbSpid = tbSpecification.getId();
        Example example = new Example(TbSpecification.class);
        example.createCriteria().andEqualTo("id",tbSpid);
        final Integer num = tbSpecificationMapper.updateByExample(tbSpecification, example);

        final List<TbSpecificationOption> optionList = tbSpecification.getTbSpecificationOptions();

        //级联内部修改
        Example opExample = new Example(TbSpecificationOption.class);
        opExample.createCriteria().andEqualTo("specId",tbSpid);

        optionList.forEach((e)->{
            e.setSpecId(tbSpid);
            optionMapper.updateByExample(e,opExample);
        });
        return num;
    }
}
