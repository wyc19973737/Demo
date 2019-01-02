var appPath = "/userMis";

function formSelect(filter, url, func) {
	layui.use("form", function() {
		layui.form.on(filter, function(data) {
			ajax(url, "json", {parentCode:data.value}, func);
		});
	});
}

function formSubmit(url, submit, dataType, func) {
	layui.use("form", function() {
		layui.form.on(submit, function(data) {
			ajax(url, dataType, data.field, func);
			return false;
		});
	});
}

function ajax(url, dataType, field, func) {
	layui.use("jquery", function() {
		layui.$.ajax({
			type: "post",
			url: url,
			dataType: dataType,
			data: field,
			traditional: true,
			success: func
		});
	});
}

function openDialog(url, title, area, resize, func) {
	layui.use("layer", function() {
		layui.layer.open({
    		type: 2,
    		content: url,
    		title: title,
    		area: area == null ? ["500px", "520px"] : area,
    		closeBtn: 1,
    		resize: resize,
    		maxmin: true,
    		end: func
    	});
	});
}

function pageInit(elem, rowCount, limit, curr, func) {
	layui.use("laypage", function() {
		layui.laypage.render({
			elem: elem,
			count: rowCount,
			groups: 3,
			limit: limit,
			curr: curr,
			limits: [5, 10, 20, 30, 40],
			layout: ["count", "prev", "page", "next", "limit", "skip"],
			jump: func
		});
	});
}

function upload(elem, url, accept, bindAction, choose, before, done, error) {
	layui.use("upload", function() {
		var upload = layui.upload;
    	var uploadInst = upload.render({
    		elem: elem,
    		url: url,
    		accept: accept,
    		acceptMime: (accept=="images" ? "image" : accept) + "/*",
    		auto: false,
    		bindAction: bindAction,
    		choose: choose,// 选择文件回调
    		before: before,// 上传文件前回调
    		done: done,// 上传成功回调
    		error: error// 上传失败回调
    	});
	});
}

function closeDialog(index) {
	layui.use("layer", function() {
		var layer = layui.layer;
		var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
	});
}
