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
    <title>角色维护</title>
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
        <%@include file="../common/admin_sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 角色列表</h3>
                </div>
                <div class="panel-body">
                    <button type="button" id="delete_all" class="btn btn-danger" style="float:right;margin-left:10px;">
                        <i class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='${pageContext.request.contextPath}/role/'">
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
                                <th style="text-align: center">名称</th>
                                <th style="text-align: center" width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                            <tfoot>

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
        //获取分页数据,包含数据和分页条信息
        getData();

        //侧边栏点击事件
        $(".list-group-item").click(function () {
            if ($(this).find("ul")) {
                $(this).toggleClass("tree-closed");
                if ($(this).hasClass("tree-closed")) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });

        //单选框的全选功能
        $("#select_all").click(function () {
            $("tbody input[type='checkbox']").prop("checked", this.checked);
        });

        //删除确认提示框
        $(document).on("click", "#btn_single_delete", function () {
            let role_name = $(this).parent().parent().find("td:eq(2)").text();
            let role_id = $(this).parent().parent().find("td:eq(0)").text();
            console.log("adminID++>" + role_id);

            layer.confirm('确定删除【' + role_name + '】吗？', {
                skin: 'layui-layer-molv', //样式类名
                btn: ['确定', '取消'] //按钮
            }, function () {
                layer.load(1, {
                    shade: [0.1, '#fff']//0.1透明度的白色背景
                });
                window.location.href = "${pageContext.request.contextPath}/role/delete/" + role_id;
            }, function () {

            });
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
            $.each(check_box_list, function (index, element) {
                let id = $(element).attr("role_id");
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
                window.location.href = "${pageContext.request.contextPath}/role/deleteBatch/" + ids;
            }, function () {
                console.log(ids);
            });
        });
    });

    //获取角色数据
    function getData() {
        let result = '';
        let index;

        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/role/roles",
            data: "pageNum=0",
            beforeSend: function () {
                index = layer.load(0, {shade: false});
            },
            success: function (msg) {
                layer.close(index);
                result = msg;
                showData(result);
                console.log("初始化完成！！！");
            }
        });
    }

    //展示数据
    function showData(result) {
        let role_list = result.list;
        let context = '';
        $.each(role_list, function (index, role) {
            console.log("单个角色==>" + role);
            context += '<tr>';
            context += '    <td style="text-align: center">' + role.id + '</td>';
            context += '    <td style="text-align: center"><input type="checkbox" role_id="' + role.id + '"></td>';
            context += '    <td style="text-align: center">' + role.name + '</td>';
            context += '    <td style="text-align: center">';
            context += '        <button class="btn btn-success btn-xs"><i';
            context += '                class=" glyphicon glyphicon-check"></i></button>';
            context += '        <a href=""';
            context += '           class="btn btn-primary btn-xs"><i';
            context += '                class=" glyphicon glyphicon-pencil"></i></a>';
            context += '        <button id="btn_single_delete" class="btn btn-danger btn-xs"><i';
            context += '                class=" glyphicon glyphicon-remove"></i></button>';
            context += '    </td>';
            context += '</tr>';
        });
        $("tbody").html(context);
    }
</script>

</body>
</html>

