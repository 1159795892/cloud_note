package com.pj.cloud_note.service;

import java.util.List;

import com.pj.cloud_note.entity.Like;
import com.pj.cloud_note.entity.Share;
import com.pj.cloud_note.util.NoteResult;

public interface LikeService {
	public NoteResult<List<Share>> loadLikeNotes();
	public NoteResult<Like> LikeNote(String shareId);
	public NoteResult<Object> DeleteLikeNote(String shareId);
	
}

