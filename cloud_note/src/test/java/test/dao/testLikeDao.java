package test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.pj.cloud_note.dao.LikeDao;
import com.pj.cloud_note.dao.ShareDao;
import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.entity.Share;
import com.pj.cloud_note.util.NoteUtil;

import test.TestBase;

public class testLikeDao extends TestBase{
	private ShareDao shareDao;
	private LikeDao likeDao;
	@Before
	public void init(){
		ApplicationContext ctx=super.getContext();
		shareDao=ctx.getBean("shareDao",ShareDao.class);
		likeDao=ctx.getBean("likeDao",LikeDao.class);
	}
	@Test
	public void testfindbyShareId(){
		String shareId="07d3fe4f6fae4bb1a4f15a4ca2a411bd";
		//Share share=likeDao.findLikeShareById(shareId);
		//System.out.println(share);
	}
}
