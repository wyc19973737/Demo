<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/web/header.jsp"%>
<title>主页</title>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">UserMis</div>
			<!-- 头部区域 -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a
					href="javascript:location.reload();"><i
						class="layui-icon layui-icon-website"></i></a></li>
				<li class="layui-nav-item"><a href="javascript:;"
					class="open-url" id="refresh"
					data-url="/web/page/user/userList.jsp"> <i
						class="layui-icon layui-icon-refresh"></i>
				</a></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="/image/headImg/${user.headImg}" class="layui-nav-img">
						${user.petName}
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:uUpdate('${user.account}');">修改资料</a>
						</dd>
						<dd>
							<a href="javascript:imgUpdate('${user.account}');">修改头像</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="javascript:;">退了</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域 -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed"><a
						href="javascript:;">不知道叫什么好</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" class="open-url"
									data-url="/web/page/user/userList.jsp">用户信息</a>
							</dd>
							<dd>
								<a href="javascript:;" class="open-url"
									data-url="/web/page/addr/addrList.jsp">收货地址</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">基础数据</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" class="open-url"
									data-url="/web/page/basis/addrDictList.jsp">地址维护</a>
							</dd>
							<dd>
								<a href="javascript:;" class="open-url" data-url="/web/404.jsp">列表二</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;"
						class="open-url" data-url="/web/404.jsp">其它</a></li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<iframe name="frame" style="width: 100%; height: 100%"
				src="<%=appPath%>/web/page/user/userList.jsp"></iframe>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			© UserMis.com
		</div>
	</div>

	<script type="text/javascript">
    layui.use(["element", "jquery"], function() {
    	var $ = layui.jquery;
    	$(".open-url").click(function() {
    		var url = $(this).data("url");
   		    $("#refresh").data("url",url)
    		window.open(appPath + url, "frame");
    	});
    	
    	uUpdate = function(account) {
            openDialog(appPath + "/UserServlet?action=updDialog&account=" + account, "修改", null, false, function() {
            	location.reload();
            });
        }
    	
    	imgUpdate = function(account) {
            openDialog(appPath + "/UserServlet?action=imgDialog&account=" + account, "上传头像", null, false, function() {
            	location.reload();
            });
    	}
    	
    });
    </script>
</body>
</html>