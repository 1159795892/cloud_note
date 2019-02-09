package com.pj.cloud_note.dao;

import java.util.List;

import com.pj.cloud_note.entity.Like;
import com.pj.cloud_note.entity.Share;

public interface LikeDao {
	public List<Share> findLikeNoteById();

	public void saveLike(Like like);

	public int deleteLike(String shareId);
}
