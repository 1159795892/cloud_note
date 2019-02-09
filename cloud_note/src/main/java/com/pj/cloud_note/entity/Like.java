package com.pj.cloud_note.entity;

import java.io.Serializable;

public class Like implements Serializable {
	private String cn_like_id;
	private String cn_like_title;
	private String cn_like_body;
	private String cn_share_id;
	public String getCn_like_id() {
		return cn_like_id;
	}
	public void setCn_like_id(String cn_like_id) {
		this.cn_like_id = cn_like_id;
	}
	public String getCn_like_title() {
		return cn_like_title;
	}
	public void setCn_like_title(String cn_like_title) {
		this.cn_like_title = cn_like_title;
	}
	public String getCn_like_body() {
		return cn_like_body;
	}
	public void setCn_like_body(String cn_like_body) {
		this.cn_like_body = cn_like_body;
	}
	public String getCn_share_id() {
		return cn_share_id;
	}
	public void setCn_share_id(String cn_share_id) {
		this.cn_share_id = cn_share_id;
	}
	@Override
	public String toString() {
		return "Like [cn_like_id=" + cn_like_id + ", cn_like_title=" + cn_like_title + ", cn_like_body=" + cn_like_body
				+ ", cn_share_id=" + cn_share_id + "]";
	}
	
	
	
	
}
