package test.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pj.cloud_note.dao.UserDao;
import com.pj.cloud_note.entity.User;
import com.pj.cloud_note.service.UserService;

public class TestUserDao {
	UserDao dao;
	@Before
	public void init(){
	String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-transaction.xml","conf/spring-aop.xml"};
	//实例化ctx对象
	ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
	//获取UserDao对象
	dao = ctx.getBean("userDao",UserDao.class);
	}
	@Test
	public void testUserDao(){
		User user = dao.findByName("demo");
		System.out.println(user);
		if (user!=null) {
			System.out.println("用户存在");
		}else{
			System.out.println("查无此人");
		}
	}
	@Test
	public void testSave(){
		User user=new User();
		user.setCn_user_id("1234568");
		user.setCn_user_name("张山");
		user.setCn_user_password("123456");
		user.setCn_user_nick("金宝");
		dao.save(user);
		System.out.println(user);
	}
	@Test
	public void testChangepassword(){
		User user=dao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
		user.setCn_user_id("48595f52-b22c-4485-9244-f4004255b972");
		user.setCn_user_password("4QrcOUm6Wau+VuBX8g+IPg==");
		dao.dynamicUpdate(user);
		System.out.println(user);
	}
	
}
