//"更多"按钮单击处理
function sureMorePage(){
	 		 //将page加1
	 		 page = page+1;
	 		 //发送Ajax请求加载数据
	 		 searchSharePage(keyword,page);
	 	 }
//搜索分享笔记
function sureSearchPage(event) {
			var code = event.keyCode;
			if (code == 13) {//回车键
				//清除原有列表结果
				$("#pc_part_6 ul").empty();
				//显示搜索结果列表,其他列表隐藏
				$("#pc_part_2").hide();
				$("#pc_part_4").hide();
				$("#pc_part_6").show();
				$("#pc_part_7").hide();
				$("#pc_part_8").hide();
				//获取请求参数
				keyword = $("#search_note").val().trim();
				page = 1;//设置初始值1
				//发送Ajax请求
				searchSharePage(keyword, page);
			}
}
//分页加载搜索分享笔记
function searchSharePage(keyword, page) {
	$.ajax({
		url : path + "/note/search_share.do",
		type : "post",
		data : {
			"keyword" : keyword,
			"page" : page
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				var shares = result.data;
				for (var i = 0; i < shares.length; i++) {
					var shareId = // 分享ID
					shares[i].cn_share_id;
					var shareTitle = // 分享标题
					shares[i].cn_share_title;
					// 生成li
					createshareLi(shareId, shareTitle);
				}
			}
		},
		error : function() {
			alert("搜索笔记异常");
		}
	});
}