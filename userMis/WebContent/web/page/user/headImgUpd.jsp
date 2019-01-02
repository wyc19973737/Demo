<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/web/header.jsp"%>
<title>头像上传</title>
<link rel="stylesheet" type="text/css"
	href="<%=appPath%>/web/css/headImgUpload.css" />
</head>
<body>
	<div class="main">
		<div class="img">
			<img src="">
		</div>
		<div class="btn">
			<button class="layui-btn" id="select">选择图片</button>
			<button class="layui-btn" id="upload">
				<i class="layui-icon layui-icon-upload"></i>上传
			</button>
		</div>
	</div>

	<script type="text/javascript">
    layui.use(["jquery", "layer"], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        upload("#select", appPath + "/UserServlet?action=headImgUpd", "images", "#upload", choose, before, done, error);
        function choose(obj) {
        	obj.preview(function(index, file, result) {
        	    $("img").attr("src", result);
            });
        }
        
        function before(obj) {
        	this.data = {account: "${account}"};
        }
        
        function done(res, index, upload) {
        	if (res.message == "") {
        		layer.msg("上传成功", {icon: 6, time: 800}, closeDialog);
        	} else {
        		error();
        	}
        }
        
        function error(index, upload) {
        	var html = "<i class='layui-icon layui-icon-upload'></i>重新上传";
        	$("#upload").html(html);
        }
    });
    </script>
</body>
</html>