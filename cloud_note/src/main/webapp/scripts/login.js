//主处理方法
$(function() {// 加载完body以后调用该函数
	// 登录功能事件绑定
	$("#login").click(userLogin);
	// 注册功能事件绑定
	$("#regist_button").click(regist);

	// 监听Enter键事件
	$(document).keyup(function(event) {
		if (event.keyCode == 13) {
			$("#login").click();
		}
	});

});

function userLogin() {
	// 测试绑定事件
	// alert("1");
	// 获取参数
	var name = $("#count").val().trim();
	var password = $("#password").val().trim();
	$("#count_span").html("");
	$("#password_span").html("");
	// alert(name+","+password);
	// 格式检测
	var ok = true;
	if (name == "") {
		$("#count_span").html("用户名不能为空");
		ok = false;
	}
	if (password == "") {
		$("#password_span").html("密码不能为空");
		ok = false;
	}
	if (ok) {// 检测格式通过
		// 发送请求
		$.ajax({
			url : path + "/user/login.do",
			type : "post",
			data : {
				"name" : name,
				"password" : password
			},
			dataType : "json",
			success : function(result) {
				// result是服务器返回的JSON结果
				if (result.status == 0) {
					// 将用户信息保存到Cookie
					var userId = result.data.cn_user_id;
					addCookie("userId", userId, 2);
					window.location.href = "edit.html";
				} else if (result.status == 1) {
					$("#count_span").html(result.msg);
				} else if (result.status == 2) {
					$("#password_span").html(result.msg);
				}
			},
			error : function() {
				alert("网络繁忙,请稍后再试");
			}

		});
	}
}

function regist() {
	// 测试绑定事件
	// alert("1");
	// 获取参数
	var name = $("#regist_username").val().trim();
	var nick = $("#nickname").val().trim();
	var password = $("#regist_password").val().trim();
	var final_password = $("#final_password").val().trim();
	// alert(name+","+nickname+","+password1+","+password2);
	$("#warning_1 span").html("");
	$("#warning_2 span").html("");
	$("#warning_3 span").html("");
	// 格式数据检测
	// 用户数据检测
	var ok = true;
	if (name == "") {
		ok = false;
		$("#warning_1").show();
		$("#warning_1 span").html("用户不能为空");
	}
	// 检测密码1:非空2不能小于6位
	if (password == "") {
		ok = false;
		$("#warning_2").show();
		$("#warning_2 span").html("密码不能为空");
	} else if (password.length >= 0 && password.length < 6) {
		ok = false;
		$("#warning_2 span").html("密码不能小于6位");
	}
	// 检测确认密码1非空2是否一致
	if (final_password != password) {
		ok = false;
		$("#warning_3").show();
		$("#warning_3 span").html("两次密码不一致");
	}
	if (ok) {
		$.ajax({// 数据校验通过
			url : path + "/user/add.do",
			type : "post",
			data : {
				"name" : name,
				"nick" : nick,
				"password" : password
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 0) {// 成功
					alert(result.msg);// 提示成功
					$("#back").click();// 转向登录界面
				} else if (result.status == 1) {// 用户名被占
					$("#warning_1").show();
					$("#warning_1 span").html(result.msg);
				}
			},
			error : function() {
				alert("注册失败");
			}
		});
	}
}