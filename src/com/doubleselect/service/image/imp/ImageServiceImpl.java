package com.doubleselect.service.image.imp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doubleselect.dao.image.ImageDao;
import com.doubleselect.model.image.Timage;
import com.doubleselect.model.image.vo.Image;
import com.doubleselect.service.image.ImageService;


@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ImageDao imageDao;

	
	
	public void save(Image t) throws Exception {
		Timage tdd = new Timage();
		BeanUtils.copyProperties(t, tdd);
		this.imageDao.save(tdd);	
	}

	
	public void delete(Image t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		this.imageDao.delete(id, Timage.class);
	}

	
	public void delete(int[] ids) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void update(Image t) throws Exception {
		// TODO Auto-generated method stub
		Timage tdd = new Timage();
		BeanUtils.copyProperties(t, tdd);
		this.imageDao.update(tdd);	
	}

	
	public List<Image> getByPage(int page, int row, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Image getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int queryTotal(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public List<Image> getByPage(int page, int row, String sno) {
		List<Image> list_return = new ArrayList<Image>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		/*int id, String studentsno,String studentname,String studentsex,
		 *  String studentmajor,
		String finaldegree, String graduation, Date time, 
		String expertise*/
		String hql = "select new com.doubleselect.model.image.vo.Image(t.id,t.sno,t.imagename,t.imagepath" +
				") from Timage t ";
		if(null!=sno && !"".equals(sno)){
			hql+= " where t.sno=:sno";
			params.put("sno",sno);
			flag = true;
		}
		list_return = this.imageDao.getHJ(hql, page, row, params);
		return list_return;
	}

	public List<Image> getBysno(String sno){
		List<Image> list_return = new ArrayList<Image>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select new com.doubleselect.model.image.vo.Image(t.id,t.sno,t.imagename,t.imagepath" +
				") from Timage t ";
		if(null!=sno && !"".equals(sno)){
			hql+= " where t.sno=:sno";
			params.put("sno",sno);
			flag = true;
		}			
		list_return = this.imageDao.getBySno(hql, params);
		return list_return;
	}
	
	public int queryTotal(String sno) {
		// TODO Auto-generated method stub
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select new com.doubleselect.model.image.vo.Image(t.id,t.sno,t.imagename,t.imagepath" +
				") from Timage t ";
		if(null!=sno && !"".equals(sno)){
			hql+= " where t.sno=:sno";
			params.put("sno",sno);
			flag = true;
		}
		return this.imageDao.count(hql, params).intValue();
	}
}
