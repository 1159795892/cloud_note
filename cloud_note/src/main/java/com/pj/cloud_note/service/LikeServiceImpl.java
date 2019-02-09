package com.pj.cloud_note.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.cloud_note.dao.LikeDao;
import com.pj.cloud_note.dao.ShareDao;
import com.pj.cloud_note.entity.Like;
import com.pj.cloud_note.entity.Share;
import com.pj.cloud_note.util.NoteResult;
import com.pj.cloud_note.util.NoteUtil;

@Service("likeService")
@Transactional
public class LikeServiceImpl implements LikeService {
	@Resource(name = "shareDao")
	private ShareDao shareDao;
	@Resource(name = "likeDao")
	private LikeDao likeDao;

	public NoteResult<List<Share>> loadLikeNotes() {
		List<Share> shares = likeDao.findLikeNoteById();
		NoteResult<List<Share>> result = new NoteResult<List<Share>>();
		result.setStatus(0);
		result.setMsg("加载完毕");
		result.setData(shares);
		return result;
	}

	public NoteResult<Like> LikeNote(String shareId) {
		NoteResult<Like> result = new NoteResult<Like>();
		Share share = shareDao.findByShareId(shareId);
		if ("2".equals(share.getCn_like_type_id())) {
			result.setStatus(1);
			result.setMsg("该笔记已收藏过了");
			return result;
		}
		Like like = new Like();
		like.setCn_like_id(NoteUtil.createId());
		like.setCn_like_body(share.getCn_share_body());
		like.setCn_like_title(share.getCn_share_title());
		like.setCn_share_id(shareId);
		likeDao.saveLike(like);
		System.out.println(like);
		Share share1 = new Share();
		share1.setCn_share_id(shareId);
		share1.setCn_like_type_id("2");
		int rows = shareDao.dynamicUpdate(share1);
		System.out.println(share1);
		if (rows >= 0) {
			result.setStatus(0);
			result.setMsg("收藏成功");
		}
		return result;
	}

	public NoteResult<Object> DeleteLikeNote(String shareId) {
		Share share=new Share();
		share.setCn_share_id(shareId);
		share.setCn_like_type_id("1");
		shareDao.dynamicUpdate(share);
		int rows=likeDao.deleteLike(shareId);
		NoteResult<Object> result = new NoteResult<Object>();
		if(rows>=0){
		result.setStatus(0);
		result.setMsg("删除收藏笔记成功");
		return result;
		}else{
		result.setStatus(1);
		result.setMsg("删除收藏笔记失败");
		return result;
		}
	}

}
