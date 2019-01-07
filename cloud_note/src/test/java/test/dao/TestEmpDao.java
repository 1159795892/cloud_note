package test.dao;

import org.junit.Before;
import org.junit.Test;

import com.pj.cloud_note.dao.EmpDao;
import com.pj.cloud_note.entity.Emp;

import test.TestBase;

public class TestEmpDao extends TestBase {
	private EmpDao empDao;
	@Before
	public void init(){
		empDao = getContext().getBean("empDao",EmpDao.class);
	}
	@Test
	public void testsave(){
		Emp emp=new Emp();
		emp.setName("你大爷");
		emp.setAge(20);
		empDao.save(emp);
		System.out.println(emp);
		int id=emp.getId();
		System.out.println(id);
	}
}
