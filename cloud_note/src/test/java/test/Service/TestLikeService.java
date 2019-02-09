package test.Service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.pj.cloud_note.entity.Like;
import com.pj.cloud_note.service.LikeService;
import com.pj.cloud_note.util.NoteResult;

import test.TestBase;

public class TestLikeService extends TestBase{
	LikeService service;
	@Before
	public void init(){
	ApplicationContext ctx = super.getContext();
	service = ctx.getBean("likeService",LikeService.class);
	
	}
	@Test//用例-1:预期结果:收藏成功
	public void testlike1(){
		String shareId="07d3fe4f6fae4bb1a4f15a4ca2a411bd";
		NoteResult<Like> result =service.LikeNote(shareId);
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	@Test//用例-2:预期结果:收藏失败
	public void testlike2(){
		String shareId="11";
		NoteResult<Like> result =service.LikeNote(shareId);
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	
	@Test//用例-3:预期结果:该笔记已收藏过了
	public void testlike3(){
		String shareId="07d3fe4f6fae4bb1a4f15a4ca2a411bd";
		NoteResult<Like> result =service.LikeNote(shareId);
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	
}
