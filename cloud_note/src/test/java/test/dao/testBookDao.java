package test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pj.cloud_note.dao.BookDao;
import com.pj.cloud_note.entity.Book;
import com.pj.cloud_note.util.NoteUtil;

public class testBookDao {
	BookDao dao;
	@Before
	public void init(){
		String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-transaction.xml","conf/spring-aop.xml"};
		ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
		dao=ctx.getBean("bookDao",BookDao.class);
	}
	@Test//测试是否能查询到数据
	public void test1(){
		List<Book> list = dao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
		for (Book book : list) {
			System.out.println(book);
			
		}
		}
	@Test//测试是否能重命名
	public void testrename(){
		Book book=new Book();
		book.setCn_notebook_id("25521f62f23b42c5b0ea89cdee1bb8a2");
		String name=book.getCn_notebook_name();
		System.out.println(name);
		book.setCn_notebook_name("无情铁手2");
		dao.dynamicUpdate(book);
		String name2=book.getCn_notebook_name();
		System.out.println(name2);
			
	}
	@Test//测试是否能增加数据
	public void testadd(){
		Book book=new Book();
		NoteUtil noteUtil=new NoteUtil();
		book.setCn_notebook_id(noteUtil.createId());
		book.setCn_user_id("ea09d9b1-ede7-4bd8-b43d-a546680df00b");
		book.setCn_notebook_name("无敌神王");
		dao.save(book);
		System.out.println(book);
		
	}
	@Test//测试是否能增加数据
	public void testcount(){
		String bookId="cf992df44e8f4de8813593d2cbe9c482";
		int rows = dao.cheakBookNote(bookId);
		System.out.println(rows);
		
	}
}
