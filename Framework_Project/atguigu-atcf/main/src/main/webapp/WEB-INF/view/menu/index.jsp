<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/10/14
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>菜单维护</title>
    <%--  引入css样式  --%>
    <%@include file="../common/css.jsp" %>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ztree/zTreeStyle.css">

    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }

        table tbody tr:nth-child(odd) {
            background: #F4F4F4;
        }

        table tbody td:nth-child(even) {
            color: #C00;
        }
    </style>
</head>

<body>

<%-- 引入头部 --%>
<%@include file="../common/admin_header.jsp" %>

<div class="container-fluid">
    <div class="row">

        <%--    引入侧边栏    --%>
            <%@include file="../common/admin_sidebar.jsp"%>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i>菜单维护</h3>
                </div>

                <%--    树形菜单    --%>
                <div class="panel-body">
                    <ul id="ztree_menu" class="ztree"></ul>
                </div>

            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/view/common/script.jsp" %>
<script src="${pageContext.request.contextPath}/static/ztree/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/script/docs.min.js"></script>
<script type="text/javascript">
    $(function () {
        let result = '';
        let index;

        //发起异步请求获取树形菜单数据
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/menu/menus",
            data: "",
            beforeSend: function(){
                index = layer.load(0, {shade: false});
            },
            success: function(msg) {
                layer.close(index);
                result = msg;
                console.log("异步请求完成，获取数据："+result);
                showMenuTree(result);
            }
        });


        //侧边栏点击事件
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });

        //展示树形菜单
        function showMenuTree(result) {

            console.log("页面展示函数:"+result);

            //属性菜单设置
            let setting = {
                            data: {
                                simpleData: {
                                        enable: true,
                                        idKey: "id",
                                        pIdKey: "pid",
                                        rootPId: 0
                                }
                            }
            };

            //添加根节点
            result.push({"id":0,"name":"控制菜单","icon":"glyphicon glyphicon-dashboard","open":true});

            $.fn.zTree.init($("#ztree_menu"), setting, result);

            let treeObj = $.fn.zTree.getZTreeObj("ztree_menu");
            treeObj.expandAll(true);
        }

    });
</script>
</body>
</html>