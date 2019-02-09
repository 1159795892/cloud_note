package com.pj.cloud_note.service;


import java.util.List;

import com.pj.cloud_note.entity.Book;
import com.pj.cloud_note.util.NoteResult;

public interface BookService {
	public NoteResult<List<Book>> LoadUserBooks(String id);
	public NoteResult<Object> addBook(String userId,String bookName);
	public NoteResult<Object> rename(String bookId,String bookName);
	public NoteResult<Object> deleteBook(String bookId);
}
	