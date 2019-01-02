<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/web/header.jsp"%>
<title>欢迎注册</title>
<link rel="stylesheet" type="text/css"
	href="<%=appPath%>/web/css/register.css" />
</head>
<body class="layui-layout-body">
	<div class="layui-main">
		<!-- logo -->
		<div class="logo">
			<a href="<%=appPath%>/web/page/index.jsp"><img
				src="/image/logo.png"></a>
			<h2>欢迎注册</h2>
		</div>

		<!-- 表单 -->
		<form class="layui-form" onsubmit="return false;">
			<div class="layui-form-item in">
				<label class="layui-icon layui-icon-username"></label> <input
					type="text" class="layui-input" name="account"
					placeholder="账号，字母、数字、下划线" lay-verify="required" autocomplete="off" />
			</div>
			<div class="layui-form-item in">
				<label class="layui-icon layui-icon-password"></label> <input
					type="password" class="layui-input" name="password"
					placeholder="密码，6到12位数字、字符组成" lay-verify="required"
					autocomplete="off" />
			</div>
			<div class="layui-form-item in">
				<label class="layui-icon layui-icon-password"></label> <input
					type="password" class="layui-input" name="confirmPassword"
					placeholder="确认密码" lay-verify="required" autocomplete="off" />
			</div>
			<div class="layui-form-item in">
				<label class="layui-icon layui-icon-star"></label> <input
					type="text" class="layui-input" name="petName" placeholder="昵称"
					lay-verify="required" autocomplete="off" />
			</div>
			<div class="layui-form-item">
				<input type="radio" class="layui-input" name="sex" value="男"
					title="男" checked /> <input type="radio" class="layui-input"
					name="sex" value="女" title="女" />
			</div>
			<div class="layui-form-item">
				<input type="checkbox" class="layui-input" name="hobby" value="看书"
					title="看书" /> <input type="checkbox" class="layui-input"
					name="hobby" value="唱歌" title="唱歌" /> <input type="checkbox"
					class="layui-input" name="hobby" value="发呆" title="发呆" />
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
				<input type="checkbox" title="同意用户协议" lay-skin="primary" checked>
			</div>
			<div class="layui-form-item">
				<button class="layui-btn layui-btn-fluid" lay-submit
					lay-filter="register">注册</button>
			</div>
		</form>
	</div>

	<script type="text/javascript">
	layui.use("layer", function() {
		var layer = layui.layer;
		
		formSubmit(appPath + "/UserServlet?action=register", "submit(register)", "text", function(data) {
	        if (data == "") {
	            layer.msg("注册成功", {icon: 6, time: 800}, function() {
	            	location.href = appPath + "/web/login.jsp";
	            });
	        } else {
	            layer.msg(data, {icon: 0});
	        }
		});
	});
  </script>
</body>
</html>