package com.pj.cloud_note.dao;


import java.util.List;
import java.util.Map;

import com.pj.cloud_note.entity.Book;

public interface BookDao {
	public List<Book> findByUserId(String userId);
	public void save(Book book);
	public int dynamicUpdate(Book book);
	public int deleteBook(String bookId);
	public int cheakBookNote(String bookId);
}
