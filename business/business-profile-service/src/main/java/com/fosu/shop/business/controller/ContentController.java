package com.fosu.shop.business.controller;

import com.fosu.shop.business.commons.ResponseResultMe;

import com.fosu.shop.business.dto.ContentFileName;
import com.fosu.shop.business.dto.ContentParam;
import com.fosu.shop.provider.api.ContentService;
import com.fosu.shop.provider.domain.TbContent;
import com.fosu.shop.provider.domain.TbContentCategory;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * 广告图片上传
 * @author Administrator
 */
@RestController
@RequestMapping(value = "content")
public class ContentController {

    @Reference(version = "1.0.0")
    private ContentService contentService;

    @PostMapping("/fileUpload")
    @ApiOperation(value = "广告添加")
    public ResponseResultMe<Integer> upload(@RequestParam("file") MultipartFile file, ContentParam contentParam) {

        TbContent tbContent = new TbContent();

        tbContent.setCategoryId(contentParam.getCategoryId());
        tbContent.setSortOrder(contentParam.getSortOrder());
        tbContent.setTitle(contentParam.getTitle());
        tbContent.setUrl(contentParam.getUrl());
        tbContent.setStatus(contentParam.getStatus());

        //是否没有文件
        if (Objects.isNull(file) || file.isEmpty()) {
            // return new ResponseResultMe<>(ResponseResultMe.CodeStatus.FAIL, "文件为空，请重新上传");
            return new ResponseResultMe<>(ResponseResultMe.CodeStatus.BREAKING, "没有文件图片传入");
        }

        ContentFileName contentFileName = new ContentFileName();
        try {
            byte[] bytes = file.getBytes();

            contentFileName.setOriginalFilename(file.getOriginalFilename());
            contentFileName.setBytes(file.getBytes());
            contentFileName.setSize(file.getSize());

            contentService.postContent(tbContent, contentFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseResultMe<>(ResponseResultMe.CodeStatus.FAIL, "文件存储异常");
        }
        return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK, "文件存储成功");
    }

    @GetMapping("/getContent")
    @ApiOperation(value = "广告信息查询")
    public ResponseResultMe<List<TbContent>> getContent() {
        final List<TbContent> content = contentService.getContent();
        return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK,"广告查询成功",content);
    }

    @PostMapping("/delContent")
    @ApiOperation(value = "广告信息批量删除")
    ResponseResultMe<Integer> deleteContent(@RequestBody List<Integer> contentId) {
        if(contentId.size() == 0){
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.BREAKING,"没有参数");
        }
        Integer num = contentService.delContent(contentId);

        if(num == contentId.size()) {
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.OK,"删除数据成功");
        }
        else
        {
            return new ResponseResultMe<Integer>(ResponseResultMe.CodeStatus.FAIL,"删除数据失败");
        }
    }
    @PostMapping("/fixContent")
    @ApiOperation(value = "广告信息修改")
    ResponseResultMe<Integer> fixContent(@RequestBody TbContent tbContent){
        final Integer num = contentService.fixContent(tbContent);
        if(num != 0)
        {
            return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK,"修改成功");
        }
        return new ResponseResultMe<>(ResponseResultMe.CodeStatus.FAIL,"修改失败");
    }

    @GetMapping("/getContentCategory")
    @ApiOperation(value = "广告分类获取")
    ResponseResultMe<List<TbContentCategory>> getCategory(){
        final List<TbContentCategory> categoryList = contentService.getAllContentCategory();
        return new ResponseResultMe<>(ResponseResultMe.CodeStatus.OK,"广告分类获取成功",categoryList);
    }
}