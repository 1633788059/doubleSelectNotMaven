package com.doubleselect.util.realm;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;

import com.doubleselect.service.interceptor.IAuthInterceptorService;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section>{

	@Resource
	private IAuthInterceptorService iAuthInterceptorService;

    private String filterChainDefinitions;

    /**
     * 默认premission字符串
     */
    public static final String PREMISSION_STRING="perms[\"{0}\"]";

    public Section getObject() throws BeansException {

        //获取所有Resource
    	List<String>  list= this.iAuthInterceptorService.getList();

        Ini ini = new Ini();
        //加载默认的url
        ini.load(filterChainDefinitions);
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        //循环Resource的url,逐个添加到section中。section就是filterChainDefinitionMap,
        //里面的键就是链接URL,值就是存在什么条件才能访问该链接
        for (Iterator<String> it = list.iterator(); it.hasNext();) {

            String resource = it.next();
            //如果不为空值添加到section中
            //这里如果不去除这两个权限，则该路径找不到,这里也不知道为什么
            if(!resource.equals("teacher/teachermessage/uploadImage")||!resource.equals("student/studentmessage/uploadImage")){
            section.put("/"+resource+"*",  MessageFormat.format(PREMISSION_STRING,resource));}
           

        }
        for (Object obj : section.entrySet()) {
            Entry entry = (Entry) obj;
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            //System.out.println(key + "=" + value);
       }
        return section;
    }

    /**
     * 通过filterChainDefinitions对默认的url过滤定义
     * 
     * @param filterChainDefinitions 默认的url过滤定义
     */
    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    public Class<?> getObjectType() {
        //return this.getClass();
        return Section.class;
    }

    public boolean isSingleton() {
        return false;
    }

}


