<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/web/header.jsp"%>
<title>地址维护</title>
<link rel="stylesheet" type="text/css"
	href="<%=appPath%>/web/css/userList.css" />
</head>
<body>
	<div class="layui-collapse">
		<div class="layui-colla-item">
			<h2 class="layui-colla-title">条件查询</h2>
			<div class="layui-colla-content">
				<form class="layui-form select" onsubmit="return false;">
					<div class="layui-input-inline">
						<input type="text" name="code" class="layui-input"
							placeholder="编号">
					</div>
					<div class="layui-inline">
						<select name="parentCode" lay-search></select>
					</div>
					<div class="layui-input-inline">
						<input type="text" name="name" class="layui-input"
							placeholder="名称">
					</div>
					<input type="hidden" name="pageIndex" value="1"> <input
						type="hidden" name="pageLimit" value="5">
					<button class="layui-btn layui-btn-normal" lay-submit
						lay-filter="select" id="select">
						<i class="layui-icon layui-icon-search"></i>
					</button>
				</form>
			</div>
		</div>
	</div>

	<div class="layui-form">
		<a href="javascript:deleteAll();"
			class="layui-btn layui-btn-sm layui-btn-danger">删除选中项</a> <a
			href="javascript:uAdd();"
			class="layui-btn layui-btn-sm layui-btn-normal">添加</a>
		<table class="layui-table">
			<colgroup>
				<col width="5%">
				<col width="5%">
				<col width="15%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th><input type="checkbox" lay-filter="checkAll" title="全选"
						lay-skin="primary"></th>
					<th>序号</th>
					<th>编号</th>
					<th>名称</th>
					<th>上级编号</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tbody"></tbody>
		</table>
		<div id="page"></div>
	</div>

	<script id="dictAdd" type="text/html">
     <tr>
         <td><input type="checkbox" value="{{d.code}}" lay-skin="primary"></td>
         <td>{{d.num}}</td>
         <td>{{d.code}}</td>
         <td>{{d.name}}</td>
         <td>{{d.parentCode}}</td>
         <td>
             <a href="javascript:uDelete('{{d.code}}');" class="layui-btn layui-btn-sm layui-btn-danger">
                 <i class="layui-icon layui-icon-delete"></i>
             </a>
             <a href="javascript:uUpdate('{{d.code}}');" class="layui-btn layui-btn-sm layui-btn-normal">
                 <i class="layui-icon layui-icon-edit"></i>
             </a>
         </td>
     </tr>
    </script>

	<script id="optAdd" type="text/html">
    <option value="{{d.code}}">{{d.name}}</option>
    </script>

	<script type="text/javascript">
    layui.use(["element", "form", "jquery", "laytpl", "layer", "laypage"], function() {
        var $ = layui.jquery;
        var form = layui.form;
        var laytpl = layui.laytpl;
        var layer = layui.layer;
        var laypage = layui.laypage;
        
        function init() {
	       	form.on("checkbox(checkAll)", checkAll);
	       	getOption();
        }
        
        function getOption() {
            ajax(appPath + "/DictServlet", "json", {action:"getAllList"}, function(data) {
                var html = "<option value=''>上级：不限</option>";
                html += "<option value='0000'>省</option>";
                $.each(data, function(index, dm) {
                    html += laytpl($("#optAdd").html()).render(dm);
                });
                $("select[name='parentCode']").html(html);
                form.render("select");
                getList();
            });
        }
        
    	formSubmit(appPath + "/DictServlet?action=getList", "submit(select)", "json", function(data) {
    		var limit = $("input[name='pageLimit']").val();
    		var curr = $("input[name='pageIndex']").val();
			pageInit("page", data.rowCount, limit, curr, function(obj, first) {
				$("input[name='pageLimit']").val(obj.limit);
				$("input[name='pageIndex']").val(obj.curr);
		        if (!first) {
		            getList();
		        }
			});
    		
	        var html = "";
    		var list = data.list;
	        $.each(list, function(index, dom) {
	        	dom.num = index + 1 + (curr-1)*limit;
	            html += laytpl($("#dictAdd").html()).render(dom);
	        });
            $("#tbody").html(html);
	        form.render("checkbox");
	        
    		var leng = list.length;
	        if (curr != 1 && leng < 1) {
	        	$("input[name='pageIndex']").val(1);
	        	getList();
	        } else if (curr == 1 && leng < 1) {
	            layer.msg("没有查到数据", {icon: 5});
	        }
    	});
        
        function checkAll(data) {
        	$("#tbody :checkbox").prop("checked", data.elem.checked);
            form.render("checkbox");
        }
        
        function getList() {
        	$("#select").click();
        	$("input[lay-filter='checkAll']").prop("checked", false);
        }
        
        uDelete = function(code) {
        	layer.confirm("确认删除？", function(index) {
                ajax(appPath + "/DictServlet?action=delete", "text", {code: code}, function(data) {
                	if (data == "") {
                		layer.msg("删除成功", {icon: 6, time: 800}, function() {
                		});
                	} else {
                		layer.msg(data, {icon: 0});
                	}
                	getOption();
                });
        		layer.close(index);
        	});
        }
        
        deleteAll = function() {
        	layer.confirm("确认删除？", function(index) {
        		var data = new Array();
        		$("#tbody :checked").each(function(i) {
        			data[i] = $(this).val();
        		});
        		
        		if (data.length < 1) {
        			layer.msg("没有任何选中项", {icon: 0});
        			return;
        		}
        		
                ajax(appPath + "/DictServlet?action=delete", "text", {code: data}, function(data) {
                	if (data == "") {
                		layer.msg("删除成功", {icon: 6});
                	} else {
                		layer.msg(data, {icon: 0});
                	}
                	getOption();
                });
        		layer.close(index);
        	})
        }
        
        uAdd = function() {
        	openDialog(appPath + "/DictServlet?action=addDialog", "添加", ["500px", "400px"], true, getOption);
        }
        
        uUpdate = function(code) {
        	openDialog(appPath + "/DictServlet?action=updDialog&code=" + code, "修改", ["500px", "400px"], true, getOption);
        }
        
        init();
    });
    </script>
</body>
</html>