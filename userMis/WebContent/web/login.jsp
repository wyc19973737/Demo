<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/web/header.jsp"%>
<title>欢迎登录</title>
<link rel="stylesheet" type="text/css"
	href="<%=appPath%>/web/css/login.css" />
</head>
<body class="layui-layout-body">
	<!-- 页面头部 -->
	<div class="title">
		<a href="<%=appPath%>/web/page/index.jsp"><img
			src="/image/logo.png" class="logo"></a>
		<h2>欢迎登录</h2>
	</div>
	<hr>

	<!-- 页面主体 -->
	<div class="body">
		<div class="img" style="background-image: url('/image/login.jpg');">
			<div class="d-form">
				<div class="layui-form-item">
					<h2>账户登录</h2>
				</div>
				<hr>
				<!-- 登陆表单 -->
				<form class="layui-form" onsubmit="return false;">
					<div class="layui-form-item">
						<label class="layui-icon layui-icon-username"></label> <input
							type="text" class="layui-input" name="account" placeholder="账号"
							lay-verify="required" autocomplete="off" />
					</div>
					<div class="layui-form-item">
						<label class="layui-icon layui-icon-password"></label> <input
							type="password" class="layui-input" name="password"
							placeholder="密码" lay-verify="required" autocomplete="off" />
					</div>
					<div class="layui-form-item">
						<label class="layui-icon layui-icon-vercode"></label> <input
							type="text" id="authCode" class="layui-input" name="authCode"
							lay-verify="required" placeholder="验证码" /> <img
							src="<%=appPath%>/AuthCodeServlet" class="auth-code"
							onclick="this.src='<%=appPath%>/AuthCodeServlet?'+Math.random();">
					</div>
					<div class="layui-form-item">
						<button class="layui-btn layui-btn-fluid" lay-submit
							lay-filter="login">登陆</button>
					</div>
				</form>
				<hr>
				<div class="layui-form-item">
					<a href="<%=appPath%>/web/register.jsp">立即注册</a> <a
						href="javascript:;" class="forgot">忘记密码</a>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
  layui.use("layer", function() {
	  var layer = layui.layer;
	  formSubmit(appPath + "/UserServlet?action=login", "submit(login)", "text", function(data) {
		  if (data == "") {
			  layer.msg("登录成功", {icon: 6, time: 800}, function() {
				  location.href = appPath + "/web/page/index.jsp";
			  });
		  } else {
			  layer.msg(data, {icon: 0});
		  }
	  });
  });
  </script>
</body>
</html>