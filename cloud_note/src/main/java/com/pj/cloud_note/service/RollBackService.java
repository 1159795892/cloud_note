package com.pj.cloud_note.service;

import java.util.List;

import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.util.NoteResult;

public interface RollBackService {
	public NoteResult<List<Note>> loadRollBackNotes();
	public NoteResult<Object> rollBackdelete(String noteId);
	public NoteResult<Object> rollBackreplay(String noteId,String bookId);
}

