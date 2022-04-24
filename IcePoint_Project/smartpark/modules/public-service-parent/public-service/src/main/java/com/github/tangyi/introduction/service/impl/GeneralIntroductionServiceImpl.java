package com.github.tangyi.introduction.service.impl;

import com.github.tangyi.common.core.utils.TreeUtil;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.core.mybatis.page.PageUtils;
import com.github.tangyi.introduction.entity.GeneralIntroduction;
import com.github.tangyi.introduction.mapper.GeneralIntroductionMapper;
import com.github.tangyi.introduction.service.GeneralIntroductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: GeneralIntroductionServiceImpl
 * @Desc:
 * @Author Juyss
 * @Date: 2021-04-16 15:01
 * @Version 1.0
 */
@Service
public class GeneralIntroductionServiceImpl implements GeneralIntroductionService {

    @Autowired
    private GeneralIntroductionMapper mapper;

    /**
     * 选择插入
     *
     * @param generalIntroduction 数据封装对象
     * @return 是否添加成功
     */
    @Override
    public Boolean insert(GeneralIntroduction generalIntroduction) {
        return (mapper.insertSelective(generalIntroduction) == 1);
    }

    /**
     * 逻辑删除一条数据
     *
     * @param id 要删除的数据的ID
     * @return 是否删除成功
     */
    @Override
    public Boolean delete(Long id) {
        return (mapper.deleteByPrimaryKey(id) == 1);
    }

    /**
     * 选择更新
     *
     * @param generalIntroduction 数据封装对象
     * @return 是否更新成功
     */
    @Override
    public Boolean update(GeneralIntroduction generalIntroduction) {
        return (mapper.updateByPrimaryKeySelective(generalIntroduction) == 1);
    }

    /**
     * 根据ID获取一条通用简介数据
     *
     * @param id 主键ID
     * @return 数据实体
     */
    @Override
    public GeneralIntroduction getIntroById(Long id) {
        return mapper.getIntroById(id);
    }

    /**
     * 根据ID查询集合所有数据
     *
     * @param sort  排序类别
     * @param order 排序方向
     * @return 符合条件的集合
     */
    @Override
    public List<GeneralIntroduction> getList(String sort, String order) {
        List<GeneralIntroduction> list = mapper.getList(sort, order);
        if (ObjectUtils.isEmpty(list)){
            return null;
        }
        List<GeneralIntroduction> sortedList = list.stream().sorted(Comparator.comparing(GeneralIntroduction::getIdx)).collect(Collectors.toList());
        List<GeneralIntroduction> treeList = TreeUtil.buildTree(sortedList, 0L);
        return treeList;
    }

    /**
     * 根据ID获取子节点数据
     *
     * @param parentId 父节点ID
     * @param sort  排序类别
     * @param order 排序方向
     * @return 符合条件的集合
     */
    @Override
    public PageResult getChildrenList(Long parentId, String name, Integer pageNum, Integer pageSize, String sort, String order) {
        return PageUtils.query(pageNum, pageSize, 10, () -> mapper.getChildrenList(parentId, name, sort, order));
    }

    /**
     * 获取同一层级最大索引值
     *
     * @param parentId 父节点ID
     * @return 最大idx值
     */
    @Override
    public Double getMaxIdx(Long parentId) {
        return mapper.getMaxIdx(parentId);
    }

    /**
     * 获取同一层级最小索引值
     *
     * @param parentId 父级节点ID
     * @return 最小Idx
     */
    @Override
    public Double getMinIdx(Long parentId) {
        return mapper.getMinIdx(parentId);
    }

    /**
     * 根据ID更新子节点排序
     *
     * @param parentId 父节点ID
     * @param thisId   此节点ID
     * @param anotherId 另一个需要修改的节点ID
     * @param command 置顶 :top  置底 :end 移动 :move
     * @return 是否操作成功
     */
    @Override
    public Boolean updateIdxById(Long parentId, Long thisId, Long anotherId,  String command) {

        //如果命令移动命令为空，返回false
        if(StringUtils.isEmpty(command)){
            return false;
        }

        //更新数量
        boolean flag = false;

        //置顶，设置此节点 idx=MIN(idx)-0.1
        if("top".equalsIgnoreCase(command)){
            Double minIdx = mapper.getMinIdx(parentId);
            int updated = mapper.updateIdxById(thisId, minIdx - 0.1);
            flag = updated==1;
        }

        //置底，设置此节点 idx=MAX(idx)+0.1
        if("end".equalsIgnoreCase(command)){
            Double maxIdx = mapper.getMaxIdx(parentId);
            int updated = mapper.updateIdxById(thisId, maxIdx + 0.1);
            flag = updated==1;
        }

        //移动，将两个节点idx数值进行交换
        if("move".equalsIgnoreCase(command)){
            GeneralIntroduction thisIntro = mapper.getIntroById(thisId);
            GeneralIntroduction anotherIntro = mapper.getIntroById(anotherId);
            Double thisIntroIdx = thisIntro.getIdx();
            Double anotherIntroIdx = anotherIntro.getIdx();
            int thisUpdated = mapper.updateIdxById(thisId,anotherIntroIdx);
            int anotherUpdated = mapper.updateIdxById(anotherId,thisIntroIdx);
            flag = thisUpdated + anotherUpdated == 2;
        }

        return flag;
    }

    /**
     * 根据name查询数据
     *
     * @param name  名称
     * @param sort  排序分类
     * @param order 排序方向
     * @return 符合条件的集合
     */
    @Override
    public List<GeneralIntroduction> selectIntroByName(String name, String sort, String order) {
        return mapper.selectIntroByName(name, sort, order);
    }
}
