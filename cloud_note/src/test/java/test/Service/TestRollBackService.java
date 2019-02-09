package test.Service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.pj.cloud_note.entity.Book;
import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.service.RollBackService;
import com.pj.cloud_note.util.NoteResult;

import test.TestBase;

public class TestRollBackService extends TestBase{
	RollBackService service;
	@Before
	public void init(){
	ApplicationContext ctx = super.getContext();
	service = ctx.getBean("rollBackService",RollBackService.class);
	
	}
	@Test//
	public void test1(){
		NoteResult<List<Note>> result = service.loadRollBackNotes();
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	@Test//用例-1:预期结果:彻底删除成功
	public void testdelete1(){
		String noteId="16545464";
		NoteResult<Object> result =service.rollBackdelete(noteId);
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	@Test//用例-2:预期结果:没有此数据,请刷新
	public void testdelete2(){
		String noteId="112";
		NoteResult<Object> result=service.rollBackdelete(noteId);
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	
}
