//回收站笔记彻底删除
function delete_rollback() {
	var $li = $("#rollback_ul a.checked").parent();
	var noteId = $li.data("noteId");
	$.ajax({
		url : path + "/note/delete_rollback.do",
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
			}else if(result.status == 1) {
				// 提示失败
				alert(result.msg);
			}
		},
		error : function() {
			alert("彻底删除笔记异常");
		}
	});
	
};
//回收站笔记删除撤销
function replay() {
	 //获取请求参数
	 //获取要恢复的笔记ID
	 var $li = $("#rollback_ul a.checked").parent();
	 var noteId = $li.data("noteId");
	 //获取要恢复到的笔记本ID
	 var bookId = $("#replaySelect").val();
	 //发送Ajax请求
	 $.ajax({
		 url:path+"/note/replay.do",
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
			 alert("恢复笔记异常");
		 }
	 });
	
};
//回收站
function rollback() {
	// 切换显示
	$("#pc_part_2").hide();
	$("#pc_part_4").show();
	$("#pc_part_6").hide();
	$("#pc_part_7").hide();
	$("#pc_part_8").hide();
	$.ajax({
		url : path + "/note/rollback.do",
		type : "post",
		data : {},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				// 清空原有笔记列表
				// $("#rollback_ul").empty();
				$("#rollback_ul li").remove();
				// 获取服务器返回的笔记集合信息
				var notes = result.data;
				// 循环生成笔记li元素
				for (var i = 0; i < notes.length; i++) {
					// 获取笔记ID和笔记标题
					var noteId = notes[i].cn_note_id;
					var noteTitle = notes[i].cn_note_title;
					// 创建一个笔记li元素
					createRollbackLi(noteId, noteTitle);
					// 将新添加的元素判断是否该加分享图标
				}
			}
			//获取当前点击的li(a标签加checked)进行变色
			$('#rollback_ul a').click(function() {
				$("#rollback_ul a").removeClass("checked");
				$(this).addClass("checked");
			});
		},
		error : function() {
			alert("打开回收站异常");
		}
	});
};

// 创建笔记列表li元素
function createRollbackLi(noteId, noteTitle) {
	var sli = "";
	sli += '<li class="disable">';
	sli += '	<a>';
	sli += '		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	sli += noteTitle;
	// sli+='<i class="fa fa-sitemap"></i>';
	sli += '		<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button>';
	sli += '	    <button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay"><i class="fa fa-reply"></i></button>';
	sli += '	</a>';
	sli += '</li>';
	// 将noteId绑定到li元素上
	var $li = $(sli);
	$li.data("noteId", noteId);
	// 将li元素添加到笔记列表ul中
	$("#rollback_ul").append($li);
};
