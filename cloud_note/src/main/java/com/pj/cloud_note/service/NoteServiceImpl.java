package com.pj.cloud_note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.cloud_note.dao.NoteDao;
import com.pj.cloud_note.dao.ShareDao;
import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.entity.Share;
import com.pj.cloud_note.util.NoteResult;
import com.pj.cloud_note.util.NoteUtil;

@Service("noteService")
@Transactional
public class NoteServiceImpl implements NoteService {
	@Resource(name = "noteDao")
	private NoteDao noteDao;
	@Resource(name = "shareDao")
	private ShareDao shareDao;

	public NoteResult<List<Map>> loadBookNotes(String bookId) {
		NoteResult<List<Map>> result = new NoteResult<List<Map>>();
		List<Map> list = noteDao.findByBookId(bookId);
		result.setStatus(0);
		result.setMsg("查询完毕");
		result.setData(list);
		return result;

	}

	public NoteResult<Note> loadNote(String noteId) {
		NoteResult<Note> result = new NoteResult<Note>();
		Note note = noteDao.findByNoteId(noteId);
		if (note == null) {
			result.setStatus(1);
			result.setMsg("查无数据");
			return result;
		}
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(note);
		return result;
	}

	public NoteResult<Object> updateNote(String noteId, String title, String body) {
		Note note = new Note();
		NoteResult<Object> result = new NoteResult<Object>();
		note.setCn_note_id(noteId);
		note.setCn_note_title(title);
		note.setCn_note_body(body);
		Long time = System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		int check = noteDao.updateNote(note);
		if (check == 0) {
			result.setStatus(1);
			result.setMsg("更新失败");
		}
		result.setStatus(0);
		result.setMsg("更新成功");
		return result;
	}

	public NoteResult<Object> addNote(String userId, String bookId, String title) {
		Note note = new Note();
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
		note.setCn_user_id(userId);
		note.setCn_note_title(title);
		Long time = System.currentTimeMillis();
		note.setCn_note_create_time(time);
		note.setCn_note_last_modify_time(time);
		noteDao.save(note);
		NoteResult<Object> result = new NoteResult<Object>();
		result.setStatus(0);
		result.setMsg("创建笔记成功");
		result.setData(note);// 返回笔记ID
		return result;

	}
	@Transactional
	public NoteResult<Share> shareNote(String noteId) {
		NoteResult<Share> result = new NoteResult<Share>();
		Note note = noteDao.findByNoteId(noteId);
		// 检查cn_note_type_id是否为分享状态,
		// 如果已分享不执行下面逻辑
		if ("2".equals(note.getCn_note_type_id())) {
			result.setStatus(1);
			result.setMsg("该笔记已分享过");
			return result;
		}
		// 添加到分享表
		Share share = new Share();
		share.setCn_note_id(noteId);// 笔记ID
		share.setCn_share_id(NoteUtil.createId());// 分享ID
		share.setCn_share_title(note.getCn_note_title());// 分享标题
		share.setCn_share_body(note.getCn_note_body());// 分享内容
		shareDao.save(share);
		
		Note note1 = new Note();
		note1.setCn_note_id(noteId);
		note1.setCn_note_type_id("2");
		int rows = noteDao.dynamicUpdate(note1);
		//模拟异常
//		String str=null;
//		str.length();
		if (rows >= 0) {
			result.setStatus(0);
			result.setMsg("分享成功");
		} else {
			result.setStatus(1);
			result.setMsg("分享失败");
		}
		return result;
	}

	public NoteResult<Object> delectNote(String noteId) {
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("2");
		int rows = noteDao.dynamicUpdate(note);
		NoteResult<Object> result = new NoteResult<Object>();
		if (rows >= 1) {// 成功
			result.setStatus(0);
			result.setMsg("删除笔记成功");
		} else {
			result.setStatus(1);
			result.setMsg("删除笔记失败");
		}
		return result;
	}

	public NoteResult<List<Share>> searchShareNote(String keyword, int page) {
		// 处理查询条件值
		String title = "%";
		if (keyword != null && !"".equals(keyword)) {
			title = "%" + keyword + "%";
		}
		// 计算抓取起点
		if (page < 1) {
			page = 1;
		}
		int begin = (page - 1) * 3;
		// 封装成Map参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("begin", begin);// 对应#{begin}
		params.put("keyword", title);// 对应#{keyword}
		List<Share> list = shareDao.findLikeTitle(params);
		// 封装NoteResult结果
		NoteResult<List<Share>> result = new NoteResult<List<Share>>();
		result.setStatus(0);
		result.setMsg("搜索完毕");
		result.setData(list);
		return result;
	}

	public NoteResult<Share> loadShareNote(String shareId) {
		Share share = shareDao.findByShareId(shareId);
		NoteResult<Share> result = new NoteResult<Share>();
		if(share==null){
			result.setStatus(1);
			result.setMsg("加载笔记失败");
			return result;
		}
		result.setStatus(0);
		result.setMsg("加载笔记成功");
		result.setData(share);
		
		return result;

	}
	@Transactional
	public NoteResult<Object> deleteNotes(String... ids) {
		//String... 就是String[]
		NoteResult<Object> result=new NoteResult<Object>();
		for(String noteId:ids){
			int n = noteDao.delectNote(noteId);
			if(n!=1){//抛出异常触发事务的回滚!
				result.setStatus(1);
				result.setMsg("批量删除笔记失败");
				throw new RuntimeException("删错了");
			}
		}
		result.setStatus(0);
		result.setMsg("批量删除笔记成功");
		return result;
	}

	public NoteResult<Object> moveNotes(String noteId, String bookId) {
		Note note =new Note();
		note.setCn_note_id(noteId);//设置笔记ID
		note.setCn_notebook_id(bookId);//设置笔记本ID
		int rows=noteDao.dynamicUpdate(note);
		//创建返回结果
		NoteResult<Object> result=new NoteResult<Object>();
		if(rows>=1){
			result.setStatus(0);
			result.setMsg("转移笔记成功");
		}else{
			result.setStatus(1);
			result.setMsg("转移笔记失败");
		}
		return result;
		
	}
}
