package com.icepoint.framework.web.ui.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.data.util.TreeUtils;
import com.icepoint.framework.web.ui.entity.UiLessFile;
import com.icepoint.framework.web.ui.entity.UiMenu;
import com.icepoint.framework.web.ui.mapper.UiMenuMapper;
import com.icepoint.framework.web.ui.service.UiLessFileService;
import com.icepoint.framework.web.ui.service.UiMenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Administrator
 */
@Service
public class UiMenuServiceImpl implements UiMenuService {

    @Resource
    private UiMenuMapper mapper;


    private final String uploadPath = "/data/";

    @Resource
    private UiLessFileService uiLessFileService;

    @Override
    public List<TreeNode<UiMenu>> getTreeList(String code, Integer termType, Integer type, Long appId, Long ownerId) {
        if (ObjectUtils.isEmpty(code)) {
            LambdaQueryWrapper<UiMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(UiMenu::getAppId, appId)
                    .eq(UiMenu::getOwnerId, ownerId);
            if (!ObjectUtils.isEmpty(termType)) {
                lambdaQueryWrapper.eq(UiMenu::getTermType, termType);
            }
            List<UiMenu> all = mapper.findAll(lambdaQueryWrapper);
            return TreeUtils.buildTreeStructure(all);
        }
        LambdaQueryWrapper<UiMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UiMenu::getAppId, appId)
                .eq(UiMenu::getOwnerId, ownerId)
                .eq(UiMenu::getCode, code);
        Optional<UiMenu> one = mapper.findOne(lambdaQueryWrapper);
        if (!one.isPresent()) {
            UiMenu parent = one.get();
            lambdaQueryWrapper.clear();
            lambdaQueryWrapper.eq(UiMenu::getAppId, appId)
                    .eq(UiMenu::getOwnerId, ownerId)
                    .eq(UiMenu::getCode, code);
            if (ObjectUtils.isEmpty(type)) {
                lambdaQueryWrapper.eq(UiMenu::getType, type);
            }
            List<UiMenu> children = mapper.findAll(lambdaQueryWrapper);
            if (ObjectUtils.isEmpty(children)) {
                return TreeUtils.buildTreeStructure(Collections.singletonList(parent));
            } else {
                List<UiMenu> list = new ArrayList<>(children);
                list.add(parent);
                return TreeUtils.buildTreeStructure(list, parent.getId());
            }
        }

        return Collections.emptyList();
    }

    @Override
    public Boolean updateUiMenu(UiMenu uiMenu) {
        if (ObjectUtils.isEmpty(uiMenu.getId())) {
            return mapper.insert(uiMenu) > 0;
        }
        return mapper.update(uiMenu) > 0;

    }

    @Override
    public Boolean deleteUiMenu(Long id) {
        return mapper.deleteById(id) > 0;
    }

    @Override
    public Page<UiMenu> page(UiMenu uiMenu, Pageable pageable) {
        Assert.isTrue(!ObjectUtils.isEmpty(uiMenu), "参数有误");
        Assert.isTrue(!ObjectUtils.isEmpty(uiMenu.getOwnerId()), "ownerId不存在");
        Assert.isTrue(!ObjectUtils.isEmpty(uiMenu.getAppId()), "appId不存在");
        Map<String, Object> map = MapUtils.objectToLineMap(uiMenu);
        QueryWrapper<UiMenu> wrapper = new QueryWrapper<>();
        wrapper.allEq(map);
        return mapper.findAll(wrapper, pageable);
    }

    @Override
    public UiMenu queryById(Long id) {
        return this.mapper.findById(id).orElseThrow(() -> new IllegalArgumentException("参数错误"));
    }

    @Override
    public Boolean updateIdxById(Long parentId, Long thisId, Long anotherId, String command) {
        //如果命令移动命令为空，返回false
        if (ObjectUtils.isEmpty(command)) {
            return false;
        }
        //更新数量
        boolean flag = false;

        //置顶，设置此节点 idx=MIN(idx)-0.1
        if ("top".equalsIgnoreCase(command)) {

            //查询最小索引
            LambdaQueryWrapper<UiMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UiMenu::getParentId, parentId).orderByAsc(UiMenu::getIdx).last("limit 1");
            Optional<UiMenu> optional = mapper.findOne(wrapper);
            if (optional.isPresent()) {
                UiMenu uiMenu = optional.get();
                //得到最小索引
                int minOrder = uiMenu.getIdx();
                wrapper.clear();
                wrapper.eq(UiMenu::getId, thisId);
                Optional<UiMenu> thisOptional = mapper.findOne(wrapper);
                if (thisOptional.isPresent()) {
                    UiMenu uiMenu1 = thisOptional.get();
                    //更新节点索引值
                    uiMenu.setIdx(minOrder - 1);
                    int updated = mapper.update(uiMenu);
                    flag = updated == 1;
                }
            }

        }

        //置底，设置此节点 idx=MAX(idx)+0.1
        if ("end".equalsIgnoreCase(command)) {
            //查询最大索引
            LambdaQueryWrapper<UiMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UiMenu::getParentId, parentId).orderByDesc(UiMenu::getIdx).last("limit 1");
            Optional<UiMenu> optional = mapper.findOne(wrapper);
            if (optional.isPresent()) {
                UiMenu genericUiMenu = optional.get();

                //得到最大索引
                int maxOrder = genericUiMenu.getIdx();
                wrapper.clear();
                wrapper.eq(UiMenu::getId, thisId);
                Optional<UiMenu> thisOptional = mapper.findOne(wrapper);
                if (thisOptional.isPresent()) {
                    UiMenu uiMenu = thisOptional.get();
                    //更新节点索引值
                    uiMenu.setIdx(maxOrder + 1);
                    int updated = mapper.update(uiMenu);
                    flag = updated == 1;
                }
            }
        }

        //移动，将两个节点idx数值进行交换
        if ("move".equalsIgnoreCase(command)) {
            //先查询是否有数据
            final Optional<UiMenu> thisOptional = mapper.findById(thisId);
            final Optional<UiMenu> anotherOptional = mapper.findById(anotherId);

            if (thisOptional.isPresent() && anotherOptional.isPresent()) {
                //获取节点数据
                UiMenu thisNode = thisOptional.get();
                UiMenu anotherNode = anotherOptional.get();
                int thisOrder = thisNode.getIdx();
                int anotherOrder = anotherNode.getIdx();
                //order值调换
                thisNode.setIdx(anotherOrder);
                anotherNode.setIdx(thisOrder);
                //更新数据
                int thisUpdated = mapper.update(thisNode);
                int anotherUpdated = mapper.update(anotherNode);

                flag = thisUpdated + anotherUpdated == 2;
            }
        }

        return flag;
    }

    @Override
    public String uploadLess(MultipartFile multipartFile, HttpServletRequest request, UiLessFile uiLessFile) {

        Assert.isTrue(!ObjectUtils.isEmpty(uiLessFile.getAppId()), "appId为空");
        Assert.isTrue(!ObjectUtils.isEmpty(uiLessFile.getOwnerId()), "ownerId为空");
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
            String format = sdf.format(new Date());
            File folder = new File(uploadPath + format);
            if (!folder.isDirectory()) {
                folder.mkdirs();
            }
            String oldName = multipartFile.getOriginalFilename();
            uiLessFile.setFileName(multipartFile.getName());
            String prifx = oldName.substring(oldName.lastIndexOf("."), oldName.length());
            if (!".css".equals(prifx)) {
                throw new IllegalArgumentException("文件后缀名应为css");
            }
            String newName = UUID.randomUUID() + prifx;
            multipartFile.transferTo(new File(folder, newName));
            // 返回上传文件的访问路径
            String filePath = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + "/less/" + format + newName;
            uiLessFile.setUrl(filePath);
            if (ObjectUtils.isEmpty(uiLessFile.getId())) {
                uiLessFileService.save(uiLessFile);
                return filePath;
            }
            uiLessFileService.update(uiLessFile);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public List<UiMenu> getMenuByRole(Long roleId) {
        System.out.println(mapper.getMenuByRole(roleId));
        return mapper.getMenuByRole(roleId);
    }

    @Override
    public Boolean updateMenuByRole(Long roleId, List<Long> menuId) {
        Long id = Optional.ofNullable(roleId).orElseThrow(() -> new IllegalArgumentException("角色id不存在"));
        //先根据roleId删除原有的数据
        mapper.deleteMenuByRoleId(id);
        if(!ObjectUtils.isEmpty(menuId)){
            menuId.forEach(item->{
                mapper.insertMenId(roleId,item);
            });
        }
        return true;
    }


}
