package com.github.tangyi.user.synchrodata;

import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.utils.IdGen;
import com.github.tangyi.common.security.ty.MainAccessToken;
import com.github.tangyi.common.security.ty.MainUserWithRole;
import com.github.tangyi.core.common.util.JsonUtils;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.core.mybatis.service.CommonDaoService;
import com.github.tangyi.example.MainRoleExample;
import com.github.tangyi.example.MainUserExample;
import com.github.tangyi.example.SysUserDeptExample;
import com.github.tangyi.mapper.SysUserDeptBaseMapper;
import com.github.tangyi.model.MainRole;
import com.github.tangyi.model.MainUser;
import com.github.tangyi.model.SysUserDept;
import com.github.tangyi.user.api.dto.UserDto;
import com.github.tangyi.user.api.module.*;
import com.github.tangyi.user.service.*;
import com.github.tangyi.user.synchrodata.config.SynchDeptProperties;
import com.github.tangyi.user.synchrodata.config.SynchMainUserProperties;
import com.github.tangyi.user.synchrodata.model.MainDept;
import com.github.tangyi.user.synchrodata.model.MainUserWithRoleAndDept;
import com.github.tangyi.user.synchrodata.service.ClientAuthService;
import com.github.tangyi.user.synchrodata.service.MainDeptApiService;
import com.github.tangyi.user.synchrodata.service.MainRoleApiService;
import com.github.tangyi.user.synchrodata.service.MainUserApiService;
import com.qiniu.util.Json;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author xh
 * @Description 同步主系统的数据 定时任务
 * @Date 6:19 2020/11/4
 * @Param
 * @return
 **/
@Component
public class SynchTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private CommonDaoService commonDaoService;
    @Autowired
    private ClientAuthService clientAuthService;
    @Autowired
    private MainUserApiService mainUserApiService;
    @Autowired
    private MainRoleApiService mainRoleApiService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthsService userAuthsService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private MainDeptApiService mainDeptApiService;
    @Autowired
    private SynchMainUserProperties synchMainUserProperties;
    @Autowired
    private DeptService deptService;
    @Autowired(required = false)
    private SysUserDeptBaseMapper sysUserDeptBaseMapper;

    //同步用户数据 每天执行一次
    @Scheduled(cron = "0 0 0 * * *")
    @CacheEvict(value = "user", allEntries = true)
    public void synchUserData() {

        try {
            logger.info("同步用户数据的定时任务开始执行");

            MainAccessToken accessToken = clientAuthService.getAccessToken();

            //获取用户列表
            int page = 1, size = 1000;
            synchUser(accessToken, page, size);
        } catch (Exception e) {
            logger.info("同步用户数据的定时任务执行异常 {}", e);
        }
    }

    //同步用户数据 每天执行一次
    @Scheduled(cron = "0 10 0 * * *")
    public void synchRoleData() {

        try {
            logger.info("同步角色数据的定时任务开始执行");

            MainAccessToken accessToken = clientAuthService.getAccessToken();

            //获取角色列表
            int page = 1, size = 1000;
            List<Long> longs = synchRole(accessToken, page, size);
            logger.info("主系统已有角色的id列表： {}", JsonUtils.toString(longs));
            Role role = new Role();
            role.setApplicationCode("EXAM");
            List<Role> allList = roleService.findAllList(role);
            List<Role> collect = allList.stream().filter(item -> item.getDelFlag() == 0 && !longs.contains(item.getId())).collect(Collectors.toList());
            logger.info("新增角色有： {}", JsonUtils.toString(collect));
            // 子系统新加的角色，同步到主系统中去
            collect.forEach(item -> {
                MainRole mainRole = new MainRole();
                mainRole.setRoleCode(item.getRoleCode());
                mainRole.setRoleName(item.getRoleName());
                mainRole.setSubRoleId(item.getId());
                int id = mainRoleApiService.add(mainRole, accessToken.getAccessToken());
                mainRole.setRoleId(id);
                mainRole.setCommonValue("admin", "EXAM", "gitee");
                commonDaoService.insert(mainRole);
            });

        } catch (Exception e) {
            logger.info("同步角色数据的定时任务执行异常 {}", e.toString());
        }
    }

    private List<Long> synchRole(MainAccessToken accessToken, int page, int size) {
        List<Long> ids = new ArrayList<Long>();
        PageResult result = mainRoleApiService.getListByPage(page, size, accessToken.getAccessToken());
        List<MainRole> rows = (List<MainRole>) result.getRows();
        //遍历 根据是否已存，进行新增可修改
        MainRoleExample example = new MainRoleExample();
        example.and().andValid().andRoleIdNotIn(rows.stream().map(MainRole::getRoleId).collect(Collectors.toList()));
        MainRole mainRole1 = new MainRole();
        mainRole1.setDelFlag(1);
        commonDaoService.updateByExample(mainRole1, example);
        rows.forEach(item -> {
            example.clear();
            example.and().andRoleIdEqualTo(item.getRoleId()).andValid();
            MainRole mainRole = commonDaoService.selectOneByExample(example);
            if (mainRole != null) {
                Role role = roleService.get(mainRole.getSubRoleId());
                if (role == null || role.getDelFlag() == 1) {
                    mainRoleApiService.del(mainRole, accessToken.getAccessToken());
                    commonDaoService.delete(mainRole);
                } else {
                    ids.add(mainRole.getSubRoleId());
                    mainRole.setRoleCode(role.getRoleCode());
                    mainRole.setRoleName(role.getRoleName());
                    mainRoleApiService.update(mainRole, accessToken.getAccessToken());
                }
            } else {
                mainRoleApiService.del(item, accessToken.getAccessToken());
            }
        });

        if (result.getPageInfo().getTotal() > page * size) {
            ids.addAll(synchRole(accessToken, page + 1, size));
        }
        return ids;
    }

    private void synchUser(MainAccessToken accessToken, int page, int size) {
        PageResult result = mainUserApiService.getListByPage(page, size, accessToken.getAccessToken());
        List<MainUserWithRoleAndDept> rows = (List<MainUserWithRoleAndDept>) result.getRows();
        //遍历 根据是否已存，进行新增可修改
        MainUserExample example = new MainUserExample();
        rows.forEach(item -> {
            example.clear();
            example.and().andLoginNameEqualTo(item.getLoginName()).andValid();
            MainUser mainUser = commonDaoService.selectOneByExample(example);
            if (mainUser != null) {
                updateUserByMainUser(item, mainUser);
            } else {
                generateUserByMainUser(item);
            }
        });

        if (result.getPageInfo().getTotal() > page * size) {
            synchUser(accessToken, page + 1, size);
        }
    }

    private void generateUserByMainUser(MainUserWithRoleAndDept item) {
        MainUser mainUser;
        mainUser = new MainUser();
        mainUser.setGender(item.getGender());
        mainUser.setLoginName(item.getLoginName());
        mainUser.setUserMail(item.getUserMail());
        mainUser.setUserMobile(item.getUserMobile());
        mainUser.setUserOph(item.getUserOph());
        mainUser.setNote(item.getNote());
        mainUser.setUserName(item.getUserName());
        mainUser.setUserId(item.getUserId());
        mainUser.setCommonValue("admin", "EXAM", "gitee");

        UserDto userDto = new UserDto();
        userDto.setIdentifier(MainUserWithRole.IDENTIFIER_PREFIX + item.getLoginName());
        userDto.setEmail(item.getUserMail());
        userDto.setPhone(item.getUserMobile());
        userDto.setSex(item.getGender() % 2);//gender 1男 2 女 0 未选择
        userDto.setStatus(0);
        userDto.setName(item.getUserName());
        if(!isBelongKeepPasswordUsers(item.getLoginName()))userDto.setCredential(UUID.randomUUID().toString());
        try {
            User user = userService.addUser(userDto);
            mainUser.setSubUserId(user.getId());
            //角色处理
            userRoleHandle(item, user);
            userDeptHandle(item, user);
        } catch (Exception e) {
            logger.info("同步用户异常： mainUser :{} ,exception : {}", JsonUtils.toString(mainUser), e.getMessage());
        }
        commonDaoService.insert(mainUser);
    }

    //是否属于保持用户密码的用户
    private boolean isBelongKeepPasswordUsers(String loginName){
        String[] split = synchMainUserProperties.getKeepPasswordUsers().split(",");
        return Arrays.asList(split).contains(loginName);
    }

    private void updateUserByMainUser(MainUserWithRoleAndDept item, MainUser mainUser) {
        mainUser.setGender(item.getGender());
        mainUser.setLoginName(item.getLoginName());
        mainUser.setUserName(item.getUserName());
        mainUser.setUserMail(item.getUserMail());
        mainUser.setUserMobile(item.getUserMobile());
        mainUser.setUserOph(item.getUserOph());
        mainUser.setNote(item.getNote());
        commonDaoService.update(mainUser);
        User user = userService.get(mainUser.getSubUserId());
        user.setName(item.getUserName());
        user.setSex(item.getGender());
        user.setEmail(item.getUserMail());
        user.setPhone(item.getUserMobile());
        user.setSex(item.getGender() % 2);//gender 1男 2 女 0 未选择
        userService.update(user);
        if(!isBelongKeepPasswordUsers(item.getLoginName())){
            UserAuths userAuths = new UserAuths();
            userAuths.setIdentifier(item.getLoginName());
            UserAuths byIdentifier = userAuthsService.getByIdentifier(userAuths);
            byIdentifier.setCredential(encoder.encode(UUID.randomUUID().toString()));
            userAuthsService.update(byIdentifier);
        }

        userRoleHandle(item, user);
        userDeptHandle(item, user);
    }

    private void userDeptHandle(MainUserWithRoleAndDept item, User user) {
        List<MainUserWithRoleAndDept.Org> orgs = item.getOrgs();
        ArrayList<Long> depts = new ArrayList<>();
        SysUserDeptExample sysUserDeptExample = new SysUserDeptExample();
        if(orgs !=null && orgs.size()>0){
            orgs.forEach(one -> {
                Dept dept = new Dept();
                dept.setZtId(one.getOrgId().toString());
                List<Dept> list = deptService.findList(dept);
                if(list.size() == 0) {
                    this.synchDeptData();
                    list = deptService.findList(dept);
                    if(list.size() == 0 || list.size()>1) throw new CommonException("部门数据异常，检查该部门数据是否存在：" + JsonUtils.toString(one));
                }
                Dept dept1 = list.get(0);
                depts.add(dept1.getId());
                sysUserDeptExample.clear();
                sysUserDeptExample.and().andDeptIdEqualTo(dept1.getId()).andUserIdEqualTo(user.getId());
                SysUserDept sysUserDept = sysUserDeptBaseMapper.selectOneByExample(sysUserDeptExample);
                if(sysUserDept == null) {
                    sysUserDept = new SysUserDept();
                    sysUserDept.setId(IdGen.snowflakeId());
                    sysUserDept.setDeptId(dept1.getId());
                    sysUserDept.setUserId(user.getId());
                    sysUserDeptBaseMapper.insert(sysUserDept);
                }
            });

        }
        sysUserDeptExample.clear();
        sysUserDeptExample.and().andUserIdEqualTo(user.getId()).andDeptIdNotIn(depts);
        sysUserDeptBaseMapper.deleteByExample(sysUserDeptExample);
    }


    private void userRoleHandle(MainUserWithRole item, User user) {
        List<MainRole> roles = item.getRoles();
        if (roles != null && roles.size() > 0) {
            MainRoleExample mainRoleExample = new MainRoleExample();
            mainRoleExample.and().andValid().andRoleIdIn(roles.stream().map(MainRole::getRoleId).collect(Collectors.toList()));
            List<MainRole> mainRoles = commonDaoService.selectByExample(mainRoleExample);
            List<Long> ids = mainRoles.stream().map(MainRole::getSubRoleId).collect(Collectors.toList());
            //由于需求变动，让统一认证的用户能登陆学习端，所以 添加普通用户角色
            Role role = new Role();
            role.setRoleCode("role_user");
            Role roleuser = roleService.findByRoleCode(role);
            ids.add(roleuser.getId());
            //删除原来的角色
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRoleService.delete(userRole);
            //添加新的
            ids.forEach(id -> {
                UserRole entity = new UserRole();
                entity.setUserId(user.getId());
                entity.setRoleId(id);
                entity.setId(IdGen.snowflakeId());
                userRoleService.insert(entity);
            });
        }
    }



    //同步用户数据 每天执行一次
    @Scheduled(cron = "0 10 0 * * *")
    public void synchDeptData() {

        try {
            logger.info("同步部门数据的定时任务开始执行");

            MainAccessToken accessToken = clientAuthService.getAccessToken();

            List<MainDept> mainDeptTree = mainDeptApiService.getMainDeptTree(accessToken.getAccessToken());

            Map<Integer, MainDept> mainMap = toMainMap(mainDeptTree, null);
            //获取第一级 parentId=0
            List<MainDept> mainDeptByPage = mainDeptApiService.getMainDeptByPage(accessToken.getAccessToken(), 1, 100, 0);
            mainMap.put(mainDeptByPage.get(0).getOrgId(), mainDeptByPage.get(0));

            List<Dept> all = deptService.depts(null, null);
            Map<String, Dept> map = all.stream().filter(item -> !"qiye".equals(item.getType())).collect(Collectors.toMap(Dept::getZtId, item -> item));
            all.stream().filter(item -> "qiye".equals(item.getType())).forEach(item -> map.put("qiye" + item.getZtId(), item));

            ArrayList<Dept> delList = new ArrayList<>();
            doSomething(mainDeptTree, null, mainMap, map);
            Set<Integer> integers = mainMap.keySet();
            for (Dept dept : all) {
                if (!integers.contains(Integer.valueOf(dept.getZtId()))) delList.add(dept);
            }
            for (Dept dept : delList) {
                deptService.delete(dept);
            }

            // 需求变更不在需要同步企业
            //synchEnterprise();

        } catch (Exception e) {
            logger.info("同步部门数据的定时任务执行异常 {}", e);
        }
    }

    private void doSomething(List<MainDept> mainDeptTree, String type, Map<Integer, MainDept> mainMap, Map<String, Dept> map) {
        for (MainDept mainDept : mainDeptTree) {
            Integer orgId = mainDept.getOrgId();
            Dept dept = map.get(String.valueOf(orgId));
            if (dept == null) dept = map.get("qiye" + orgId);
            if (dept == null) {
                dept = save(mainDept, !"qiye".equals(type) ? getType(mainDept) : type, map, mainMap);
            } else {
                dept.setDeptName(mainDept.getOrgName());
                dept.setOrgCode(mainDept.getOrgCode());
                setParent(dept, mainDept, map, mainMap);
                dept.setDeptDesc(mainDept.getNote());
                deptService.update(dept);
            }
            if (mainDept.getChildren() != null && mainDept.getChildren().size() > 0)
                doSomething(mainDept.getChildren(), dept.getType(), mainMap, map);
        }
    }

    private void setParent(Dept dept, MainDept mainDept, Map<String, Dept> map, Map<Integer, MainDept> mainMap) {
        if (mainDept.getParentId() == 0) dept.setParentId(-1L);
        else {
            MainDept mainDept1 = mainMap.get(mainDept.getParentId());
            if (mainDept1 == null) throw new CommonException("数据异常");
            Dept dept1 = map.get(("qiye".equals(dept.getType()) ? dept.getType() : "") + mainDept1.getOrgId().toString());
            if (dept1 == null || !StringUtils.equals(dept1.getType(), dept.getType())) {
                dept1 = save(mainDept1, dept.getType(), map, mainMap);
            }
            dept.setParentId(dept1.getId());
        }
    }

    private Dept save(MainDept mainDept, String type, Map<String, Dept> map, Map<Integer, MainDept> mainMap) {
        Dept dept = new Dept();
        dept.setDeptName(mainDept.getOrgName());
        dept.setOrgCode(mainDept.getOrgCode());
        dept.setZtId(String.valueOf(mainDept.getOrgId()));
        dept.setType(type);
        dept.setDeptDesc(mainDept.getNote());
        dept.setSort(0);//默认为零
        setParent(dept, mainDept, map, mainMap);
        dept.setCommonValue("admin", "EXAM", "gitee");
        deptService.save(dept);
        if ("qiye".equals(type)) {
            map.put("qiye" + dept.getZtId(), dept);
        } else {
            map.put(dept.getZtId(), dept);
        }
        return dept;
    }

    private Map<Integer, MainDept> toMainMap(List<MainDept> mainDeptTree, HashMap<Integer, MainDept> map) {
        if (map == null) map = new HashMap<>();

        for (MainDept item : mainDeptTree) {
            map.put(item.getOrgId(), item);
            if (item.getChildren() != null && item.getChildren().size() > 0) {
                toMainMap(item.getChildren(), map);
            }
        }
        return map;
    }

    private String getType(MainDept mainDept) {
        if (mainDept != null) {
            if (StringUtils.equals("企业", mainDept.getOrgName()) || mainDept.getOrgType()==2) {
                return "qiye";
            }
            return "yuanqu";
        }
        return null;
    }


    public void synchEnterprise() {
        List<Dept> ztQiye = deptService.depts(null, "qiye");
        List<HashMap<String, String>> enterpriseList = deptService.listEnterprise();
        List<HashMap<String, String>> toUpdateList = new LinkedList<>();
        List<HashMap<String, String>> toInsertList = new LinkedList<>();

        // 现在的时间
        String nowDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ztQiye.forEach(ztEnterprise -> {
            boolean existed = false;
            for (HashMap<String, String> enterprise : enterpriseList) {
                if (StringUtils.equals(ztEnterprise.getOrgCode(), enterprise.get("creditCode"))) {
                    existed = true;
                    enterprise.put("companyName", ztEnterprise.getDeptName());
                    enterprise.put("ztId", ztEnterprise.getZtId());
                    enterprise.put("updateTime", nowDateTime);
                    toUpdateList.add(enterprise);
                    break;
                }
            }
            if (!existed) {
                HashMap<String, String> map = new HashMap<>();
                map.put("ztId", ztEnterprise.getZtId());
                map.put("companyName", ztEnterprise.getDeptName());
                map.put("orgCode", ztEnterprise.getOrgCode());
                map.put("createTime", nowDateTime);
                map.put("updateTime", nowDateTime);
                toInsertList.add(map);
            }
        });

        deptService.updateEnterprise(toUpdateList);
        deptService.saveEnterprise(toInsertList);
    }

}
