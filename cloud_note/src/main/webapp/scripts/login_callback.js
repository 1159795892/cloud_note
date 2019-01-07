$(function() {
	$("#changePassword").click(changePassword);
	$("#back").click(backEdit);
});

function changePassword() {
	var userId=getCookie("userId");
	var password = $("#last_password").val().trim();
	var newpwd = $("#new_password").val().trim();
	var final_password = $("#final_password").val().trim();
	$("#warning_1 span").html("");
	$("#warning_2 span").html("");
	$("#warning_3 span").html("");

	var ok = true;
	if (userId == "") {
		ok = false;
		$("#warning_1").show();
		$("#warning_1 span").html("用验证失效,请重新登录");
		window.location.href = "edit.html";
	}
	if (password == "") {
		ok = false;
		$("#warning_1").show();
		$("#warning_1 span").html("密码不能为空");
	}
	if (newpwd == "") {
		ok = false;
		$("#warning_2").show();
		$("#warning_2 span").html("密码不能为空");
	} else if (newpwd.length >= 0 && newpwd.length < 6) {
		ok = false;
		$("#warning_2 span").html("密码不能小于6位");
	}
	if (newpwd != final_password) {
		ok = false;
		$("#warning_3").show();
		$("#warning_3 span").html("密码输入不一致");
	}
	if (ok) {
		$.ajax({
			url : path + "/user/chang_pwd.do",
			type : "post",
			data : {
				"userId" : userId,
				"password" : password,
				"newpwd":newpwd
			},
			dataType : "json",
			success : function(result) {
				if(result.status==1){
					$("#warning_1").show();
					$("#warning_1 span").html("原始密码错误");
				}
				if (result.status==0) {
					window.location.href = "edit.html";
				}
			},
			error : function() {
				$("#warning_1").show();
				$("#warning_1 span").html("修改密码异常");
			}
		});
	}

}

function backEdit() {
	//window.location.href = "edit.html";
	//window.history.go(-1);
	window.history.back();location.reload();
}