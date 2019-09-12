package com.fosu.shop.provider.service;

import com.fosu.shop.business.dto.ContentFileName;
import com.fosu.shop.provider.api.ContentService;
import com.fosu.shop.provider.domain.TbBrand;
import com.fosu.shop.provider.domain.TbContent;
import com.fosu.shop.provider.domain.TbContentCategory;
import com.fosu.shop.provider.mapper.TbContentCategoryMapper;
import com.fosu.shop.provider.mapper.TbContentMapper;
import com.fosu.shop.provider.utils.FileNameUtils;
import org.apache.dubbo.common.Version;
import org.apache.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Administrator
 */
@Service(version = "1.0.0")
public class ContentServiceImpl implements ContentService {

    @Resource
    private TbContentMapper tbContentMapper;

    @Resource
    private TbContentCategoryMapper categoryMapper;

    @Override
    public Integer postContent(TbContent tbContent, ContentFileName contentFileName) {

        String localPath = "D:/doc";

        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            String filePath = localPath;
            File dir = new File(filePath);
            //判断文件目录是否存在
            if (!dir.exists() || !dir.isDirectory()) {
                dir.mkdirs();
            }

            //已经随机过的文件名字
            String pathName = localPath + "/" + FileNameUtils.getFileName(contentFileName.getOriginalFilename());

            file = new File(pathName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(contentFileName.getBytes());

            System.out.println(file.getName());
            System.out.println(file.length());
            System.out.println(file.toPath());

            //这里要改
            String url = "http://localhost:9002/doc/";
            url = url + file.getName();

            tbContent.setPic(url);
            tbContentMapper.insert(tbContent);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return 1;
    }

    @Override
    public List<TbContent> getContent() {
        final List<TbContent> tbContents = tbContentMapper.selectAll();
        return tbContents;
    }

    @Override
    public Integer delContent(List<Integer> contentId) {

        AtomicReference<Integer> num = new AtomicReference<>(0);
        contentId.forEach((e)->{
            int i = tbContentMapper.deleteByPrimaryKey(e);
            num.getAndSet(num.get() + i);
        });
        System.out.println(num.get());
        return num.get();
    }

    @Override
    public Integer fixContent(TbContent tbContent) {
        Example example = new Example(TbContent.class);
        example.createCriteria().andEqualTo("id",tbContent.getId());
        final Integer num = tbContentMapper.updateByExample(tbContent, example);
        return num;
    }

    @Override
    public List<TbContentCategory> getAllContentCategory() {

        final List<TbContentCategory> list = categoryMapper.selectAll();
        return list;
    }
}

