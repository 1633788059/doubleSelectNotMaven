package com.doubleselect.dao.image;

import java.util.List;
import java.util.Map;

import com.doubleselect.dao.IBaseDao; 
import com.doubleselect.model.image.Timage;
import com.doubleselect.model.image.vo.Image;

public interface ImageDao extends IBaseDao<Timage>{
	/**
	 * 方法描述:根据学生学号，学生姓名，学生性别查询学生信息返回符合条件的全部信息
	 * 方法名：getHJ
	 * @param page
	 * @param row
	 * @param sno 学号
	 * @return
	 * @throws Exception
	 */
	public List<Image> getHJ(String hql,int page, int row,Map<String,Object> param);

	/**
	 * 根据sno获取所有信息
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<Image> getBySno(String hql, Map<String, Object> params);

}
