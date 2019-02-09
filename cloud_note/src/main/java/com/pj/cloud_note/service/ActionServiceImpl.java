package com.pj.cloud_note.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.cloud_note.dao.ActionDao;
import com.pj.cloud_note.dao.NoteDao;
import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.util.NoteResult;

@Service("actionService")
@Transactional
public class ActionServiceImpl implements ActionService {
	@Resource(name = "noteDao")
	private NoteDao noteDao;
	@Resource(name = "actionDao")
	private ActionDao actionDao;

	public NoteResult<List<Note>> loadActionNotes() {
		List<Note> notes = actionDao.LoadActionNotes();
		NoteResult<List<Note>> result = new NoteResult<List<Note>>();
		result.setStatus(0);
		result.setMsg("加载完毕");
		result.setData(notes);
		return result;
	}


	public NoteResult<Object> DeleteActionNote(String noteId) {
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_type_id("1");
		noteDao.dynamicUpdate(note);
		int rows=actionDao.deleteAction(noteId);
		NoteResult<Object> result = new NoteResult<Object>();
		if(rows>=0){
		result.setStatus(0);
		result.setMsg("删除活动笔记成功");
		return result;
		}else{
		result.setStatus(1);
		result.setMsg("删除活动笔记失败");
		return result;
		}
	}



}
