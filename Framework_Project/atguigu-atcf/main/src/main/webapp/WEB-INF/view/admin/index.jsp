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
    <title>用户维护</title>
    <%--  引入css样式  --%>
    <%@include file="../common/css.jsp" %>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
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
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;"
                          action="${pageContext.request.contextPath}/admin/select/${page_info.pageNum}" method="post">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="query_condition" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" id="delete_all" class="btn btn-danger" style="float:right;margin-left:10px;">
                        <i
                                class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='${pageContext.request.contextPath}/admin/toEdit/0/${page_info.pageNum}'">
                        <i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <c:if test="${not empty errormsg}">
                        <div class="alert alert-info" role="alert">${errormsg}</div>
                    </c:if>
                    <div>
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th style="text-align: center" width="60">序号</th>
                                <th style="text-align: center" width="30"><input id="select_all" type="checkbox"></th>
                                <th style="text-align: center">账号</th>
                                <th style="text-align: center">名称</th>
                                <th style="text-align: center">邮箱地址</th>
                                <th style="text-align: center" width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${admin_list}" var="admin">
                                <tr>
                                    <td style="text-align: center">${admin.id}</td>
                                    <td style="text-align: center"><input type="checkbox" admin_id="${admin.id}"></td>
                                    <td style="text-align: center">${admin.loginacct}</td>
                                    <td style="text-align: center">${admin.username}</td>
                                    <td style="text-align: center">${admin.email}</td>
                                    <td style="text-align: center">
                                        <button class="btn btn-success btn-xs"><i
                                                class=" glyphicon glyphicon-check"></i></button>
                                        <a href="${pageContext.request.contextPath}/admin/toEdit/${admin.id}/${page_info.pageNum}"
                                           class="btn btn-primary btn-xs"><i
                                                class=" glyphicon glyphicon-pencil"></i></a>
                                        <button id="btn_single_delete" class="btn btn-danger btn-xs"><i
                                                class=" glyphicon glyphicon-remove"></i></button>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <c:if test="${page_info.isFirstPage}">
                                            <li><a class="btn btn-default"
                                                   href="${pageContext.request.contextPath}/admin/index/1">首页</a></li>
                                            <li><a class="btn btn-default" href="#">上一页</a></li>
                                        </c:if>
                                        <c:if test="${!page_info.isFirstPage}">
                                            <li><a class="btn btn-default"
                                                   href="${pageContext.request.contextPath}/admin/index/1">首页</a></li>
                                            <li><a class="btn btn-default"
                                                   href="${pageContext.request.contextPath}/admin/index/${page_info.pageNum-1}">上一页</a>
                                            </li>
                                        </c:if>

                                        <c:forEach items="${page_info.navigatepageNums}" var="page_num">
                                            <c:if test="${page_num == page_info.pageNum}">
                                                <li><a style="background-color: palegreen" class="btn btn-primary"
                                                       href="${pageContext.request.contextPath}/admin/index/${page_num}">${page_num}
                                                    <span class="sr-only">(current)</span></a></li>
                                            </c:if>
                                            <c:if test="${page_num != page_info.pageNum}">
                                                <li><a class="btn btn-default"
                                                       href="${pageContext.request.contextPath}/admin/index/${page_num}">${page_num}
                                                    <span class="sr-only">(current)</span></a></li>
                                            </c:if>
                                        </c:forEach>

                                        <c:if test="${page_info.isLastPage}">
                                            <li><a class="btn btn-default" href="#">下一页</a></li>
                                            <li><a class="btn btn-default"
                                                   href="${pageContext.request.contextPath}/admin/index/${page_info.pages}">末页</a>
                                            </li>

                                        </c:if>
                                        <c:if test="${!page_info.isLastPage}">
                                            <li><a class="btn btn-default"
                                                   href="${pageContext.request.contextPath}/admin/index/${page_info.pageNum+1}">下一页</a>
                                            </li>
                                            <li><a class="btn btn-default"
                                                   href="${pageContext.request.contextPath}/admin/index/${page_info.pages}">末页</a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/view/common/script.jsp" %>

<script src="${pageContext.request.contextPath}/static/script/docs.min.js"></script>
<script type="text/javascript">
    $(function () {

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

        //删除确认提示框
        $(document).on("click", "#btn_single_delete", function () {
            let admin_name = $(this).parent().parent().find("td:eq(3)").text();
            let admin_id = $(this).parent().parent().find("td:eq(0)").text();
            console.log("adminID++>" + admin_id);

            layer.confirm('确定删除【' + admin_name + '】吗？', {
                skin: 'layui-layer-molv', //样式类名
                btn: ['确定', '取消'] //按钮
            }, function () {
                layer.load(1, {
                    shade: [0.1, '#fff']//0.1透明度的白色背景
                });
                window.location.href = "${pageContext.request.contextPath}/admin/delete/" + admin_id + "/" +${page_info.pageNum};
            }, function () {

            });

        });

        //单选框的全选功能
        $("#select_all").click(function () {
            $("tbody input[type='checkbox']").prop("checked", this.checked);
        });

        //全部删除按钮点击事件函数
        $("#delete_all").click(function () {
            let check_box_list = $("tbody input[type='checkbox']:checked");

            if (check_box_list.length === 0) {
                layer.msg("请选择要删除的选项");
                return false;
            }
            let ids = '';
            let array = [];
            $.each(check_box_list,function(index,element){
                let id = $(element).attr("admin_id");
                array.push(id);
            });
            ids = array.join(",");

            layer.confirm('确定删除吗？', {
                skin: 'layui-layer-molv', //样式类名
                btn: ['确定', '取消'] //按钮
            }, function () {
                layer.load(1, {
                    shade: [0.1, '#fff']//0.1透明度的白色背景
                });
                window.location.href = "${pageContext.request.contextPath}/admin/delete_batch/" + ids + "/" +${page_info.pageNum};
            }, function () {
                console.log(ids);
            });
        });


    });
</script>
</body>
</html>

