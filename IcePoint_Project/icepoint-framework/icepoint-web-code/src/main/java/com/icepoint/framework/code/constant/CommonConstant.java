package com.icepoint.framework.code.constant;

/**
 * 常用常量
 *
 * @author Juyss
 * @version 1.0
 * @since 2021-05-26 9:36
 */
public class CommonConstant {

    /**
     * 页码
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 分页大小
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序
     */
    public static final String SORT = "sort";

    /**
     * 排序方向
     */
    public static final String ORDER = "order";

    /**
     * 默认页数
     */
    public static final String PAGE_NUM_DEFAULT = "1";

    /**
     * 默认分页大小
     */
    public static final String PAGE_SIZE_DEFAULT = "10";

    /**
     * 默认排序
     */
    public static final String PAGE_SORT_DEFAULT = "create_time";

    /**
     * 默认排序方向
     */
    public static final String PAGE_ORDER_DEFAULT = " desc";

    /**
     * 正常状态
     */
    public static final Integer DEL_FLAG_NORMAL = 0;

    /**
     * 删除状态
     */
    public static final Integer DEL_FLAG_DEL = 1;

    /**
     * mapper查询条件逻辑删除字段
     */
    public static final Boolean MAPPER_DELETED_FLAG = true;

    /**
     * utf-8
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 默认超时时间 300秒
     */
    public static final String CACHE_EXPIRE = "300";

    /**
     * 顶级菜单的parentId
     */
    public static final Long ROOT = 0L;


    /**
     * 函数add.xml后缀
     */
    public static final String ADD_XML = "Add.xml";
    /**
     * 函数Delete.xml后缀
     */
    public static final String DELETE_XML = "Delete.xml";
    /**
     * 函数Get.xml后缀
     */
    public static final String GET_XML = "Get.xml";
    /**
     * 函数Update.xml后缀
     */
    public static final String UPDATE_XML = "Update.xml";

    /**
     * processList 文件
     */
    public static final String PROCESSLIST_XML = "processList.xml";


    public static final String METHOD_ADD = "add";

    public static final String METHOD_DELETE = "delete";

    public static final String METHOD_UPDATE = "update";

    public static final String METHOD_GET = "get";

    public static final String DEFAULT_FUN_TYPE = "projectFun";


}
