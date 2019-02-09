package test.dao;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pj.cloud_note.dao.BookDao;
import com.pj.cloud_note.dao.RollBackDao;
import com.pj.cloud_note.entity.Book;
import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.util.NoteUtil;

public class testRollBackDao {
	RollBackDao dao;

	@Before
	public void init() {
		String[] conf = { "conf/spring-mybatis.xml", "conf/spring-mvc.xml", "conf/spring-transaction.xml",
				"conf/spring-aop.xml" };
		ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
		dao = ctx.getBean("rollbackDao", RollBackDao.class);
	}

	@Test // 测试是否能查询到数据
	public void test1() {
		List<Note> list = dao.findRollBackNoteById();
		for (Note note : list) {
			System.out.println(note);
		}
	}

	
}
