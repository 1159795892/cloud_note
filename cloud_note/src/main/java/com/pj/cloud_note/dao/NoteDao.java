package com.pj.cloud_note.dao;

import java.util.List;
import java.util.Map;

import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.util.NoteResult;

public interface NoteDao {
	public List<Map> findByBookId(String bookId);
	public Note findByNoteId(String noteId);
	public int updateNote(Note note);
	public void save(Note note);
	public int dynamicUpdate(Note note);
	/**
	 * map 中需要添加两个参数
	 * 	map={ids:[id1,id2,id3....],status:2}
	 * 	ids 代表被删除的笔记ID列表
	 * 	status 代表被删除的笔记的状态属性
	 * @param map
	 * @return
	 */
	public int deleteNotes (Map<String, Object> map);
	public int delectNote(String noteId);
	public int batchDelete(String[] ids);
	
	
}
