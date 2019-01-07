package test.Service;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.entity.Share;
import com.pj.cloud_note.service.NoteService;
import com.pj.cloud_note.util.NoteResult;

import test.TestBase;

public class TestNoteService extends TestBase{
	NoteService service;
	@Before
	public void init(){
	ApplicationContext ctx = super.getContext();
	service = ctx.getBean("noteService",NoteService.class);
	
	}
	@Test//用例-1:预期结果:查询完毕
	public void test1(){
		NoteResult<List<Map>> result = service.loadBookNotes("4b86d1f9-6345-4532-bc50-ee86442f004b");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	@Test//用例-1:预期结果:查询完毕
	public void test2(){
		NoteResult<Note> result=service.loadNote("148800c2-885d-41be-bd9a-581763138074");
		System.out.println(result.getMsg());
		System.out.println(result.getStatus());
		System.out.println(result.getData());

	}
	@Test//用例-2:预期结果:查无数据
	public void test3(){
		NoteResult<Note> result=service.loadNote("54");
		System.out.println(result.getMsg());
		System.out.println(result.getStatus());
		System.out.println(result.getData());
		
	}
	@Test//用例-1:预期结果:更新成功
	public void test4(){
		Note note=new Note();
		NoteResult<Object> result=service.updateNote("148800c2-885d-41be-bd9a-581763138074","最好的语言","java是世界上最好的语言");
		System.out.println(result.getMsg());
		System.out.println(result.getStatus());
		System.out.println(result.getData());
		
	}
	@Test//用例-1:预期结果:增加成功
	public void testsave1(){
		Note note=new Note();
		NoteResult<Object> result=service.addNote("ea09d9b1-ede7-4bd8-b43d-a546680df00b", "4b86d1f9-6345-4532-bc50-ee86442f004b", "我in我中国");
		System.out.println(result.getMsg());
		System.out.println(result.getStatus());
		System.out.println(result.getData());
		
		
	}
	@Test//用例-1:预期结果:加载笔记成功
	public void testloadshareNote1(){
		NoteResult<Share> result=service.loadShareNote("07d3fe4f6fae4bb1a4f15a4ca2a411bd");
		System.out.println(result.getMsg());
		System.out.println(result.getStatus());
		System.out.println(result.getData());
		
		
	}
	@Test//用例-2:预期结果:加载笔记失败
	public void testloadshareNote2(){
		NoteResult<Share> result=service.loadShareNote("");
		System.out.println(result.getMsg());
		System.out.println(result.getStatus());
		System.out.println(result.getData());
		
		
	}
	@Test
	public void testdelete(){
		//调用动态参数时候,可以不创建数组
		NoteResult<Object> result = service.deleteNotes("56fafd80d35a48efab1d0b7b1f6611e3","aefa006b5e824419ba505e97377c4c81","id3");
		System.out.println(result.getMsg());
		System.out.println(result.getStatus());
		
	}

	
	
	
}
