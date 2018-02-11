package com.doubleselect.controller.image;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.doubleselect.controller.BaseController;
import com.doubleselect.model.image.vo.Image;
import com.doubleselect.model.vo.Json;
import com.doubleselect.service.image.ImageService;

@Controller
@RequestMapping("/image/imagemessage/")
public class imageController extends BaseController{
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping("open")
	public String open(){
		return "Image/Imagemessage/list";
	}
	@RequestMapping("add")
	@ResponseBody
	public Json add(Image t,HttpServletRequest request){
		Json json = new Json();
		try {
			this.imageService.save(t);
			json.setSuccess(true);
			json.setMsg("添加成功！");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("添加失败，请尝试刷新之后再操作！");
			e.printStackTrace();
		}
		
		return json;
	}
	
	
	@RequestMapping("update")
	@ResponseBody
	public Json update(Image t,int id,HttpServletRequest request){
		Json json = new Json();
		try {

			this.imageService.update(t);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("修改失败，请尝试刷新之后再操作！");
			e.printStackTrace();
		}
		
		return json;
	}
	/**
	 * 
	@RequestMapping("delete")
	 * @param ids
	 * @return
	 */
	
	@RequestMapping(value="delete",produces="text/html;charset=UTF-8")
	@ResponseBody
	public Json delete(int []ids){
		Json json = new Json();
		try {
			for(int id:ids){
				
				this.imageService.delete(id);
			}
			json.setSuccess(true);
			json.setMsg("删除成功！");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("删除失败，请尝试刷新之后再操作！");
			e.printStackTrace();
		}
		
		return json;
	}
	
	@RequestMapping("search")
	@ResponseBody
	public Map<String,Object> search(int page,int rows,@RequestParam(value="sno",required=false) String sno) throws Exception{

		/**
		 * tomcat7使用时注释去掉
		 */
		if(sno!=null){
		 sno= new String(sno.getBytes("ISO8859-1"),"UTF-8");
		}	
	
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<Image> list = null;
		int total = 0;
		try {
			total = this.imageService.queryTotal(sno);
			list = this.imageService.getByPage(page, rows, sno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("rows",list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 根据学生学号查找学生所有信息
	 * @param ImageId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("searchImage")
	@ResponseBody
	public Json searchImage(String sno) throws Exception{
		Json json=new Json();
		List<Image> list = null;
		try {
			list = this.imageService.getBysno(sno);
			if(list.size()==0){
				json.setSuccess(false);
			}else{
				json.setObj(list);
				json.setSuccess(true);
				json.setMsg("获取信息成功！");
				}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("获取信息失败，请尝试刷新之后再操作！");
			e.printStackTrace();
		}
		return json;
	}
	
}
