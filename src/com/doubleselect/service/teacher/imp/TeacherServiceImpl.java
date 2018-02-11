package com.doubleselect.service.teacher.imp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.doubleselect.dao.teacher.TeacherDao;
import com.doubleselect.model.teacher.Tteacher;
import com.doubleselect.model.teacher.vo.Teacher;
import com.doubleselect.service.teacher.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private TeacherDao teacherDao;

	
	
	public void save(Teacher t) throws Exception {
		Tteacher tdd = new Tteacher();
		BeanUtils.copyProperties(t, tdd);
		this.teacherDao.save(tdd);	
	}

	
	public void delete(Teacher t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		this.teacherDao.delete(id, Tteacher.class);
	}

	
	public void delete(int[] ids) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void update(Teacher t) throws Exception {
		// TODO Auto-generated method stub
		Tteacher tdd = new Tteacher();
		BeanUtils.copyProperties(t, tdd);
		this.teacherDao.update(tdd);	
	}

	
	public List<Teacher> getByPage(int page, int row, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Teacher getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int queryTotal(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public List<Teacher> getByPage(int page, int row, String teachersno,String teachername,String teachersex,String teacherId) {
		List<Teacher> list_return = new ArrayList<Teacher>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		/*int id, String teachersno, String teachername,
		String teachersex, String teachermajor, int guidemin, int guidemax,
		String degree*/
		String hql = "select new com.doubleselect.model.teacher.vo.Teacher("
				+ "t.id,t.teachersno,t.teachername,t.teachersex,"
				+ "t.teachermajor,t.guidemin,t.guidemax,t.degree) from Tteacher t ";
		if(null!=teachersno && !"".equals(teachersno)){
			hql+= " where t.teachersno=:teachersno";
			params.put("teachersno", teachersno);
			flag = true;
		}
		if(null!=teachername && !"".equals(teachername)){
			if(flag){
				hql += " and t.teachername=:teachername" ;		
				params.put("teachername", teachername);
			}else{
				hql += " where t.teachername=:teachername";
				flag = true;
				params.put("teachername", teachername);				
			}
		}
		if(null!=teachersex && !"".equals(teachersex)){
			if(flag){
				hql += " and t.teachersex=:teachersex" ;
				params.put("teachersex",teachersex);							
			}else{
				hql += " where t.teachersex=:teachersex";
				params.put("teachersex",teachersex);						
			}
		}
		if(null!=teacherId && !"".equals(teacherId)){
			if(flag){
				hql += " and t.id=:teacherId" ;
				params.put("teacherId",Integer.parseInt(teacherId));							
			}else{
				hql += " where t.id=:teacherId";
				params.put("teacherId",Integer.parseInt(teacherId));						
			}
		}
		list_return = this.teacherDao.getHJ(hql, page, row, params);
		return list_return;
	}

	public List<Teacher> getByteacherId(String teacherId){
		List<Teacher> list_return = new ArrayList<Teacher>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select new com.doubleselect.model.teacher.vo.Teacher("
				+ "t.id,t.teachersno,t.teachername,t.teachersex,"
				+ "t.teachermajor,t.guidemin,t.guidemax,t.degree) from Tteacher t ";
				hql += " where t.id=:teacherId";
				params.put("teacherId",Integer.parseInt(teacherId));						
		list_return = this.teacherDao.getByteacherId(hql, params);
		return list_return;
	}
	
	public int queryTotal(String teachersno,String teachername,String teachersex,String teacherId) {
		// TODO Auto-generated method stub
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select count(*) from Tteacher t ";
		if(null!=teachersno && !"".equals(teachersno)){
			hql+= " where t.teachersno=:teachersno";
			params.put("teachersno", teachersno);
			flag = true;
		}
		if(null!=teachername && !"".equals(teachername)){
			if(flag){
				hql += " and t.teachername=:teachername" ;		
				params.put("teachername", teachername);
			}else{
				hql += " where t.teachername=:teachername";
				flag = true;
				params.put("teachername", teachername);				
			}
		}
		if(null!=teachersex && !"".equals(teachersex)){
			if(flag){
				hql += " and t.teachersex=:teachersex" ;
				params.put("teachersex",teachersex);							
			}else{
				hql += " where t.teachersex=:teachersex";
				params.put("teachersex",teachersex);						
			}
		}
		if(null!=teacherId && !"".equals(teacherId)){
			if(flag){
				hql += " and t.id=:teacherId" ;
				params.put("teacherId",Integer.parseInt(teacherId));							
			}else{
				hql += " where t.id=:teacherId";
				params.put("teacherId",Integer.parseInt(teacherId));						
			}
		}
		return this.teacherDao.count(hql, params).intValue();
	}


	public List<Teacher> getByTeacherSno(String teacherSno) {
		List<Teacher> list_return = new ArrayList<Teacher>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select new com.doubleselect.model.teacher.vo.Teacher("
				+ "t.id,t.teachersno,t.teachername,t.teachersex,"
				+ "t.teachermajor,t.guidemin,t.guidemax,t.degree) from Tteacher t ";
				hql += " where t.teachersno=:teachersno";
				params.put("teachersno",teacherSno);						
		list_return = this.teacherDao.getByteacherSno(hql, params);
		return list_return;
	}


	public List<Tteacher> findAll() {
		return this.teacherDao.find(" from Tteacher ");
	}
}
