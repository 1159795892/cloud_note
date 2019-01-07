//笔记本重命名
function renameBook() {
	$("#renamebook_span").html("");
	var $li = $("#book_ul a.checked").parent();
	var bookId = $li.data("bookId");
	var bookName = $("#input_notebook_rename").val().trim();
	var userId = getCookie("userId");
	//获取标题信息
	var $li = $("#book_ul a.checked").parent();
	var Name = $li.text().trim(); 
	var ok = true;
	if (bookName=="") {
		ok = false;
		$("#renamebook_span").html("笔记本命名不能为空");
	}
	if (bookName==Name) {
		ok = false;
		$("#renamebook_span").html("笔记本命名没有改变");
	}
	if (userId == null) {
		ok = false;
		window.location.href = "log_in.html";
	}
	if (ok) {
		$.ajax({
			url : path + "/book/rename.do",
			type : "post",
			data : {
				"bookId" : bookId,
				"bookName":bookName
			},
			dataType : "json",
			success : function(result) {
				if(result.status==1){
					alert(result.msg);
				}
				if(result.status==0){
					var bookName = result.data.cn_notebook_name;
					var sli="";
					sli += '    <i class="fa fa-book" title="online" rel="tooltip-bottom">';
					sli += '    </i>' + bookName;
					$li.find("a").html(sli);
					alert(result.msg);
				}
			},
			error : function() {
				 alert("重命名异常");
			}
		});
	}
}

// 确定创建笔记本
function addBook() {
	var bookName = $("#input_notebook").val().trim();
	var userId = getCookie("userId");
	// 格式检查
	var ok = true;
	if (bookName == "") {
		ok = false;
		$("#notebook_span").html("笔记本命名不能为空");
	}
	if (userId == null) {
		ok = false;
		window.location.href = "log_in.html";
	}
	if (ok) {
		$.ajax({
			url : path + "/book/add.do",
			type : "post",
			data : {
				"userId" : userId,
				"bookName" : bookName
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 0) {
					closeAlertWindow();
					var bookId = result.data.cn_notebook_id;
					var bookName = result.data.cn_notebook_name;
					createBookLi(bookId, bookName);
					// 提示成功
					alert(result.msg);
				}
			},
			error : function() {
				alert("创建笔记本异常");
			}
		});
	}
};
function loadUserBooks() {
	// 根据用户id显示笔记本列表
	var userId = getCookie("userId");
	// 判断吧是否获取到有效的userId
	if (userId == null) {
		window.location.href = "log_in.html";
	} else {// 发送ajax请求
		$.ajax({
			url : path + "/book/loadBookds.do",
			type : "post",
			data : {
				"userId" : userId
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 0) {
					// 获取返回的笔记本集合
					var books = result.data;
					// 循环生成列表元素
					for (var i = 0; i < books.length; i++) {
						var bookId = // 获取笔记本ID
						books[i].cn_notebook_id;
						var bookName = // 获取笔记本名称
						books[i].cn_notebook_name;
						// 创建笔记本列表li
						createBookLi(bookId, bookName);
					}
				}
			},
			error : function() {
				alert("加载笔记本加载失败");
			}
		});
	}
}
function createBookLi(bookId, bookName) {
	// 构建列表li元素
	var sli = "";
	sli += '<li class="online">';
	sli += '  <a>';
	sli += '    <i class="fa fa-book" title="online" rel="tooltip-bottom">';
	sli += '    </i>' + bookName;
	sli += '  </a>';
	sli += '</li>';
	// 将sli字符串转换成JQuery对象li元素
	var $li = $(sli);
	// 将bookId值与JQuery对象绑定
	$li.data("bookId", bookId);
	// 将li元素添加到ul列表中
	$("#book_ul").append($li);
}