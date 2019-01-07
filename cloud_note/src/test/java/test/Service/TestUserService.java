package test.Service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pj.cloud_note.entity.User;
import com.pj.cloud_note.service.UserService;
import com.pj.cloud_note.util.NoteResult;

public class TestUserService {
	UserService service;
	@Before
	public void init(){
	String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-transaction.xml"};
	ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
	service = ctx.getBean("userService",UserService.class);
	
	}
	@Test//用例-1:预期结果:用户不存在
	public void test1(){
		NoteResult<User> result = service.checkLogin("demo1","11");
		System.out.println(service.getClass().getName());
//		System.out.println(result.getStatus());
//		System.out.println(result.getMsg());
//		System.out.println(result.getData());
	}
	@Test//用例-2:预期结果:密码错误
	public void test2(){
		NoteResult<User> result = service.checkLogin("demo","11");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	@Test//用例-3:预期结果:成功
	public void test3(){
		NoteResult<User> result = service.checkLogin("demo","123456");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	@Test//用例-1:预期结果:用户已存在
	public void test4(){
		NoteResult<Object> result = service.addUser("张山", "123456", "金宝");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	@Test//用例-2:预期结果:密码错误
	public void test5(){
		NoteResult<Object> result = service.addUser("李四", "123456", "四哥");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	@Test
	public void testchangepwd(){
		NoteResult<User> result = service.chang_password("48595f52-b22c-4485-9244-f4004255b972", "123456", "123123");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	
}
