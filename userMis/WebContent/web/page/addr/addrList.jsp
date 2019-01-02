<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/web/header.jsp"%>
<title>用户信息</title>
<link rel="stylesheet" type="text/css"
	href="<%=appPath%>/web/css/userList.css" />
</head>
<body>
	<div class="layui-collapse">
		<div class="layui-colla-item">
			<h2 class="layui-colla-title">条件查询</h2>
			<div class="layui-colla-content">
				<form class="layui-form select" onsubmit="return false;">
					<div class="layui-form-item">
						<div class="layui-input-inline">
							<select name="account" lay-search></select>
						</div>
						<div class="layui-input-inline">
							<input type="text" name="name" class="layui-input"
								placeholder="收货人">
						</div>
						<div class="layui-input-inline">
							<input type="text" name="phone" class="layui-input"
								placeholder="电话">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-inline">
							<select name="addr1Code" lay-filter="addr1" lay-search></select>
						</div>
						<div class="layui-input-inline">
							<select name="addr2Code" lay-filter="addr2" lay-search>
								<option value="">市：不限</option>
							</select>
						</div>
						<div class="layui-input-inline">
							<select name="addr3Code" lay-filter="addr3" lay-search>
								<option value="">区：不限</option>
							</select>
						</div>
						<div class="layui-input-inline">
							<input type="text" name="addr4" class="layui-input"
								placeholder="详细地址">
						</div>
						<input type="hidden" name="pageIndex" value="1"> <input
							type="hidden" name="pageLimit" value="5">
						<button class="layui-btn layui-btn-normal" lay-submit
							lay-filter="select" id="select">
							<i class="layui-icon layui-icon-search"></i>
						</button>
					</div>
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
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="5%">
				<col width="5%">
				<col width="5%">
				<col width="15%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th><input type="checkbox" lay-filter="checkAll" title="全选"
						lay-skin="primary"></th>
					<th>序号</th>
					<th>账号</th>
					<th>收货人</th>
					<th>电话</th>
					<th>省</th>
					<th>市</th>
					<th>区</th>
					<th>详细地址</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tbody"></tbody>
		</table>
		<div id="page"></div>
	</div>

	<script id="addrAdd" type="text/html">
     <tr>
         <td><input type="checkbox" value="{{d.id}}" lay-skin="primary"></td>
         <td>{{d.num}}</td>
         <td>{{d.account}}</td>
         <td>{{d.name}}</td>
         <td>{{d.phone}}</td>
         <td>{{d.addr1}}</td>
         <td>{{d.addr2}}</td>
         <td>{{d.addr3}}</td>
         <td>{{d.addr4}}</td>
         <td>
             <a href="javascript:uDelete({{d.id}});" class="layui-btn layui-btn-sm layui-btn-danger">
                 <i class="layui-icon layui-icon-delete"></i>
             </a>
             <a href="javascript:uUpdate({{d.id}});" class="layui-btn layui-btn-sm layui-btn-normal">
                 <i class="layui-icon layui-icon-edit"></i>
             </a>
         </td>
     </tr>
    </script>

	<script id="doptAdd" type="text/html">
    <option value="{{d.code}}">{{d.name}}</option>
    </script>

	<script id="uoptAdd" type="text/html">
    <option value="{{d.account}}">{{d.petName}}({{d.account}})</option>
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
	       	getAllOpt();
        }
        
        function getAllOpt() {
        	getOption("/UserServlet", "账号：不限", "select[name='account']", "#uoptAdd");
        	getOption("/DictServlet?parentCode=0000", "省：不限", "select[name='addr1Code']", "#doptAdd");
            getList();
        }
        
        function getOption(path, first, elem, id) {
            ajax(appPath + path, "json", {action:"getAllList"}, function(data) {
                var html = "<option value=''>"+first+"</option>";
                $.each(data, function(index, dm) {
                    html += laytpl($(id).html()).render(dm);
                });
                $(elem).html(html);
                form.render("select");
            });
        }
        
        formSelect("select(addr1)", appPath + "/DictServlet?action=getAllList", function(data) {
                var html = "<option value=''>市：不限</option>";
                $.each(data, function(index, dm) {
                    html += laytpl($("#doptAdd").html()).render(dm);
                });
                $("select[name='addr2Code']").html(html);
                form.render("select");
        });
        
        formSelect("select(addr2)", appPath + "/DictServlet?action=getAllList", function(data) {
                var html = "<option value=''>区：不限</option>";
                $.each(data, function(index, dm) {
                    html += laytpl($("#doptAdd").html()).render(dm);
                });
                $("select[name='addr3Code']").html(html);
                form.render("select");
        });
        
    	formSubmit(appPath + "/AddrServlet?action=getList", "submit(select)", "json", function(data) {
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
	            html += laytpl($("#addrAdd").html()).render(dom);
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
        
        uDelete = function(id) {
        	layer.confirm("确认删除？", function(index) {
                ajax(appPath + "/AddrServlet?action=delete", "text", {id: id}, function(data) {
                	if (data == "") {
                		layer.msg("删除成功", {icon: 6, time: 800}, function() {
                		});
                	} else {
                		layer.msg(data, {icon: 0});
                	}
               		getList();
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
        		
                ajax(appPath + "/AddrServlet?action=delete", "text", {account: data}, function(data) {
                	if (data == "") {
                		layer.msg("删除成功", {icon: 6});
                	} else {
                		layer.msg(data, {icon: 0});
                	}
               		getList();
                });
        		layer.close(index);
        	})
        }
        
        uAdd = function() {
        	openDialog(appPath + "/AddrServlet?action=addDialog", "添加", ["750px", "450px"], true, getList);
        }
        
        uUpdate = function(id) {
        	openDialog(appPath + "/AddrServlet?action=updDialog&id=" + id, "修改",
        			["750px", "450px"], true, getList);
        }
        
        init();
    });
    </script>
</body>
</html>