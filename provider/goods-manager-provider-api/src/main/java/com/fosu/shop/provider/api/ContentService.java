package com.fosu.shop.provider.api;

import com.fosu.shop.business.dto.ContentFileName;
import com.fosu.shop.provider.domain.TbContent;

import java.util.List;

/**
 * 广告管理
 * @author Administrator
 */
public interface ContentService {

    /**
     *   广告内容插入
     * @param tbContent
     * @param contentFileName
     */
    Integer postContent(TbContent tbContent, ContentFileName contentFileName);

    /**
     * 查询所有广告
     * @return
     */
    List<TbContent> getContent();

    /**
     * 批量删除广告
     * @param contentId
     * @return
     */
    Integer delContent(List<Integer> contentId);

    /**
     * 修改广告
     * @param tbContent
     * @return
     */
    Integer fixContent(TbContent tbContent);
}
