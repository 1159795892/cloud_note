//转移笔记
function moveNote(){
	 //获取请求参数
	 //获取要转移的笔记ID
	 var $li = $("#note_ul a.checked").parent();
	 var noteId = $li.data("noteId");
	 //获取要转入的笔记本ID
	 var bookId = $("#moveSelect").val();
	 //发送Ajax请求
	 $.ajax({
		 url:path+"/note/move.do",
		 type:"post",
		 data:{"noteId":noteId,"bookId":bookId},
		 dataType:"json",
		 success:function(result){
			 if(result.status==0){
				 //移除笔记li
				 $li.remove();
				 //提示成功
				 alert(result.msg);
			 }
		 },
		 error:function(){
			 alert("转移笔记异常");
		 }
	 });
 };

//查看搜索结果列表的笔记信息
function previewNote() {
	// 获取请求参数
	var shareId = $(this).data("shareId");
	// 发送Ajax请求
	$.ajax({
		url : path + "/note/load_share.do",
		type : "post",
		data : {
			"shareId" : shareId
		},
		dataType : "json",
		success : function(result) {
			var share = result.data;
			if (result.status == 1) {
				alert(result.msg);
			}
			if (result.status == 0) {
				var title = share.cn_share_title;
				var body = share.cn_share_body;
				$("#noput_note_title").html(title);
				$("#noput_note_title").next().html(body);
				// 切换显示
				$("#pc_part_3").hide();
				$("#pc_part_5").show();
			}

		},
		error : function() {
			alert("查看搜索结果异常");
		}

	});
}
//查看回收站列表的笔记信息
function seeRollbackNote() {
	// 获取请求参数
	var noteId = $(this).data("noteId");
	// 发送Ajax请求
	$.ajax({
		url : path + "/note/load.do",
		type : "post",
		data : {
			"noteId" : noteId
		},
		dataType : "json",
		success : function(result) {
			var note = result.data;
			if (result.status == 1) {
				alert(result.msg);
			}
			if (result.status == 0) {
				var title = note.cn_note_title;
				var body = note.cn_note_body;
				$("#noput_note_title").html(title);
				$("#noput_note_title").next().html(body);
				// 切换显示
				$("#pc_part_3").hide();
				$("#pc_part_5").show();
			}
			
		},
		error : function() {
			alert("查看搜索结果异常");
		}
		
	});
}
// 删除笔记
function deleteNote() {
	// 获取请求参数
	var $li = $("#note_ul a.checked").parent();
	var noteId = $li.data("noteId");
	$.ajax({
		url : path + "/note/delete.do",
		type : "post",
		data : {
			"noteId" : noteId
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				// 删除li
				$li.remove();
				// 提示成功
				alert(result.msg);
			}
		},
		error : function() {
			alert("删除笔记异常");
		}
	});
}
// 分享笔记
function shareNote() {
	// 获取noteId
	var $li = $(this).parents("li");
	var noteId = $li.data("noteId");
	// 发送Ajax请求
	$.ajax({
		url : path + "/note/share.do",
		type : "post",
		data : {
			"noteId" : noteId
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				// 添加分享图标
				var img = '<i class="fa fa-sitemap"></i>';
				$li.find(".btn_slide_down").before(img);
				alert(result.msg);
			}
			if (result.status == 1) {
				// 提示已分享过
				alert(result.msg);
			}
		},
		error : function() {
			alert("分享异常");
		}
	});
}
// 隐藏笔记菜单
function hideNoteMenu() {
	// 隐藏所有笔记菜单
	$("#note_ul div").hide();
};
// 弹出笔记菜单
function popNoteMenu() {
	// 隐藏所有笔记菜单
	$("#note_ul div").hide();
	// 显示点击的笔记菜单
	// var $menu=$(this).parents("li").find("div");
	var $menu = $(this).parent().next();
	$menu.slideDown(100);
	// 设置点击笔记选中效果
	$("#note_ul a").removeClass("checked");
	$(this).parent().addClass("checked");
	// 阻止事件向li,body冒泡
	return false;
};
// 增加笔记的创建按钮处理
function addNote() {
	// 获取请求参数
	// 获取标题
	var title = $("#input_note").val().trim();
	// 获取用户ID
	var userId = getCookie("userId");
	// 获取笔记本ID
	var $li = $("#book_ul a.checked").parent();
	var bookId = $li.data("bookId");
	var ok = true;
	if (title == "") {// 判断是否为空
		ok = false;
		$("#note_span").html("标题不能为空");
	}
	if (userId == null) {
		ok = false;
		window.location.href = "log_in.html";
	}
	if (ok) {
		$.ajax({
			url : path + "/note/add.do",
			type : "post",
			data : {
				"userId" : userId,
				"bookId" : bookId,
				"title" : title
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 0) {
					// 关闭对话框
					closeAlertWindow();
					// 生成笔记列表li
					var noteId = result.data.cn_note_id;// 获取服务器返回的笔记ID
					var notetitle = result.data.cn_note_title;// 获取服务器返回的笔记ID
					createNoteLi(noteId, notetitle);
					// 弹出提示
					alert(result.msg);
				}
			},
			error : function() {
				alert("添加笔记异常!");
			}
		});
	}
};
// "保存笔记"按钮的处理
function updateNote() {
	// 获取请求参数
	var title = $("#input_note_title").val().trim();
	var body = um.getContent();
	var $li = // 获取选中的笔记li元素
	$("#note_ul a.checked").parent();
	var noteId = $li.data("noteId");
	// 清除上次提示信息
	$("#note_title_span").html("");
	// 检查格式
	if ($li.length == 0) {
		alert("请选择要保存的笔记");
	} else if (title == "") {
		$("#note_title_span").html("<font color='red'>标题不能为空</font>");
	} else {
		// 发送Ajax请求
		$
				.ajax({
					url : path + "/note/update.do",
					type : "post",
					data : {
						"noteId" : noteId,
						"title" : title,
						"body" : body
					},
					dataType : "json",
					success : function(result) {
						if (result.status == 0) {
							// 更新列表li中标题
							var sli = "";
							sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
							sli += title;
							sli += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
							// 将选中li元素的a内容替换
							$li.find("a").html(sli);
							// 提示成功
							alert(result.msg);
						}
					},
					error : function() {
						alert("保存笔记异常");
					}
				});
	}
};
// 显示笔记信息
function loadNote() {
	// 切换显示
	$("#pc_part_3").show();
	$("#pc_part_5").hide();
	// 设置选中效果
	$("#note_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	// 获取参数
	var noteId = $(this).data("noteId");
	// 发送ajax请求
	$.ajax({
		url : path + "/note/load.do",
		data : {
			"noteId" : noteId
		},
		type : "post",
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				var title = // 获取笔记标题
				result.data.cn_note_title;
				var body = // 获取笔记内容
				result.data.cn_note_body;
				// 设置到编辑区域
				$("#input_note_title").val(title);
				um.setContent(body);
			}
		},
		error : function() {
			alert("加载笔记异常");
		}
	});
};


function loadBookNotes() {
	 //切换列表显示
	 $("#pc_part_2").show();
	 $("#pc_part_4").hide();
	 $("#pc_part_6").hide();
	 $("#pc_part_7").hide();
	 $("#pc_part_8").hide();
	 //设置笔记本li选中效果
	 $("#book_ul a").removeClass("checked");
	 $(this).find("a").addClass("checked");
	 //获取请求参数
	 var bookId = $(this).data("bookId");
	 //发送Ajax请求
	 $.ajax({
		 url:path+"/note/loadnotes.do",
		 type:"post",
		 data:{"bookId":bookId},
		 dataType:"json",
		 success:function(result){
			 if(result.status==0){
				 //清空原有笔记列表
				//$("#note_ul").empty();
				 $("#note_ul li").remove();
				 //获取服务器返回的笔记集合信息
				 var notes = result.data;
				 //循环生成笔记li元素
				 for(var i=0;i<notes.length;i++){
					 //获取笔记ID和笔记标题
					 var noteId = notes[i].cn_note_id;
					 var noteTitle = notes[i].cn_note_title;
					 //创建一个笔记li元素
					 createNoteLi(noteId,noteTitle);
					 //将新添加的元素判断是否该加分享图标
					 var typeId = 
						 notes[i].cn_note_type_id;
					 if(typeId=='2'){//加分享图标
						 var img = '<i class="fa fa-sitemap"></i>';
						 //获取新添加的li元素
						 var $li = $("#note_ul li:last");
						 $li.find(".btn_slide_down").before(img);
					 }
				 }
			 }
		 },
		 error:function(){
			 alert("加载笔记列表异常");
		 }
	 });

};
function createNoteLi(noteId, noteTitle) {
	var sli = "";
	sli += '<li class="online">';
	sli += '	<a>';
	sli += '		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	sli += noteTitle;
	// sli+='<i class="fa fa-sitemap"></i>';
	sli += '		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	sli += '	</a>';
	sli += '	<div class="note_menu" tabindex="-1">';
	sli += '		<dl>';
	sli += '			<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	sli += '			<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	sli += '			<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	sli += '		</dl>';
	sli += '	</div>';
	sli += '</li>';
	// 将noteId绑定到li元素上
	var $li = $(sli);
	$li.data("noteId", noteId);
	// 将li元素添加到笔记列表ul中
	$("#note_ul").append($li);
};
