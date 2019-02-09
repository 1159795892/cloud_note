//删除参加活动笔记
function suredeleteAction() {
	// 获取noteId
	var $li = // 获取选中的笔记li元素
	$("#action_ul a.checked").parent();
	var noteId = $li.data("noteId");
	// 发送Ajax请求
	$.ajax({
		url : path + "/note/suredeleteAction.do",
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
			if (result.status == 1) {
				// 提示删除活动笔记失败
				alert(result.msg);
			}
		},
		error : function() {
			alert("删除活动异常");
		}
	});
}
//参加活动笔记
function action() {
	// 切换显示
	$("#pc_part_2").hide();
	$("#pc_part_4").hide();
	$("#pc_part_6").hide();
	$("#pc_part_7").hide();
	$("#pc_part_8").show();
	$.ajax({
		url : path + "/note/action.do",
		type : "post",
		data : {},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				// 清空原有笔记列表
				// $("#rollback_ul").empty();
				$("#action_ul li").remove();
				// 获取服务器返回的笔记集合信息
				var notes = result.data;
				// 循环生成笔记li元素
				for (var i = 0; i < notes.length; i++) {
					// 获取笔记ID和笔记标题
					var noteId = notes[i].cn_note_id;
					var noteTitle = notes[i].cn_note_title;
					// 创建一个笔记li元素
					createActionLi(noteId, noteTitle);
				}
				
			}
			// 获取当前点击的li(a标签加checked)进行变色
			$('#action_ul a').click(function() {
				$("#action_ul a").removeClass("checked");
				$(this).addClass("checked");
			});
		},
		error : function() {
			alert("打开收藏夹异常");
		}
	});
};
// 创建笔记列表li元素
function createActionLi(noteId, noteTitle) {
	var sli = "";
	sli += '<li class="offline">';
	sli += '	<a>';
	sli += '		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	sli += noteTitle;
	sli += '		<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button>';
	sli += '	</a>';
	sli += '</li>';
	// 将noteId绑定到li元素上
	var $li = $(sli);
	$li.data("noteId", noteId);
	// 将li元素添加到笔记列表ul中
	$("#action_ul").append($li);
};
