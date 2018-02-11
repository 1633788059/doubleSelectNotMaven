package com.doubleselect.controller.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.doubleselect.model.sys.Tshujuzd;
import com.doubleselect.model.vo.Json;
import com.doubleselect.service.sys.IShujuzdService;



/**
 * 
 * 类描述：数据字典控制器
 * 类名称：ShujuzdController
 * 创建人： hy
 * 创建时间：2014-8-27 下午12:04:00
 * @version 1.0
 */
@Controller
@RequestMapping("sys/shujuzdController")
public class ShujuzdController {

	@Autowired
	private IShujuzdService shujuzdService;
	
	@RequestMapping("open")
	public String open(){
		return "sys/shujuzd/list";
	}
	
	@RequestMapping("search")
	@ResponseBody
	public Map<String, Object> search(int page, int rows, String zdlb) {
		long total = 0;
		List<Tshujuzd> sjzdList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			total = shujuzdService.queryTotal(zdlb);
			sjzdList = shujuzdService.query(page, rows, zdlb);
			map.put("total", total);
			map.put("rows", sjzdList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public boolean delete(int id){
		try {
			shujuzdService.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping("update")
	@ResponseBody
	public boolean update(Tshujuzd bean){
		try {
			shujuzdService.update(bean);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * 方法描述：行提交，没有办法返回标志，所以要用到后台io流
	 * 方法名：add
	 * @param sjzd
	 * 返回值：void
	 */
	@RequestMapping("add")
	public void add(Tshujuzd sjzd, HttpServletResponse response){
		Json json = new Json();
		try {
			if(shujuzdService.add(sjzd)){
				json.setMsg("添加成功");
				json.setSuccess(true);
			}
		} catch (Exception e) {
			json.setMsg("添加失败");
			json.setSuccess(false);
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null; 
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (out != null) {
			out.print("<alert>添加成功！</alert>");
			out.flush();
			out.close();
		}	
	}
}
