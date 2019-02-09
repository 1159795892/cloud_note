package com.pj.cloud_note.dao;

import java.util.List;

import com.pj.cloud_note.entity.Note;

public interface RollBackDao {
	public List<Note> findRollBackNoteById();
}
