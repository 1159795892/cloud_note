package test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.pj.cloud_note.dao.NoteDao;
import com.pj.cloud_note.dao.ShareDao;
import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.entity.Share;
import com.pj.cloud_note.util.NoteUtil;

import test.TestBase;

public class testShareDao extends TestBase{
	private ShareDao shareDao;
	private NoteDao noteDao;
	@Before
	public void init(){
		ApplicationContext ctx=super.getContext();
		shareDao=ctx.getBean("shareDao",ShareDao.class);
	}
	@Test
	public void testsave(){
		String noteId="faf8fe2bcb0d418ea84aef400f838b70";
		Note note=noteDao.findByNoteId(noteId);
		Share share=new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_body(note.getCn_note_title());
		String shareId=NoteUtil.createId();
		share.setCn_share_id(shareId);
		share.setCn_share_title(note.getCn_note_body());
	}
	@Test
	public void testsearch(){
		String keyword="%2%";
		int begin = 0;
		Share share=new Share();
		share.setCn_share_title(keyword);
		Map<String, Object> params = 
				new HashMap<String, Object>();
		params.put("begin", begin);
		params.put("keyword", keyword);
		List<Share> list = shareDao.findLikeTitle(params);
		for (Share share2 : list) {
			
			System.out.println(share2.getCn_share_title());
		}
		
	}
	
	
	@Test
	public void testfindbyShareId(){
		String shareId="07d3fe4f6fae4bb1a4f15a4ca2a411bd";
		Share share=shareDao.findByShareId(shareId);
		System.out.println(share);
	}
}
