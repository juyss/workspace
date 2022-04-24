package com.github.tangyi.webcast.utils;

import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.core.utils.PageUtil;

/**
 * 分页转换工具类
 *
 * @author gaokx
 * @date 2018/12/4 20:16
 */
public class PageConvertUtil extends PageUtil {

    public static void convertProperties(PageInfo pageInfo ,Integer recordCount, Integer pageNum, Integer pageSize) {
      pageInfo.setTotal(recordCount);
      pageInfo.setPageSize(pageSize);
      pageInfo.setPages(recordCount % pageSize > 0 ? recordCount / pageSize + 1 : recordCount / pageSize);
      pageInfo.setPageNum(pageInfo.getPages() < pageNum ? pageInfo.getPages() : pageNum);
      pageInfo.setIsFirstPage(pageInfo.getPageNum() <= 1 ? true : false);
      pageInfo.setPrePage(pageInfo.getPageNum() <= 1 ? pageInfo.getPageNum() : pageInfo.getPageNum() - 1);
      pageInfo.setHasPreviousPage(pageInfo.getPageNum() > 1 ? true : false);
      pageInfo.setHasNextPage(pageInfo.getPageNum() < pageInfo.getPages()  ? true : false);
  }
}
