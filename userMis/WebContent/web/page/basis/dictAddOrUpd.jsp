<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/web/header.jsp"%>
<title>信息维护</title>
<link rel="stylesheet" type="text/css"
	href="<%=appPath%>/web/css/register.css" />
</head>
<body>
	<div class="layui-main" style="width: 300px;">
		<form class="layui-form" lay-filter="addOrUpd"
			onsubmit="return false;">
			<div class="layui-form-item in">
				<label class="layui-icon layui-icon-username"></label> <input
					type="text" class="layui-input" name="code"
					placeholder="编号，字母、数字、下划线" />
			</div>
			<div class="layui-form-item in">
				<label class="layui-icon layui-icon-star"></label> <input
					type="text" class="layui-input" name="name" placeholder="名称" />
			</div>
			<div class="layui-form-item">
				<select name="parentCode" lay-search></select>
			</div>
			<div class="layui-form-item">
				<button class="layui-btn" lay-submit lay-filter="submit">确定</button>
				<button class="layui-btn" id="cancel">取消</button>
			</div>
		</form>
	</div>

	<script id="optAdd" type="text/html">
    <option value="{{d.code}}">{{d.name}}</option>
    </script>

	<script type="text/javascript">
    var path='${pageContext.request.contextPath}';
    layui.use(["form", "jquery", "layer", "laytpl"], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form; 
        var laytpl = layui.laytpl;
        var isAdd = ${empty dm};
        
        function init() {
            $("#cancel").click(closeDialog);
            getOption();
        }
        
        function getOption() {
        	ajax(appPath + "/DictServlet", "json", {action:"getAllList"}, function(data) {
        		var html = "<option value=''>无上级</option>";
        		html += "<option value='0000'>省</option>";
        		$.each(data, function(index, dm) {
        			html += laytpl($("#optAdd").html()).render(dm);
        		});
        		$("select[name='parentCode']").html(html);
        		form.render("select");
        		showData();
        	});
        }
        
        function showData() {
        	if (isAdd) {return}
        	form.val("addOrUpd", {
        		"code": "${dm.code}",
        		"name": "${dm.name}",
        		"parentCode": "${dm.parentCode}"
        	});
        	$("input[name='code']").prop("readonly", true);
        }
        
        formSubmit(appPath + "/DictServlet?action=" + (isAdd ? "add" : "upd"), "submit(submit)", "text", function(data) {
        	if (data == "") {
        		layer.msg(isAdd ? "添加成功" : "修改成功", {icon: 6, time: 800}, closeDialog);
        	} else {
        		layer.msg(data, {icon: 0});
        	}
        });
        
        init();
    });
    </script>
</body>
</html>