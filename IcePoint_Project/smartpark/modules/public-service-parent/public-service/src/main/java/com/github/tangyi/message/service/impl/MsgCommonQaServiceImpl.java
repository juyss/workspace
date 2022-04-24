package com.github.tangyi.message.service.impl;

import com.github.tangyi.common.core.utils.TreeUtil;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.core.mybatis.page.PageUtils;
import com.github.tangyi.introduction.entity.GeneralIntroduction;
import com.github.tangyi.message.entity.MsgCommonQa;
import com.github.tangyi.message.mapper.MsgCommonQaMapper;
import com.github.tangyi.message.service.MsgCommonQaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName MsgCommonQaServiceImpl
 * @description
 * @since 2021-05-20 13:57
 */
@Service
public class MsgCommonQaServiceImpl implements MsgCommonQaService {

    @Autowired
    private MsgCommonQaMapper mapper;

    /**
     * 根据ID查询集合所有数据
     *
     * @param sort  排序类别
     * @param order 排序方向
     * @return 符合条件的集合
     */
    @Override
    public List<MsgCommonQa> getTreeList(String sort, String order) {
        List<MsgCommonQa> list = mapper.getList(sort, order);
        if (ObjectUtils.isEmpty(list)){
            return null;
        }
        List<MsgCommonQa> sortedList = list.stream().sorted(Comparator.comparing(MsgCommonQa::getIdx)).collect(Collectors.toList());
        return TreeUtil.buildTree(sortedList, 0L);
    }

    /**
     * 根据ID获取子节点数据
     *
     * @param parentId 父节点ID
     * @param pageNum
     * @param pageSize
     * @param sort     排序类别
     * @param order    排序方向
     * @return 符合条件的集合
     */
    @Override
    public PageResult getChildrenList(Long parentId, String name, Integer pageNum, Integer pageSize, String sort, String order) {
        return PageUtils.query(pageNum, pageSize, 10, () -> mapper.getChildrenList(parentId, name, sort, order));
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
            double idx = mapper.getMinIdx(parentId);
            int updated = mapper.updateIdxById(thisId, idx - 0.01);
            flag = updated==1;
        }

        //置底，设置此节点 idx=MAX(idx)+0.1
        if("end".equalsIgnoreCase(command)){
            double idx  = mapper.getMaxIdx(parentId);
            int updated = mapper.updateIdxById(thisId, idx + 0.01);
            flag = updated==1;
        }

        //移动，将两个节点idx数值进行交换
        if("move".equalsIgnoreCase(command)){
            MsgCommonQa thisIntro = mapper.selectByPrimaryKey(thisId);
            MsgCommonQa anotherIntro = mapper.selectByPrimaryKey(anotherId);
            Double thisIntroIdx = thisIntro.getIdx();
            Double anotherIntroIdx = anotherIntro.getIdx();
            int thisUpdated = mapper.updateIdxById(thisId,anotherIntroIdx);
            int anotherUpdated = mapper.updateIdxById(anotherId,thisIntroIdx);
            flag = thisUpdated + anotherUpdated == 2;
        }

        return flag;
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
     * @param parentId 父节点ID
     * @return 最小idx值
     */
    @Override
    public Double getMinIdx(Long parentId) {
        return mapper.getMinIdx(parentId);
    }
}
