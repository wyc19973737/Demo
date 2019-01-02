<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/web/header.jsp"%>
<title>信息维护</title>
</head>
<body>
	<div class="layui-main" style="width: 700px; margin-top: 20px;">
		<form class="layui-form" lay-filter="addOrUpd"
			onsubmit="return false;">
			<div class="layui-form-item">
				<label class="layui-form-label">账号</label>
				<div class="layui-input-inline">
					<select name="account" lay-search></select>
				</div>
				<label class="layui-form-label">收货人</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" name="name"
						placeholder="收货人" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">电话</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" name="phone"
						placeholder="电话" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">省</label>
				<div class="layui-input-inline">
					<select name="addr1Code" lay-filter="addr1" lay-search></select>
				</div>
				<label class="layui-form-label">市</label>
				<div class="layui-input-inline">
					<select name="addr2Code" lay-filter="addr2" lay-search></select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">区</label>
				<div class="layui-input-inline">
					<select name="addr3Code" lay-search></select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">详细地址</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input" name="addr4"
						placeholder="街道、小区">
				</div>
			</div>
			<div class="layui-form-item" style="padding-left: 35%">
				<button class="layui-btn" lay-submit lay-filter="submit">确定</button>
				<button class="layui-btn" id="cancel">取消</button>
			</div>
		</form>
	</div>

	<script id="doptAdd" type="text/html">
    <option value="{{d.code}}">{{d.name}}</option>
    </script>

	<script id="uoptAdd" type="text/html">
    <option value="{{d.account}}">{{d.petName}}({{d.account}})</option>
    </script>

	<script type="text/javascript">
    var path='${pageContext.request.contextPath}';
    layui.use(["form", "jquery", "layer", "laytpl"], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form; 
        var laytpl = layui.laytpl;
        var isAdd = ${empty am};
        
        function init() {
            $("#cancel").click(closeDialog);
            getAllOpt();
        }
        
        function getAllOpt() {
            getOption("/UserServlet", "select[name='account']", "#uoptAdd");
            getOption("/DictServlet?parentCode=0000", "select[name='addr1Code']", "#doptAdd");
        }
        
        function setOption(elem, id, data) {
            	var html = "<option value=''>请选择</option>";
                $.each(data, function(index, dm) {
                    html += laytpl($(id).html()).render(dm);
                });
                $(elem).html(html);
                form.render("select");
        }
        
        function getOption(path, elem, id) {
            ajax(appPath + path, "json", {action:"getAllList"}, function(data) {
            	setOption(elem, id, data);
            	showData();
            	
            });
        }
        
        formSelect("select(addr1)", appPath + "/DictServlet?action=getAllList", function(data) {
        	setOption("select[name='addr2Code']", "#doptAdd", data);
        	$("select[name='addr3Code']").empty();
            form.render("select");
            showData();
        });
        
        formSelect("select(addr2)", appPath + "/DictServlet?action=getAllList", function(data) {
        	setOption("select[name='addr3Code']", "#doptAdd", data);
        	showData();
        });
        
        function showData() {
        	if (isAdd) {return}
        	form.val("addOrUpd", {
        		"account": "${am.account}",
        		"name": "${am.name}",
        		"phone": "${am.phone}",
        		"addr1Code": "${am.addr1Code}",
        		"addr2Code": "${am.addr2Code}",
        		"addr3Code": "${am.addr3Code}",
        		"addr4": "${am.addr4}"
        	});
        }
        
        formSubmit(appPath + "/AddrServlet?action=" + (isAdd ? "add" : "upd&id=${am.id}"), "submit(submit)",
        		"text", function(data) {
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