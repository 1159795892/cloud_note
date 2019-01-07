package com.pj.cloud_note.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.cloud_note.dao.BookDao;
import com.pj.cloud_note.entity.Book;
import com.pj.cloud_note.util.NoteResult;
import com.pj.cloud_note.util.NoteUtil;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {
	@Resource(name = "bookDao")
	private BookDao bookDao;

	public NoteResult<List<Book>> LoadUserBooks(String userId) {
		NoteResult<List<Book>> result = new NoteResult<List<Book>>();
		List<Book> list = bookDao.findByUserId(userId);
		result.setStatus(0);
		result.setMsg("查询完毕");
		result.setData(list);
		return result;
	}

	public NoteResult<Object> addBook(String userId, String bookName) {
		NoteResult<Object> result = new NoteResult<Object>();
		Book book = new Book();
		book.setCn_user_id(userId);// 设置用户ID
		book.setCn_notebook_name(bookName);// 设置笔记本名
		String bookId = NoteUtil.createId();
		book.setCn_notebook_id(bookId);// 设置笔记本ID
		Timestamp time = new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);// 设置创建时间
		bookDao.save(book);// 保存笔记本
		result.setStatus(0);
		result.setMsg("增加笔记成功");
		result.setData(book);
		return result;
	}

	public NoteResult<Object> rename(String bookId, String bookName) {
		NoteResult<Object> result = new NoteResult<Object>();
		Book book = new Book();
		book.setCn_notebook_id(bookId);
		book.setCn_notebook_name(bookName);
		int rows = bookDao.dynamicUpdate(book);
		if (rows >= 1) {// 成功
			result.setStatus(0);
			result.setMsg("修改成功");
			result.setData(book);
			return result;
		} else {
			result.setStatus(1);
			result.setMsg("重命名失败");
			return result;
		}
	}

}
