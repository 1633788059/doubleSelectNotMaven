package com.doubleselect.service.student.imp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.doubleselect.dao.student.StudentDao;
import com.doubleselect.model.student.Tstudent;
import com.doubleselect.model.student.vo.Student;
import com.doubleselect.service.student.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentDao studentDao;

	
	
	public void save(Student t) throws Exception {
		Tstudent tdd = new Tstudent();
		BeanUtils.copyProperties(t, tdd);
		this.studentDao.save(tdd);	
	}

	
	public void delete(Student t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		this.studentDao.delete(id, Tstudent.class);
	}

	
	public void delete(int[] ids) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void update(Student t) throws Exception {
		// TODO Auto-generated method stub
		Tstudent tdd = new Tstudent();
		BeanUtils.copyProperties(t, tdd);
		this.studentDao.update(tdd);	
	}

	
	public List<Student> getByPage(int page, int row, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Student getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int queryTotal(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public List<Student> getByPage(int page, int row, String studentsno,String studentname,String studentsex,String studentId) {
		List<Student> list_return = new ArrayList<Student>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		/*int id, String studentsno,String studentname,String studentsex,
		 *  String studentmajor,
		String finaldegree, String graduation, Date time, 
		String expertise*/
		String hql = "select new com.doubleselect.model.student.vo.Student(t.id,t.studentsno,t.studentname,t.studentsex,t.studentmajor,t.finaldegree,t.graduation,t.time," +
				"t.expertise) from Tstudent t ";
		if(null!=studentsno && !"".equals(studentsno)){
			hql+= " where t.studentsno=:studentsno";
			params.put("studentsno", studentsno);
			flag = true;
		}
		if(null!=studentname && !"".equals(studentname)){
			if(flag){
				hql += " and t.studentname=:studentname" ;		
				params.put("studentname", studentname);
			}else{
				hql += " where t.studentname=:studentname";
				flag = true;
				params.put("studentname", studentname);				
			}
		}
		if(null!=studentsex && !"".equals(studentsex)){
			if(flag){
				hql += " and t.studentsex=:studentsex" ;
				params.put("studentsex",studentsex);							
			}else{
				hql += " where t.studentsex=:studentsex";
				params.put("studentsex",studentsex);						
			}
		}
		if(null!=studentId && !"".equals(studentId)){
			if(flag){
				hql += " and t.id=:studentId" ;
				params.put("studentId",Integer.parseInt(studentId));							
			}else{
				hql += " where t.id=:studentId";
				params.put("studentId",Integer.parseInt(studentId));						
			}
		}
		list_return = this.studentDao.getHJ(hql, page, row, params);
		return list_return;
	}

	public List<Student> getByStudentId(String studentId){
		List<Student> list_return = new ArrayList<Student>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select new com.doubleselect.model.student.vo.Student(t.id,t.studentsno,t.studentname,t.studentsex,t.studentmajor,t.finaldegree,t.graduation,t.time," +
				"t.expertise) from Tstudent t ";
				hql += " where t.id=:studentId";
				params.put("studentId",Integer.parseInt(studentId));						
		list_return = this.studentDao.getBystudentId(hql, params);
		return list_return;
	}
	
	public int queryTotal(String studentsno,String studentname,String studentsex,String studentId) {
		// TODO Auto-generated method stub
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select count(*) from Tstudent t ";
		if(null!=studentsno && !"".equals(studentsno)){
			hql+= " where t.studentsno=:studentsno";
			params.put("studentsno", studentsno);
			flag = true;
		}
		if(null!=studentname && !"".equals(studentname)){
			if(flag){
				hql += " and t.studentname=:studentname" ;		
				params.put("studentname", studentname);
			}else{
				hql += " where t.studentname=:studentname";
				flag = true;
				params.put("studentname", studentname);				
			}
		}
		if(null!=studentsex && !"".equals(studentsex)){
			if(flag){
				hql += " and t.studentsex=:studentsex" ;
				params.put("studentsex",studentsex);							
			}else{
				hql += " where t.studentsex=:studentsex";
				params.put("studentsex",studentsex);						
			}
		}
		if(null!=studentId && !"".equals(studentId)){
			if(flag){
				hql += " and t.id=:studentId" ;
				params.put("studentId",Integer.parseInt(studentId));							
			}else{
				hql += " where t.id=:studentId";
				params.put("studentId",Integer.parseInt(studentId));						
			}
		}
		return this.studentDao.count(hql, params).intValue();
	}


	public List<Student> getByStudentSno(String studentSno) {
		List<Student> list_return = new ArrayList<Student>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select new com.doubleselect.model.student.vo.Student(t.id,t.studentsno,t.studentname,t.studentsex,t.studentmajor,t.finaldegree,t.graduation,t.time," +
				"t.expertise) from Tstudent t ";
				hql += " where t.studentsno=:studentsno";
				params.put("studentsno",studentSno);						
		list_return = this.studentDao.getBystudentSno(hql, params);
		return list_return;
	}


	public List<Tstudent> findAll() {
		return this.studentDao.find(" from Tstudent ");
		
	}
}
