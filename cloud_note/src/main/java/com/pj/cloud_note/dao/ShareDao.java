package com.pj.cloud_note.dao;

import java.util.List;
import java.util.Map;

import com.pj.cloud_note.entity.Share;

public interface ShareDao {
	public List<Share> findLikeTitle(
			Map<String, Object> params);
	public void save(Share share);
	public Share findByShareId(String shareId);
	public int dynamicUpdate(Share share);
}
