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
					type="text" class="layui-input" name="account"
					placeholder="账号，字母、数字、下划线" />
			</div>
			<div class="layui-form-item in">
				<label class="layui-icon layui-icon-password"></label> <input
					type="password" class="layui-input" name="password"
					placeholder="密码，6到12位数字、字符组成" />
			</div>
			<div class="layui-form-item in">
				<label class="layui-icon layui-icon-password"></label> <input
					type="password" class="layui-input" name="confirmPassword"
					placeholder="确认密码" />
			</div>
			<div class="layui-form-item in">
				<label class="layui-icon layui-icon-star"></label> <input
					type="text" class="layui-input" name="petName" placeholder="昵称" />
			</div>
			<div class="layui-form-item">
				<input type="radio" class="layui-input" name="sex" value="男"
					title="男" checked /> <input type="radio" class="layui-input"
					name="sex" value="女" title="女" />
			</div>
			<div class="layui-form-item">
				<input type="checkbox" class="layui-input" name="hobby[read]"
					value="看书" title="看书" /> <input type="checkbox" class="layui-input"
					name="hobby[sing]" value="唱歌" title="唱歌" /> <input type="checkbox"
					class="layui-input" name="hobby[dai]" value="发呆" title="发呆" />
			</div>
			<div class="layui-form-item">
				<select name="address">
					<option value="">请选择地址</option>
					<option value="蜀国">蜀国</option>
					<option value="魏国">魏国</option>
					<option value="吴国">吴国</option>
					<option value="晋国">晋国</option>
				</select>
			</div>
			<div class="layui-form-item">
				<button class="layui-btn" lay-submit lay-filter="submit">确定</button>
				<button class="layui-btn" id="cancel">取消</button>
			</div>
		</form>
	</div>

	<script type="text/javascript">
    var path='${pageContext.request.contextPath}';
    layui.use(["form", "jquery", "layer"], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form; 
        var isAdd = ${empty um};
        
        function init() {
            $("#cancel").click(closeDialog);
            showData();
        }
        
        function showData() {
        	if (isAdd) {return}
        	var hobby = "${um.hobby}";
        	form.val("addOrUpd", {
        		"account": "${um.account}",
        		"password": "${um.password}",
        		"confirmPassword": "${um.password}",
        		"petName": "${um.petName}",
        		"sex": "${um.sex}",
        		"hobby[read]": hobby.indexOf("看书") > -1 ? true : false,
        		"hobby[sing]": hobby.indexOf("唱歌") > -1 ? true : false,
        		"hobby[dai]": hobby.indexOf("发呆") > -1 ? true : false,
        		"address": "${um.address}"
        	});
        	$("input[name='account']").prop("readonly", true);
        }
        
        formSubmit(appPath + "/UserServlet?action=" + (isAdd ? "add" : "upd"), "submit(submit)", "text", function(data) {
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