package test.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pj.cloud_note.dao.NoteDao;
import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.util.NoteUtil;

public class TestNoteDao {
	NoteDao dao;
	@Before
	public void init(){
	String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-transaction.xml","conf/spring-aop.xml"};
	//实例化ctx对象
	ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
	//获取UserDao对象
	dao = ctx.getBean("noteDao",NoteDao.class);
	}
	@Test
	public void testNoteDao(){
		List<Map> list = dao.findByBookId("4b86d1f9-6345-4532-bc50-ee86442f004b");
		for (Map note : list) {
			System.out.println(note.get("cn_note_id")+","+note.get("cn_note_title"));
		}
	}
	@Test
	public void testfindByNoteId(){
		Note note = dao.findByNoteId("148800c2-885d-41be-bd9a-581763138074");
		System.out.println(note.getCn_note_title());
	}
	@Test
	public void testupdateNote(){
		Note note =new Note();
		note.setCn_note_id("148800c2-885d-41be-bd9a-581763138074");
		note.setCn_note_title("我爱你中华");
		note.setCn_note_body("我爱你中华,我在你中国产党,我爱你中华,我在你中国产党,我爱你中华,我在你中国产党");
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		dao.updateNote(note);
		System.out.println(note.getCn_note_title()+
		note.getCn_note_body()+
		note.getCn_note_last_modify_time());
	}
	@Test
	public void testsave(){
		Note note =new Note();
		String uuid = NoteUtil.createId();
		note.setCn_note_id(uuid);
		note.setCn_notebook_id("4b86d1f9-6345-4532-bc50-ee86442f004b");
		note.setCn_user_id("ea09d9b1-ede7-4bd8-b43d-a546680df00b");
		note.setCn_note_title("我爱你中华");
		Long time=System.currentTimeMillis();
		note.setCn_note_create_time(time);
		note.setCn_note_last_modify_time(time);
		dao.save(note);
		System.out.println(note.getCn_note_title()+
				note.getCn_note_body()+
				note.getCn_note_last_modify_time());
	}
	@Test
	public void testDeleNotes(){
		Map<String, Object> map =new HashMap<String, Object>();
		String[] ids={"id1","id13","id4"};
		map.put("ids", ids);
		map.put("status", 2);
		int n = dao.deleteNotes(map);
		System.out.println(n);
	}
	
}
