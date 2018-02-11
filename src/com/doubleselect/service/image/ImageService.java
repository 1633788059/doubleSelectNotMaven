package com.doubleselect.service.image;

import java.util.List;

import com.doubleselect.model.image.vo.Image;
import com.doubleselect.service.IBaseService;


public interface ImageService extends IBaseService<Image> {
	/**
	 * 方法描述:根据学生学号，学生姓名，学生性别查询学生信息返回符合条件的全部信息
	 * 方法名：getByPage
	 * @param page
	 * @param row
	 * @param sno 学生学号
	 * @return

	 */
	public List<Image> getByPage(int page, int row, String sno);
	
	/**
	 * 方法描述：根据条件查询符合条件的信息的数量的多少
	 * 方法名：queryTotal
	 * @param sno 学号
	 * @return
	 * 返回值：int
	 */
	public int queryTotal(String sno);
	
	/**
	 * 根据学生id查找学生的详细信息
	 * @param studentId
	 * @return
	 */
	public List<Image> getBysno(String sno);
}
