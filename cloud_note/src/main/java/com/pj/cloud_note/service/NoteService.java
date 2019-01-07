package com.pj.cloud_note.service;

import java.util.List;
import java.util.Map;

import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.entity.Share;
import com.pj.cloud_note.util.NoteResult;

public interface NoteService {
	public NoteResult<List<Map>> loadBookNotes(String bookId);
	public NoteResult<Note> loadNote(String noteId);
	public NoteResult<Object> updateNote(String noteId,String title,String body);
	public NoteResult<Object> addNote(String userId,String bookId,String title);
	public NoteResult<Share> shareNote(String noteId);
	public NoteResult<Object> delectNote(String noteId);
	public NoteResult<List<Share>> searchShareNote(String keyword ,int page);
	public NoteResult<Share> loadShareNote(String shareId);
	public NoteResult<Object> deleteNotes(String... ids);
}
