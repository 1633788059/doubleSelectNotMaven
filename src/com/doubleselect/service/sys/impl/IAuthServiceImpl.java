package com.doubleselect.service.sys.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doubleselect.dao.sys.IAuthDao;
import com.doubleselect.dao.sys.IUserDao;
import com.doubleselect.model.sys.Tauth;
import com.doubleselect.model.sys.vo.User;
import com.doubleselect.service.sys.IAuthService;

@Service
public class IAuthServiceImpl implements IAuthService {

	@Autowired
	private IAuthDao authDao;

	@Autowired
	private IUserDao userDao;

	
	public String buildMenuTree(User user) throws Exception {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", user.getId());
		
		List<Tauth> authList = authDao.find("select distinct t from Tauth t join fetch t.troles role join role.tusers user where user.id = :userId", params);
		List<Tauth> rootMenu = new ArrayList<Tauth>();
	
		
		StringBuilder sb = new StringBuilder();
		String tree = null;
		
		for(Tauth auth : authList){
			if(auth.getTauth() !=null && auth.getId() !=0 ){
				if(0 == auth.getTauth().getId()){	//添加到最外层菜单
					rootMenu.add(auth);
				}
			}
		}
		
		for(Tauth auth : rootMenu){
			sb.append("\t<div title=\""+auth.getAuth_name()+"\" style=\"overflow-y:auto;overflow-x:auto;padding:10px;\">\n");
			sb.append("\t<ul class=\"easyui-tree\" data-options=\"animate:true,onClick: function(node){\n");
			sb.append("\t $(this).tree('toggle', node.target);\n ");
			sb.append("\t }\">\n");
			tree = buildTree(authList, auth.getId()).replaceFirst("\t<ul>", "");	
			sb.append(tree);
			sb.append("\t</div>\n");
		}
		return sb.toString();
	}
	private String buildTree(List<Tauth> authList,  int id) throws UnsupportedEncodingException{	
		
		StringBuilder sb = new StringBuilder();
		sb.append("\t<ul>\n");	
		
		for(Tauth auth : authList){	
			if(id == auth.getTauth().getId()){						
				if(auth.getUrl() != null && !"".equals(auth.getUrl())){	//文件的情况			
					sb.append("\t<li>\n");
					sb.append("\t<span><div onmouseout=\"javascript:this.style.color='black'\" onmouseover=\"javascript:this.style.color='red'\" onclick=\"addTab('"+auth.getAuth_name()+"','"+auth.getUrl()+"')\">"+auth.getAuth_name()+"</div></span>\n");					
					sb.append("\t</li>\n");
				}else{								//文件夹的情况
					sb.append("\t<li data-options=\"state:'closed'\">\n");
					sb.append("\t<span" + "><div href=\"javascript:void(0)\" >"+auth.getAuth_name()+"</div></span>\n");	
					String build = buildTree(authList, auth.getId());
					sb.append(build);
					sb.append("\t</li>\n");
				}					
			}		
		}	
		sb.append("\t</ul>\n");	

		return sb.toString();
	}
	
	
	public String queryTreeMenu(int rid) {
		
		List<Tauth> authList = query();
		List<Tauth> hasAuthList = queryAuthByRole(rid);
		List<Integer> aids = new ArrayList<Integer>();
		for(Tauth tauth : hasAuthList){
			aids.add(tauth.getId());
		}
		String buildTree = buildCheckedTree(authList, hasAuthList, aids, 0);
		buildTree = buildTree.replaceFirst("\"state\":\"closed\",\"children\":", "");
		return buildTree;
	}
	
	public List<Tauth> query(){
		List<Tauth> authList = new ArrayList<Tauth>();
		authList = authDao.find("from Tauth t");
		return authList;
	}
	
	
	public List<Tauth> queryAuthByRole(int rid) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", rid);
		List<Tauth> authList = authDao.find("select distinct t from Tauth t join fetch t.troles role where role.id = :id", params);
		return authList;
	}
	
	//判断是否拥有该权限
	private String buildCheckedTree(List<Tauth> authList, List<Tauth> hasAuthList,
			List<Integer> aids,  int id){
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		sb.append("\"state\":\"closed\",");
		sb.append("\"children\":[");
		for(Tauth tauth : authList){
			boolean checked = false;
			if( tauth.getId() == 0 || tauth.getTauth() == null){
				continue;
			}
			if(id == tauth.getTauth().getId()){
				flag = true;
				sb.append("{ \t\"id\":\"")
				.append(""+tauth.getId()+"")
				.append("\",\n")
				.append("\t\"text\":\"")	
				.append(""+tauth.getAuth_name()+"")
				.append("\",\n")
				.append("\t\"checked\":");
				if(aids.contains(tauth.getId()) && !hasChild(hasAuthList ,tauth.getId())){	
					checked = true;
				}
				if(checked)
					sb.append("true\n");
				else
					sb.append("false\n");
				String build = buildCheckedTree(authList, hasAuthList, aids, tauth.getId());
				if(build != null){				
					sb.append(",");
					sb.append(build);
				}
				sb.append("},\n");
			}			
		}
		if(flag){
			sb = sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, "" );
			sb.append("]\n");
			}	
		else		
			return null;
		return sb.toString();
	}
	//判断是否拥有子节点
	private boolean hasChild(List<Tauth> authList, int id){
		for(Tauth tauth : authList){
			if(tauth.getTauth().getId() == id) 
				return true;
		}	
		return false;
	}
	
}
