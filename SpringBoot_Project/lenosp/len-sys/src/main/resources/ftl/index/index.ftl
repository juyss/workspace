<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>项目研发管理平台</title>
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/plugins/font-awesome/css/font-awesome.min.css" media="all"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/build/css/app.css" media="all"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/build/css/themes/default.css" media="all" id="skin" kit-skin/>
    <style>
        <#--前端无聊美化ing-->
        .layui-footer {
            background-color: #2F4056;
        }

        .layui-side-scroll {
            border-right: 3px solid #009688;
        }

        .project-box{
            cursor: pointer;
            color: #1E9FFF;
            border: 1px solid #dddddd;
            height: 70px;
            width: 120px;
            text-align: center;
            padding-top: 30px;
            float: left;
            margin: 3px;
        }
    </style>
</head>

<body class="kit-theme">
<div class="layui-layout layui-layout-admin kit-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">项目研发管理平台</div>
        <div class="layui-logo kit-logo-mobile"></div>
        <div class="layui-hide-xs">

            <!--
            <ul class="layui-nav layui-layout-left kit-nav">
              <li class="layui-nav-item"><a href="javascript:s();">会员管理</a></li>
                <li class="layui-nav-item"><a href="javascript:;" kit-target data-options="{url:'/article/articleList',icon:'&#xe658;',title:'文章管理',id:'966'}">文章管理</a></li>
              <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                  <dd><a href="javascript:;">邮件管理</a></dd>
                  <dd><a href="javascript:;">消息管理</a></dd>
                  <dd><a href="javascript:;">授权管理</a></dd>
                </dl>
              </li>
            </ul>
            -->
        </div>
        <ul class="layui-nav layui-layout-right kit-nav">
            <#if showTenant == false>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon" style="padding: 0 5px;">&#xe653;</i>${projectName}</a>
                </li>
            </#if>
            <li class="layui-nav-item">
                <a href="main"><i class="layui-icon" style="padding: 0 5px;">&#xe68e;</i>切换公司</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <i class="layui-icon">&#xe63f;</i> 皮肤</a>
                </a>
                <dl class="layui-nav-child skin">
                    <dd><a href="javascript:;" data-skin="default" style="color:#393D49;"><i class="layui-icon">&#xe658;</i> 默认</a></dd>
                    <dd><a href="javascript:;" data-skin="orange" style="color:#ff6700;"><i class="layui-icon">&#xe658;</i> 橘子橙</a></dd>
                    <dd><a href="javascript:;" data-skin="green" style="color:#00a65a;"><i class="layui-icon">&#xe658;</i> 春天绿</a></dd>
                    <dd><a href="javascript:;" data-skin="pink" style="color:#FA6086;"><i class="layui-icon">&#xe658;</i> 少女粉</a></dd>
                    <dd><a href="javascript:;" data-skin="blue.1" style="color:#00c0ef;"><i class="layui-icon">&#xe658;</i> 天空蓝</a></dd>
                    <dd><a href="javascript:;" data-skin="red" style="color:#dd4b39;"><i class="layui-icon">&#xe658;</i>枫叶红</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <#assign currentUser = Session["currentPrincipal"]>
                    <#--<img src="${re.contextPath}/images/${currentUser.photo}" class="layui-nav-img">-->${currentUser.username}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" kit-target
                           data-options="{url:'/person',icon:'&#xe658;',title:'基本资料',id:'966'}"><span>基本资料</span></a>
                    </dd>
                    <dd><a href="javascript:;">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="logout"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
        </ul>
    </div>

    <#macro tree data start end>
        <#if (start=="start")>
            <div class="layui-side layui-nav-tree layui-bg-black kit-side">
            <div class="layui-side-scroll">
            <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
            <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
        </#if>
        <#list data as child>
            <#if child.children?size gt 0>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">
                        <i aria-hidden="true" class="layui-icon">${child.icon}</i><span> ${child.name}</span>
                    </a>
                    <dl class="layui-nav-child">
                        <@tree data=child.children start="" end=""/>
                    </dl>
                </li>
            <#else>
                <dd>
                    <a href="javascript:;" kit-target
                       data-options="{url:'${child.url}',icon:'${child.icon}',title:'${child.name}',id:'${child.num?c}'}">
                        <i class="layui-icon">${child.icon}</i><span> ${child.name}</span></a>
                </dd>
            </#if>
        </#list>
        <#if (end=="end")>
            </ul>
            </div>
            </div>
        </#if>
    </#macro>
    <div><@tree data=menu start="start" end="end"/></div>
    <div class="layui-body" <#--style="border:1px solid red;padding-bottom:0;"--> id="container">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;"><i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop">&#xe63e;</i>
            请稍等...
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        2019 &copy; xxx
    </div>
</div>

<#if showTenant == true>
    <div style="position: fixed;top:50px;bottom: 0px;border: 1px solid red;background: #dddddd;width: 100%;z-index: 999;">
        <div style="padding: 20px; background-color: #F2F2F2;">
            <div class="layui-row layui-col-space15">

                <#list tenants as tenant>
                    <div class="layui-col-md4">
                        <div class="layui-card">
                            <div class="layui-card-header">租户信息</div>
                            <div class="layui-card-body">
                                ${tenant.tenant_name}<br>
                                创建时间：${tenant.create_date?string('yyyy-MM-dd hh:mm:ss')}
                                <br/>
                                加入时间：${tenant.join_date?string('yyyy-MM-dd hh:mm:ss')}
                            </div>

                            <div class="layui-card">
                                <div class="layui-card-header">
                                    项目列表
                                    <div style="float: right;">
                                        <button type="button" tid="${tenant.id}" tag="addTenantUser" class="layui-btn layui-btn-sm">邀请成员</button>
                                        <button type="button" tid="${tenant.id}" tag="createProject" class="layui-btn layui-btn-sm layui-btn-normal">创建项目</button>
                                    </div>
                                </div>
                                <div class="layui-card-body" style="height: 130px;overflow: auto;">

                                    <#assign projects = projectMap[tenant.id]>
                                    <#list projects as project>
                                        <div tag="project" tid="${tenant.id}" pid="${project.id}" pname="${project.projectName}" class="project-box">
                                            <a tag="del" href="javascript:;" id="${project.id}" style="position: absolute;margin-left: 50px;margin-top: -30px;">删除</a>
                                            <i class="layui-icon" style="padding: 0 5px;font-size: 30px;margin: auto;">&#${project.icon}</i>
                                            <div style="line-height: normal;">${project.projectName}</div>
                                        </div>
                                    </#list>

                                    <#--<div tag="project" class="project-box">
                                        <i class="layui-icon" style="padding: 0 5px;font-size: 30px;margin: auto;">&#xe702;</i>
                                        <div style="line-height: normal;">学生管理系统2</div>
                                    </div>
                                    <div tag="project" class="project-box">
                                        <i class="layui-icon" style="padding: 0 5px;font-size: 30px;margin: auto;">&#xe653;</i>
                                        <div style="line-height: normal;">学生管理系统2</div>
                                    </div>-->

                                    <#--<div tid="${tenant.id}" pid="123" tag="project" style="cursor: pointer;color: #1E9FFF;">
                                        <i class="layui-icon" style="padding: 0 5px;">&#xe68e;</i>学生管理系统
                                    </div>
                                    <div tid="${tenant.id}" pid="123" tag="project" style="cursor: pointer;color: #1E9FFF;">
                                        <i class="layui-icon" style="padding: 0 5px;">&#xe68e;</i>图书管理系统
                                    </div>
                                    <div tid="${tenant.id}" pid="123" tag="project" style="cursor: pointer;color: #1E9FFF;">
                                        <i class="layui-icon" style="padding: 0 5px;">&#xe68e;</i>考勤管理系统
                                    </div>
                                    <div tid="${tenant.id}" pid="123" tag="project" style="cursor: pointer;color: #1E9FFF;">
                                        <i class="layui-icon" style="padding: 0 5px;">&#xe68e;</i>电商项目
                                    </div>
                                    <div tid="${tenant.id}" pid="123" tag="project" style="cursor: pointer;color: #1E9FFF;">
                                        <i class="layui-icon" style="padding: 0 5px;">&#xe68e;</i>电商项目
                                    </div>
                                    <div tid="${tenant.id}" pid="123" tag="project" style="cursor: pointer;color: #1E9FFF;">
                                        <i class="layui-icon" style="padding: 0 5px;">&#xe68e;</i>电商项目
                                    </div>
                                    <div tid="${tenant.id}" pid="123" tag="project" style="cursor: pointer;color: #1E9FFF;">
                                        <i class="layui-icon" style="padding: 0 5px;">&#xe68e;</i>电商项目
                                    </div>-->
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</#if>

<script src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
<script src="${re.contextPath}/plugin/layui/layui.js"></script>
<script src="${re.contextPath}/plugin/tools/main.js"></script>
<script>

    // 加载项目
    function reloadProjects(){
        window.location.reload();
    }

    $(function () {
        // 创建项目
        $("[tag='createProject']").click(function (){
            var tid = $(this).attr("tid");

            var index = layer.open({
                type: 2,
                area: ['600px', '400px'],
                title: '创建项目',
                content: 'pdms/project/showProject?tenantId=' + tid
            });

        });

        // 邀请成员
        $("[tag='addTenantUser']").click(function (){
            var tid = $(this).attr("tid");

            var index = layer.open({
                type: 2,
                area: ['600px', '400px'],
                title: '邀请成员',
                content: 'pdms/tenant/invite?tenantId=' + tid
            });
        });

        // 点击项目
        $("[tag='project']").click(function () {
            var tid = $(this).attr("tid");
            var pid = $(this).attr("pid");
            var pname = $(this).attr("pname");
            window.location.href = 'main?tenant_id=' + tid + '&project_id=' + pid + '&project_name=' + pname;
        });

        // 删除项目
        $("[tag='del']").click(function (e) {
            var id = $(this).attr("id");

            layer.confirm('确认删除项目?', {icon: 3, title:'提示'}, function(index){
                layer.close(index);
                $.post('pdms/project/removeProject',{id:id},function(data){
                    window.location.reload();
                },'json');
            });

            e.stopPropagation();
        });
    });
</script>
</body>

</html>
