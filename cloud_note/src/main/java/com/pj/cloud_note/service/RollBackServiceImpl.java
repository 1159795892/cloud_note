package com.pj.cloud_note.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.cloud_note.dao.NoteDao;
import com.pj.cloud_note.dao.RollBackDao;
import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.util.NoteResult;

@Service("rollBackService")
@Transactional
public class RollBackServiceImpl implements RollBackService {
	@Resource(name = "noteDao")
	private NoteDao noteDao;
	@Resource(name = "rollBackDao")
	private RollBackDao rollbackDao;

	public NoteResult<List<Note>> loadRollBackNotes() {
		List<Note> notes = rollbackDao.findRollBackNoteById();
		NoteResult<List<Note>> result = new NoteResult<List<Note>>();
		result.setStatus(0);
		result.setMsg("加载完毕");
		result.setData(notes);
		return result;
	}

	public NoteResult<Object> rollBackdelete(String noteId) {
		int rows = noteDao.delectNote(noteId);
		NoteResult<Object> result = new NoteResult<Object>();
		if (rows >= 0) {
			result.setStatus(0);
			result.setMsg("彻底删除成功");
			return result;
		} else {
			result.setStatus(1);
			result.setMsg("没有此数据,请刷新");
			return result;
		}
	}

	public NoteResult<Object> rollBackreplay(String noteId, String bookId) {
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_status_id("1");
		int rows = noteDao.dynamicUpdate(note);
		NoteResult<Object> result = new NoteResult<Object>();
		if (rows >= 0) {
			result.setStatus(0);
			result.setMsg("恢复成功");
			return result;
		} else {
			result.setStatus(1);
			result.setMsg("无此数据,请刷新重试");
			return result;
		}
	}

}
