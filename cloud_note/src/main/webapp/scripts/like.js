//删除收藏
function suredeletelike() {
	// 获取shareId
	var $li = // 获取选中的笔记li元素
	$("#like_ul a.checked").parent();
	var shareId = $li.data("shareId");
	// 发送Ajax请求
	$.ajax({
		url : path + "/note/suredeletelike.do",
		type : "post",
		data : {
			"shareId" : shareId
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
				// 提示删除收藏失败
				alert(result.msg);
			}
		},
		error : function() {
			alert("收藏异常");
		}
	});
}
//确认收藏
function surelike() {
	// 获取shareId
	var $li = // 获取选中的笔记li元素
	$("#search_ul a.checked").parent();
	var shareId = $li.data("shareId");
	// 发送Ajax请求
	$.ajax({
		url : path + "/note/surelike.do",
		type : "post",
		data : {
			"shareId" : shareId
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				alert(result.msg);
			}
			if (result.status == 1) {
				// 提示已收藏过
				alert(result.msg);
			}
		},
		error : function() {
			alert("收藏异常");
		}
	});
}
// 收藏夹
function likeNote() {
	// 切换显示
	$("#pc_part_2").hide();
	$("#pc_part_4").hide();
	$("#pc_part_6").hide();
	$("#pc_part_7").show();
	$("#pc_part_8").hide();
	$.ajax({
		url : path + "/note/like.do",
		type : "post",
		data : {},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				// 清空原有笔记列表
				// $("#rollback_ul").empty();
				$("#like_ul li").remove();
				// 获取服务器返回的笔记集合信息
				var shares = result.data;
				// 循环生成笔记li元素
				for (var i = 0; i < shares.length; i++) {
					// 获取笔记ID和笔记标题
					var shareId = shares[i].cn_share_id;
					var shareTitle = shares[i].cn_share_title;
					// 创建一个笔记li元素
					createLikeLi(shareId, shareTitle);
				}
				
			}
			// 获取当前点击的li(a标签加checked)进行变色
			$('#like_ul a').click(function() {
				$("#like_ul a").removeClass("checked");
				$(this).addClass("checked");
			});
		},
		error : function() {
			alert("打开收藏夹异常");
		}
	});
};

// 创建笔记列表li元素
function createLikeLi(shareId, shareTitle) {
	var sli = "";
	sli += '<li class="idle">';
	sli += '	<a>';
	sli += '		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	sli += shareTitle;
	// sli+='<i class="fa fa-sitemap"></i>';
	sli += '		<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button>';
	sli += '	</a>';
	sli += '</li>';
	// 将noteId绑定到li元素上
	var $li = $(sli);
	$li.data("shareId", shareId);
	// 将li元素添加到笔记列表ul中
	$("#like_ul").append($li);
};
