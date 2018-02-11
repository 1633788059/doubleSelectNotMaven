package com.doubleselect.util.FormTool;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateEditor extends PropertyEditorSupport{
	
	@Override
	public void setAsText(String text){
		Date value = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			sdf.parse(text);
		}catch (Exception e) {
			try {
				sdf.parse("yyyy-MM-dd");
			} catch (ParseException e1) {
				value = null;
			}
			
			e.printStackTrace();
		}
		//这一步将转换好的数据写入到对象映射的属性中  
		setValue(value);
	}

}
