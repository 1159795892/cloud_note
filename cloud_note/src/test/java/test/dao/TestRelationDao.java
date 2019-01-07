package test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.pj.cloud_note.dao.RelationDao;
import com.pj.cloud_note.entity.Book;
import com.pj.cloud_note.entity.User;

import test.TestBase;

public class TestRelationDao extends TestBase {
	private RelationDao relationDao;
	@Before
	public void init() {
		ApplicationContext ctx=super.getContext();
		relationDao = ctx.getBean("relationDao",RelationDao.class);
	}
	@Test
	public void testMany1(){
		User user = relationDao.findUserAndBooks("48595f52-b22c-4485-9244-f4004255b972");
		System.out.println("=======用户信息=====");
		System.out.println("名字"+user.getCn_user_name());
		System.out.println("昵称"+user.getCn_user_nick());
		System.out.println("笔记本数量"+user.getBooks().size());
		System.out.println("=======笔记本列表=========");
		for (Book book : user.getBooks()) {
			System.out.println(book.getCn_notebook_name());
		}
	}
	@Test
	public void testMany2(){
		User user = relationDao.findUserAndBooks1("48595f52-b22c-4485-9244-f4004255b972");
		System.out.println("=======用户信息=====");
		System.out.println("名字"+user.getCn_user_name());
		System.out.println("昵称"+user.getCn_user_nick());
		System.out.println("笔记本数量"+user.getBooks().size());
		System.out.println("=======笔记本列表=========");
		for (Book book : user.getBooks()) {
			System.out.println(book.getCn_notebook_name());
		}
	}
	@Test
	public void testOne1(){
		List<Book> list= relationDao.findBookAndUser();
		for (Book book : list) {
			System.out.println(book.getCn_notebook_name()+""+book.getCn_notebook_createtime());
			if (book.getUser()!=null) {
				System.out.println(book.getUser().getCn_user_name());
			}
		}
	}
	@Test
	public void testOne2(){
		List<Book> list= relationDao.findBookAndUser1();
		for (Book book : list) {
			System.out.println(book.getCn_notebook_name()+""+book.getCn_notebook_createtime());
			if (book.getUser()!=null) {
				System.out.println(book.getUser().getCn_user_name());
			}
		}
	}
}
