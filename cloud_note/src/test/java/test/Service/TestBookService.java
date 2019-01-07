package test.Service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pj.cloud_note.entity.Book;
import com.pj.cloud_note.entity.User;
import com.pj.cloud_note.service.BookService;
import com.pj.cloud_note.service.UserService;
import com.pj.cloud_note.util.NoteResult;

import test.TestBase;

public class TestBookService extends TestBase{
	BookService service;
	@Before
	public void init(){
	ApplicationContext ctx = super.getContext();
	service = ctx.getBean("bookService",BookService.class);
	
	}
	@Test//用例-1:预期结果:查询完毕
	public void test1(){
		NoteResult<List<Book>> result = service.LoadUserBooks("52f9b276-38ee-447f-a3aa-0d54e7a736e4");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	@Test//用例-1:预期结果:查询完毕
	public void testadd(){
		String userId="48595f52-b22c-4485-9244-f4004255b972";
		NoteResult<Object> result = service.addBook(userId, "小可爱");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	@Test//用例-1:预期结果:修改成功
	public void testrename(){
		String bookId="25521f62f23b42c5b0ea89cdee1bb8a2";
		NoteResult<Object> result = service.rename(bookId, "无情铁手");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	
	
}
