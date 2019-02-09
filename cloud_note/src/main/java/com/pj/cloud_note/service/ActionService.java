package com.pj.cloud_note.service;

import java.util.List;

import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.util.NoteResult;

public interface ActionService {
	public NoteResult<List<Note>> loadActionNotes();
	public NoteResult<Object> DeleteActionNote(String shareId);
	
}

